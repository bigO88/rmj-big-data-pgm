
%default START_DATE_Q1 '2016-01-01' 
%default END_DATE_Q1 '2016-03-31'
%default USER_TBL 'user_created'
%default GAME_TBL 'game_played'
%default OUTPUT_TBL 'task_first_output_1'
%default RETENTION_D '1'


user_created = LOAD '$USER_TBL' using org.apache.hive.hcatalog.pig.HCatLoader();

filtered_user = FILTER user_created BY loaddate >= ToDate('$START_DATE_Q1') AND loaddate <= ToDate('$END_DATE_Q1');

user_created_q1_2016 = FOREACH filtered_user GENERATE  $0,$1;


game_played = LOAD '$GAME_TBL' USING org.apache.hive.hcatalog.pig.HCatLoader();

filtered_game_events = FILTER game_played BY loaddate >= ToDate('$START_DATE_Q1') AND loaddate <= ToDate('$END_DATE_Q1') AND ( principal is not null and TRIM(principal) != '' AND  TRIM(principal) != 'guest');


game_events_mapped = FOREACH filtered_game_events GENERATE $0,(long)$3;

game_events_mapped_1 =  FOREACH (group game_events_mapped by $1) {
rec = ORDER game_events_mapped by event_ts DESC;
last_played  = LIMIT rec 1;
GENERATE FLATTEN(last_played);
};


joined_records = join user_created_q1_2016 by user_id, game_events_mapped_1 by principal;

records_with_days_diff = FOREACH joined_records GENERATE $0 as user_id ,ToUnixTime(ToDate($1)) as created_on_ts,ToUnixTime(ToDate($2)) as last_played ,(long)DaysBetween(ToDate(created_on_ts),ToDate(event_ts)) as days ;


final_output = FILTER records_with_days_diff by (days == (long)'$RETENTION_D');


store final_output into '$OUTPUT_TBL' using org.apache.hive.hcatalog.pig.HCatStorer('days=$RETENTION_D');
