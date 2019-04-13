#!/usr/bin/bash

usage()
{
echo "$0 <SOURCE-FILE> <PROCESSED-OUTPUT>"
}

if [ $# -eq 2 ]
then

source_file=$1
processed_csv=$2

echo "The performing all actions mentioned in challenge 3 of point 1...."
############################## a. NO Customer missing #####################################
count=`cat $processed_csv |awk -F"," '{print $1}'|wc -l`
echo "Total customers in $processed_csv file: $count"

if [ $count -eq 0 ]
then
echo "File $processed_csv has  no customers"
exit 1;
else
echo "File $processed_csv has customers."
fi

########################################################################################

########################## b. check any random customer values from processed file ######

custId=`head -1 $processed_csv |awk -F"," '{print $1}'`
echo "Checking the customer $custId"

grep $custID $1
rc=$?
if [ $rc -eq 0 ]
then
echo "The customer present in source file"
else
echo "The customer not present in source file"
fi

custId=`tail -1 $processed_csv |awk -F"," '{print $1}'`
echo "Checking the customer $custId"

grep $custID $1
rc=$?
if [ $rc -eq 0 ]
then
echo "The customer present in source file"
else
echo "The customer not present in source file"
fi
######################################################################################

##################### c. Apply rejection rules on processed file and check any voilation #####

echo "checking Null or Empty subscriberId in processed file."
emptyCustIdCount=`cat $processed_csv |awk -F"," '{print $1}'|grep "^$" "wc -l`
                        
if [ $emptyCustIdCount -z "" ]
then
echo "No Null or Empty subscriberId in processed file"
else
echo " $emptyCustIdCount Null or Empty subscriberId in processed file"
fi

###########################################################################################


############ d. Calculation of count total number of records in source file ###############
count_total=`wc -l $source_file`
echo "Total count in source file: $count_total"
########################################################################################

else
usage
fi 
