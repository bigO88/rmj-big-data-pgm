
%default START_DATE_Q1 '2016-01-01' 
%default END_DATE_Q1 '2016-03-31'
%default USER_TBL 'user_created'
%default GAME_TBL 'game_played'
%default OUTPUT_TBL 'task_third_output'
%default RETENTION_D '2'


user_created = LOAD '$USER_TBL' using org.apache.hive.hcatalog.pig.HCatLoader();

filtered_user = FILTER user_created BY loaddate >= ToDate('$START_DATE_Q1') AND loaddate <= ToDate('$END_DATE_Q1');

user_created_q1_2016 = FOREACH filtered_user GENERATE  $0,ToDate($1) as created_on_ts;


game_played = LOAD 'game_played' USING org.apache.hive.hcatalog.pig.HCatLoader();

filtered_game_events = FILTER game_played BY loaddate >= ToDate('2016-01-01') AND loaddate <= ToDate('2016-03-31') AND ( principal is not null and TRIM(principal) != '' AND  TRIM(principal) != 'guest');


game_events_mapped = FOREACH filtered_game_events GENERATE ToDate($0) as event_ts,$1,(long)$3;

game_events_mapped_1 =  FOREACH (group game_events_mapped by $1) {
rec_desc = ORDER game_events_mapped by event_ts DESC;
rec_asc = ORDER game_events_mapped by event_ts ASC;
last_played  = LIMIT rec_desc 1;
first_played = LIMIT rec_asc 1;
GENERATE FLATTEN(last_played.principal) as principal, FLATTEN(last_played.event_ts) as last_event_ts,FLATTEN(first_played.event_ts) as first_event_ts,FLATTEN(first_played.request_uri) as first_game_played;
};


joined_records = join user_created_q1_2016 by user_id, game_events_mapped_1 by principal;

records_with_days_diff = FOREACH joined_records GENERATE $0 as user_id ,$1 as created_on_ts,$3 as last_played,$4 as  first_event_ts,$5 as first_game_played,(long)DaysBetween(created_on_ts,last_event_ts) as days ;

final_output = FILTER records_with_days_diff by (days == (long)'$RETENTION_D');

store final_output into '$OUTPUT_TBL' using org.apache.hive.hcatalog.pig.HCatStorer('days=$RETENTION_D');

