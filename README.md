## Solutions & approache:


### STEP 1.) Loading daily events to hive tables:

use setup.sh for setup of base dir  & tables.

1.) The daily events file will be at /data/incoming/{event_name}/{year}/{month}/{day}/ and below loader script will load this file to each events 
master table used for analytical perpose:

 a.) user_created : /home/applicant/rajjanwa/sqls/create_master_user_created.sql

 b.) game_played :  /home/applicant/rajjanwa/sqls/create_master_game_played.sql


The script: daily_event_loader.sh will load daily events by creating temp hive table on the incoming files and loading master tables which is partition by loaddate: YYYY-MM-DD 

 a.) user_created_YYYY_MM_DD : /home/applicant/rajjanwa/sqls/create_temp_user_created.sql 
 
 b.) game_played_YYYY_MM_DD :  /home/applicant/rajjanwa/sqls/create_temp_game_played.sql

The sql to load by partition date:

a.) user_evetn: /home/applicant/rajjanwa/sqls/load_user_created.sql

b.) game_event: /home/applicant/rajjanwa/sqls/load_game_played.sql


once data is available into master tables we can performs tasks:

### Step 2.) Tasks:

Task 1 ->
create output table by setting external path in hdfs:
SQL: /home/applicant/rajjanwa/task1/create_task_1_out_table.sql

Pig Script: /home/applicant/rajjanwa/task1/rajjanwa_task_first.pig

This pig script will create output with partition by Day diff as retention period.


Task 2 ->
create output table by setting external path in hdfs:
SQL: /home/applicant/rajjanwa/task2/create_task_2_out_table.sql

Pig Script: /home/applicant/rajjanwa/task2/rajjanwa_task_second.pig

This pig script will create output with partition by Day diff as retention period and stored the timestamp as Unixtimestamp.

Task 3:
create output table by setting external path in hdfs:
SQL: /home/applicant/rajjanwa/task3/create_task_3_out_table.sql

Pig Script: /home/applicant/rajjanwa/task3/rajjanwa_task_third.pig

This pig script will create output for user's first game played and date of first game played.

## Scala/Spark setup :

This project can be import in scala ide using:

1.) sbt clean eclipse

2.) sbt clean assembly {there can heap memory issuse if not allow enough memory for SBT_OPTS}

3.) build the project and use run scripts available in project's scripts folder.
