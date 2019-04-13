SET hive.exec.dynamic.partition=true; 
SET hive.exec.dynamic.partition.mode=nonstrict;


CREATE EXTERNAL TABLE task_second_output(
user_id bigint,
created_on_ts bigint,
last_played bigint
)
partitioned by (days bigint)
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
LOCATION 'hdfs:///user/applicant/output/task_second';
