SET hive.exec.dynamic.partition=true; 
SET hive.exec.dynamic.partition.mode=nonstrict;

insert overwrite table ${hiveconf:table_master} partition(loaddate) select user_id,created_on_ts,common_name,user_type_cd,gender_cd,country_cd,us_state_cd,cast( regexp_replace(loaddate,"/","-") as date) as loaddate from ${hiveconf:table_source};

drop table ${hiveconf:table_source};
