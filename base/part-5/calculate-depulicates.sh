#! /usr/bin/bash


usage()
{
echo "$0 <SOURCE-FILE> <DEDUP-OUTPUT>"
}

if [ $# -eq 2 ]
then
sourceFile=$1
dedupFile=$2

CountSource=`sort $sourceFile |uniq --count|awk '{print $1}'|grep -v 1|paste -sd+ | bc`

CountDedupFile=`sort $dedupFile |uniq --count|awk '{print $1}'|paste -sd+ | bc`

Countprocessd=`sort $dedupFile |uniq |wc -l`

total_processed=`expr $CountDedupFile + $Countprocessd`

echo "Summery of count: "
echo "Total duplicate records in Source file: $dupCountSource"
echo "Toatl duplicate recrods in dedup output file: $dupCountDedupFile"
echo "Total records in processed file after dedup in dedup file: $uniqCountWentIprocessd"

if [ $CountSource  -eq  $total_processed ]
then
echo "Dedup records count matched"
else
echo "Dedup records count missmatched.Please check"
fi
else
usage
fi 


