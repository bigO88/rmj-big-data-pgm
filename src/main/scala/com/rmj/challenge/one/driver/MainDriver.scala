package com.rmj.challenge.one.driver

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

import com.rmj.challenge.one.model.TelcoData
import com.rmj.challenge.one.dpirules.DpiRuleProcessor._
import org.apache.spark.storage.StorageLevel

/**
 *  Desc: This class is the main driver program to process the telco file and out will be
 *  three files as:
 *  Processed: The main file processed after applying all the DPI rules.
 *  DEDUP: This file contains all the duplicate records
 *  REJECTED: This file contains all the records having empty customer id.
 *
 */

object MainDriver {

  def main(args: Array[String]) = {

    val sparkconf = new SparkConf().setAppName("MainDriver")
    implicit val sc = new SparkContext(sparkconf)

    if (args.length < 1) {
      println("Usage: MainDriver <input-file-path-hdfs> <output-file-path-hdfs> ")
      System.exit(0)
    }

    val inputFile = args(0)
    val outputFile = args(1)

    val inputRDD = sc.textFile(inputFile, sc.defaultParallelism * 3).map(x => (x.hashCode(), x)).groupByKey().persist(StorageLevel.MEMORY_ONLY_SER_2)

    val dedupRDD = inputRDD.filter(_._2.size > 1)

    dedupRDD.map { x => x._2.toList.tail }.flatMap(x => x).saveAsTextFile(outputFile + "/dedup/")

    val validRdd = inputRDD.map(x => TelcoData(x._2.head)).map(x => (x.subscriberId, x))

    val groupRdd = validRdd.filter(x => x._1.size != 0 || !x._1.isEmpty()).groupByKey()

    groupRdd.map { x => outputRecord(x._2.toList) }.saveAsTextFile(outputFile + "/processed/")

    val rejectedRecord = validRdd.filter(x => x._1.size == 0 || x._1.equals("") || x._1.isEmpty()).map(_._2.toString().replace("(", "").replace(")", ""))

    rejectedRecord.saveAsTextFile(outputFile + "/rejected/")

  }
}
