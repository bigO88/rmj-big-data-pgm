SET hive.exec.dynamic.partition=true; 
SET hive.exec.dynamic.partition.mode=nonstrict;

insert overwrite table ${hiveconf:table_master} partition(loaddate) select event_ts,split(request_uri,'=')[1],session_id,principal,cast( regexp_replace(loaddate,"/","-") as date) as loaddate  from ${hiveconf:table_source};

drop table ${hiveconf:table_source};
