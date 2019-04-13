SET hive.exec.dynamic.partition=true; 
SET hive.exec.dynamic.partition.mode=nonstrict;


CREATE EXTERNAL TABLE game_played (
event_ts string,
request_uri string,
session_id string,
principal string
)
partitioned by (loaddate date)
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
LOCATION 'hdfs:///user/applicant/events/game_played';
