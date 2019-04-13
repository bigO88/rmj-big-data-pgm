SET hive.exec.dynamic.partition=true; 
SET hive.exec.dynamic.partition.mode=nonstrict;


CREATE EXTERNAL TABLE task_third_output(
user_id bigint,
created_on_ts timestamp,
last_played timestamp,
first_event_ts timestamp,
first_game_played string
)
partitioned by (days bigint)
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
LOCATION 'hdfs:///user/applicant/output/task_third';
