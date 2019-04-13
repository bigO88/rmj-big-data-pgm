#! /bin/bash

export BASE_DIR=/home/applicant/rajjanwa


echo "Creating all base direcotry in HDFS..."
sh create_dirs.sh

if [ $? -eq 0 ]
then

echo "Creating all hive tables in hive"

hive -f $BASE_DIR/sqls/create_master_game_played.sql
hive -f $BASE_DIR/sqls/create_master_user_created.sql
hive -f $BASE_DIR/task1/create_task_1_out_table.sql
hive -f $BASE_DIR/task2/create_task_2_out_table.sql
hive -f $BASE_DIR/task3/create_task_3_out_table.sql

else

echo "ERROR in base directory creartions. Please check"

fi
