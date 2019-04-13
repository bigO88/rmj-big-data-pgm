package com.rmj.challenge.one.dpirules

import org.scalatest.FlatSpec
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import java.text.SimpleDateFormat
import java.util.Date
import com.rmj.challenge.one.dpirules.DpiRuleProcessor._
import com.rmj.challenge.one.model._

/**
 * Desc: This is the test class for DPI rules to test the DpiRulePRocessor class.
 */
class DpiRuleProcessorTest extends FlatSpec with BeforeAndAfter {

  val input = "usage_start,0,0,2017-03-25 23:44:51 +0400,2017-03-25 23:44:59 +0400,tfake12-SHD-32K-Offline-2.telecomComp,d9a45e5509a74b80,-1896530231,-1916638914,-1490190653,Mobile,test12345,1,42402,2.48.0.5,0124F420F03CC4F7,355355082097270,iPhone 7 Plus (A1784),Apple,GSM;35377908,iOS,appleimessage,appleimessage,RealTimeCommunication,1148,7908,9056,1,137,,,,,,,,,"

  val telcoObj = TelcoData(input)

  val listOfTelcoObjects = List(telcoObj)

  "insertionDate" should "return current date in yyyy-mm-dd format" in {

    val sdf = new SimpleDateFormat("yyyy-MM-dd")
    assert(insertionDate === sdf.format(new Date()))

  }

  "totalTime" should "return difference between (min(StartTime), max(EndTime))" in {

    assert(totalTime(listOfTelcoObjects) == "0 Days 0 Hours 0 Minutes 8 Seconds")

  }
  "totalCount" should "return Count of transactions" in {

    assert(totalCount(listOfTelcoObjects) == 1)

  }
  "totalBytes" should "retrun Sum(TotalBytes) for the subscriberID" in {

    assert(totalBytes(listOfTelcoObjects) == 7908)

  }

  "MostUsedServiceNameSecond" should "retrun MostUsedServiceName second" in {

    assert(MostUsedServiceNameSecond(listOfTelcoObjects) == "")

  }

  "MostUsedServiceNameFirst" should "retrun MostUsedServiceName first" in {

    assert(MostUsedServiceNameFirst(listOfTelcoObjects)==="appleimessage")

  }

}