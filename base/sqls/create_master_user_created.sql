SET hive.exec.dynamic.partition=true; 
SET hive.exec.dynamic.partition.mode=nonstrict;


CREATE EXTERNAL TABLE user_created (
user_id bigint, 
created_on_ts string, 
common_name string, 
user_type_cd string, 
gender_cd string, 
country_cd string, 
us_state_cd string 
)
partitioned by (loaddate date)
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
LOCATION 'hdfs:///user/applicant/events/user_created';
