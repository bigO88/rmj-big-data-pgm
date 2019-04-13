#! /usr/bin/bash

usage()
{

echo "$0 <hdfs-location-data-file> <table-name> <database>"


}

if [ $# -eq 3 ]
then

hive_external_location=$1
table_name=$2
database_name=$3

hive_query="CREATE EXTERNAL TABLE IF NOT EXISTS $table_name(
        sub_id STRING,
        startDateTime STRING,
        cell_id INT,
        location_id STRING
        )
    COMMENT 'Data about Telco transactions'
    ROW FORMAT DELIMITED
    FIELDS TERMINATED BY ','
    STORED AS TEXTFILE
    location '$hive_external_location';"

hive -e "use $database_name;"
hive -e $hive_query

else
usage
fi
