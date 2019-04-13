#!/usr/bin/bash


usage()
{

echo "$0 <JAR-FILE-PATH> <INPUT-FILE> <OUTPUT-FILE-PATH> <HIVE-DATABASE> <HIVE-OUTPUT-TABLE-NAME>" 

}

if [ $# -eq 5 ]
then

jar_file=$1
inputFile=$2
outputFile=$3
database_name=$4
table_name=$5

echo "The spark application running............"

spark-submit --master yarn --deploy-mode client --driver-memory 4g –num-executors 2 --executor-memory 2g --executor-cores 2 --class com.rmj.challenge.one.driver.MainDriver $jar_file hdfs://$inputFile hdfs://$outputFile
rc=$?

if [ $rc -eq 0 ]
then

echo "Job summery: "
echo "The processed file location HDFS: $outputFile/processed"
echo "The dedup file location in HDFS: $outputFile/dedup"
echo "The rejected records file location in HDFS: $outputFile/rejected"
echo "Spark application run successfully creating hive table on csv output...."

hive_external_location="hdfs://$outputFile/processed/"

hive_query="CREATE EXTERNAL TABLE IF NOT EXISTS $table_name(
        AccountNumber STRING,
        MostUsedServiceNameFirst STRING,
        MostUsedServiceNameSecond STRING,
        eventType STRING,
        imei STRING,
        macAddress STRING,
        accessPoint STRING,
        totalBytes STRING,
        totalCount STRING,
        totalTime STRING,
        insertionDate STRING
        )
    COMMENT 'Data about Telco transactions'
    ROW FORMAT DELIMITED
    FIELDS TERMINATED BY ','
    STORED AS TEXTFILE
    location '$hive_external_location';"

hive -e "use $database_name;"
hive -e $hive_query


fi

else

usage

fi
