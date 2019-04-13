#! /usr/bin/bash
#
#
#
#
#
#



export HDFS_INCOMING_DIR=/data/incoming
export HIVE_SQL_DIR=/home/applicant/rajjanwa/sqls


usage(){
echo "
Usage:  sh daily_event_loader.sh <event-type>  <year> <month> <day>

   Notes: 
      1.) event-type : this is required to load daily game_event and user_creation events option: game|user
      2.) year: YYYY
      3.) month: MM
      4.) day: DD
"
exit 1;
}


if [ $# -eq 4 ]
then

event_type=$1
load_year=$2
load_month=$3
load_day=$4


case $event_type in 
"game")

#################### This section to load user_created events to game_played master hive table"

event_master_table=game_played

event_daily_temp_table=game_played_${load_year}_${load_month}_${load_day}

input_file_path=$HDFS_INCOMING_DIR/game_played/$load_year/$load_month/$load_day

echo "Loading $event_type events for $load_year/$load_month/$load_day into $event_master_table hive master table."

echo "Creating temp external table $event_daily_temp_table for $load_year/$load_month/$load_day on $input_file_path hdfs location"

echo "Executing hive query"

hive -S -hiveconf table_name=$event_daily_temp_table -hiveconf file_path=$input_file_path -f $HIVE_SQL_DIR/create_temp_game_played.sql
rc=$?

if [ $rc -eq 0 ]
then

hive -S -hiveconf table_source=$event_daily_temp_table -hiveconf table_master=$event_master_table -f $HIVE_SQL_DIR/load_game_played.sql

else

echo "Temp table creation failed. Please check"

fi
;;

"user")

#################### This section to load user_created events to user_created master hive table"
event_master_table=user_created

echo "Loading $event_type events for $load_year/$load_month/$load_day into $event_master_table hive master table..."

event_daily_temp_table=user_created_${load_year}_${load_month}_${load_day}
input_file_path=$HDFS_INCOMING_DIR/user_created/$load_year/$load_month/$load_day

echo "Creating temp external table $event_daily_temp_table for $load_year/$load_month/$load_day on $input_file_path hdfs location"

echo "Executing hive query"

hive -S -hiveconf table_name=$event_daily_temp_table -hiveconf file_path=$input_file_path -f $HIVE_SQL_DIR/create_temp_user_created.sql
rc=$?


if [ $rc -eq 0 ]
then

hive -S -hiveconf table_source=$event_daily_temp_table -hiveconf table_master=$event_master_table -f $HIVE_SQL_DIR/load_user_created.sql

else

echo "Temp table creation failed. Please check"

fi


;;
*)
usage
;;
esac

else
usage
fi
