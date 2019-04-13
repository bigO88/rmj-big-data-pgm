CREATE EXTERNAL TABLE ${hiveconf:table_name} (
event_ts string,
request_uri string,
session_id string,
principal string,
loaddate string 
)
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
LOCATION 'hdfs://${hiveconf:file_path}';
