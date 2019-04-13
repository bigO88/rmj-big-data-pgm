package com.rmj.challenge.one.dpirules

import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit

import com.rmj.challenge.one.model.TelcoData
/**
 *  Desc : This Singleton object is used to perform all DPI rules on Telco Model data.
 *
 */
object DpiRuleProcessor {

  val eventType = "MOBILE"

  val macAddress = "N/A"

  val accessPoint = "N/A"

  def AccountNumber(x: TelcoData) = x.subscriberId

  /**
   * Desc : This Based on column ServiceName into the file “index 20”
   * for each SubscriberId get the most used service id.
   * Simply if customer have records as the below
   * (customer 1, whatsapp)
   * (customer 1, whatsapp)
   * (customer 1, whatsapp)
   * (customer 1, facebook_messenger)
   * (customer 1, facebook_messenger)
   * (customer 1, google)
   * then MostUsedServiceName_1 is whatapp
   *
   * @param: List of TelcoData for the the subscriberID
   * @return: MostUsedServiceName_1
   */
  def MostUsedServiceNameFirst(x: List[TelcoData]): String = {

    val serviceList = x.map(y => y.serviceName)

    serviceList.length match {
      case x if x == 0 => ""
      case x if x == 1 => serviceList.head
      case _           => serviceList.groupBy(x => x).map(x => (x._1, x._2.size)).maxBy(_._2)._1
    }

  }

  /**
   * Desc: This is Based on column service name into the file “index 20”
   * for each SubscriberId get the most used service id.
   * Simply if customer have records as the below
   * (customer 1, whatsapp)
   * (customer 1, whatsapp)
   * (customer 1, whatsapp)
   * (customer 1, facebook_messenger)
   * (customer 1, facebook_messenger)
   * (customer 1, google)
   * then MostUsedServiceName_2 is facebook_messenger
   *
   * @param: List of TelcoData for the the subscriberID
   * @return : MostUsedServiceName_2
   *
   */
  def MostUsedServiceNameSecond(x: List[TelcoData]): String = {

    val serviceList = x.map(y => y.serviceName)

    serviceList.length match {
      case x if x == 0 || x == 1 => ""
      case _ => {
        val serviceFirstMap = serviceList.groupBy(x => x).map(x => (x._1, x._2.size))
        val maxFirstKey = serviceFirstMap.maxBy(_._2)._1
        val serviceSecondMap = serviceFirstMap.filter(x => !(x._1.equals(maxFirstKey)))

        serviceSecondMap.size match {
          case x if x == 0 => ""
          case _           => serviceSecondMap.maxBy(_._2)._1
        }

      }
    }

  }

  /**
   * Desc: This is Sum(TotalBytes) for the subscriberID
   * @param:List of TelcoData for the the subscriberID
   * @return: Sum(TotalBytes)
   */
  def totalBytes(x: List[TelcoData]): Long = x.map(_.totalBytes.toLong).sum

  /**
   * Desc: This return Count of transactions.
   * @param:List of TelcoData for the the subscriberID
   * @return: Count of transactions
   *
   */
  def totalCount(x: List[TelcoData]): Int = x.size

  /**
   * Desc: This method is to calculate Difference between (min(StartTime), max(EndTime)) for
   *  the subscriberID
   *  @param: List of TelcoData for the  subscriberID
   *  @return: Different between (min(StartTime), max(EndTime))
   */
  def totalTime(x: List[TelcoData]): String = {

    val sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z")
    sdf.setTimeZone(java.util.TimeZone.getTimeZone("GST"))
    val startTimeMin = x.map(x => sdf.parse(x.startTime.trim())).min
    val endTimeMax = x.map(x => sdf.parse(x.endTime.trim())).max
    val duration = endTimeMax.getTime() - startTimeMin.getTime()

    val diffInSeconds = duration / 1000 % 60
    val diffInMinutes = duration / (60 * 1000) % 60
    val diffInHours = duration / (60 * 60 * 1000) % 24
    val diffInDays = duration / (24 * 60 * 60 * 1000)

    diffInDays + " Days " + diffInHours + " Hours " + diffInMinutes + " Minutes " + diffInSeconds + " Seconds"

  }

  val sdf = new SimpleDateFormat("yyyy-MM-dd")

  /**
   * Desc: Current_Date (yyyy-mm- dd)
   */
  val insertionDate = sdf.format(new Date())

  /**
   * Desc : This method calculate the output for the  subscriberID as mentioned in DPI rules.
   * @param: List of TelcoData for the  subscriberID
   * @return: String with delimited by , having all fields in DPI rules.
   *
   */
  def outputRecord(x: List[TelcoData]): String = {

    val delim = ","
    AccountNumber(x.head) + delim + MostUsedServiceNameFirst(x) + delim + MostUsedServiceNameSecond(x) + delim + eventType + delim + x.head.imei + delim + macAddress + delim + accessPoint + delim + totalBytes(x) + delim + totalCount(x) + delim + totalTime(x) + delim + insertionDate
  }

}