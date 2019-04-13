package com.rmj.challenge.one.model

/**
 * Desc: This is companion object based model class having all 36 input fields from Input file.
 * This input line has different fields all mapped to this model class.
 *
 */
class TelcoData(
  val recordType:          String,
  val recordStatus:        String,
  val recordNumber:        String,
  val startTime:           String,
  val endTime:             String,
  val pTSHostname:         String,
  val acctSessionId:       String,
  val subscriberId:        String,
  val imsi:                String,
  val subscriberIPAddress: String,
  val networkType:         String,
  val apn:                 String,
  val ratType:             String,
  val sGSNMCCMNC:          String,
  val sGSNIP:              String,
  val cell:                String,
  val imei:                String,
  val accessDevice:        String,
  val tethered:            String,
  val serviceName:         String,
  val applicationType:     String,
  val transmittedBytes:    String,
  val receivedBytes:       String,
  val totalBytes:          String,
  val subLatency:          String,
  val intLatency:          String,
  val attribute1:          String,
  val attribute2:          String,
  val attribute3:          String,
  val attribute4:          String,
  val attribute5:          String,
  val attribute6:          String,
  val attribute7:          String,
  val attribute8:          String,
  val attribute9:          String,
  val attribute10:         String) extends Product with Serializable {

  /**
   * This method is to implement the abstract methods of Product
   */

  def productElement(x$1: Int): Any = x$1 match {
    case 0  => recordType
    case 1  => recordStatus
    case 2  => recordNumber
    case 3  => startTime
    case 4  => endTime
    case 5  => pTSHostname
    case 6  => acctSessionId
    case 7  => subscriberId
    case 8  => imsi
    case 9  => subscriberIPAddress
    case 10 => networkType
    case 11 => apn
    case 12 => ratType
    case 13 => sGSNMCCMNC
    case 14 => sGSNIP
    case 15 => cell
    case 16 => imei
    case 17 => accessDevice
    case 18 => tethered
    case 19 => serviceName
    case 20 => applicationType
    case 21 => transmittedBytes
    case 22 => receivedBytes
    case 23 => totalBytes
    case 24 => subLatency
    case 25 => intLatency
    case 26 => attribute1
    case 27 => attribute2
    case 28 => attribute3
    case 29 => attribute4
    case 30 => attribute5
    case 31 => attribute6
    case 32 => attribute7
    case 33 => attribute8
    case 34 => attribute9
    case 35 => attribute10
    case _  => throw new IndexOutOfBoundsException(x$1.toString())
  }

  /**
   * This method is to implement the abstract methods of Product
   */
  def productArity: Int = 36

  /**
   * This method is to implement the abstract methods of Product
   */
  def canEqual(other: Any): Boolean = other.isInstanceOf[TelcoData]
  /**
   * This method is string representation of TelcoData object.
   */
  override def toString(): String = scala.runtime.ScalaRunTime._toString(this)

}

/**
 *
 * Desc : This is companion object which returns the TelcoData object after mapping all input fields to
 * model class this will return TelcoData model object based on input record's number of fields in comma separated.
 *
 */

object TelcoData {

  def apply(inRecord: String) = {

    val regex = ","

    inRecord.split(regex, -1).length match {
      case 38 => {
        new TelcoData(
          inRecord.split(regex, -1)(0),
          inRecord.split(regex, -1)(1),
          inRecord.split(regex, -1)(2),
          inRecord.split(regex, -1)(3),
          inRecord.split(regex, -1)(4),
          inRecord.split(regex, -1)(5),
          inRecord.split(regex, -1)(6),
          inRecord.split(regex, -1)(7),
          inRecord.split(regex, -1)(8),
          inRecord.split(regex, -1)(9),
          inRecord.split(regex, -1)(10),
          inRecord.split(regex, -1)(11),
          inRecord.split(regex, -1)(12),
          inRecord.split(regex, -1)(13),
          inRecord.split(regex, -1)(14),
          inRecord.split(regex, -1)(15),
          inRecord.split(regex, -1)(16),
          inRecord.split(regex, -1)(17) + inRecord.split(regex, -1)(18) + inRecord.split(regex, -1)(19),
          inRecord.split(regex, -1)(20),
          inRecord.split(regex, -1)(21),
          inRecord.split(regex, -1)(22),
          inRecord.split(regex, -1)(23),
          inRecord.split(regex, -1)(24),
          inRecord.split(regex, -1)(25),
          inRecord.split(regex, -1)(26),
          inRecord.split(regex, -1)(27),
          inRecord.split(regex, -1)(28),
          inRecord.split(regex, -1)(29),
          inRecord.split(regex, -1)(30),
          inRecord.split(regex, -1)(31),
          inRecord.split(regex, -1)(32),
          inRecord.split(regex, -1)(33),
          inRecord.split(regex, -1)(34),
          inRecord.split(regex, -1)(35),
          inRecord.split(regex, -1)(36),
          inRecord.split(regex, -1)(37))

      }
      case 39 => {
        new TelcoData(
          inRecord.split(regex, -1)(0),
          inRecord.split(regex, -1)(1),
          inRecord.split(regex, -1)(2),
          inRecord.split(regex, -1)(3),
          inRecord.split(regex, -1)(4),
          inRecord.split(regex, -1)(5),
          inRecord.split(regex, -1)(6),
          inRecord.split(regex, -1)(7),
          inRecord.split(regex, -1)(8),
          inRecord.split(regex, -1)(9),
          inRecord.split(regex, -1)(10),
          inRecord.split(regex, -1)(11),
          inRecord.split(regex, -1)(12),
          inRecord.split(regex, -1)(13),
          inRecord.split(regex, -1)(14),
          inRecord.split(regex, -1)(15),
          inRecord.split(regex, -1)(16),
          inRecord.split(regex, -1)(17) + inRecord.split(regex, -1)(18) + inRecord.split(regex, -1)(19) + inRecord.split(regex, -1)(20),
          inRecord.split(regex, -1)(21),
          inRecord.split(regex, -1)(22),
          inRecord.split(regex, -1)(23),
          inRecord.split(regex, -1)(24),
          inRecord.split(regex, -1)(25),
          inRecord.split(regex, -1)(26),
          inRecord.split(regex, -1)(27),
          inRecord.split(regex, -1)(28),
          inRecord.split(regex, -1)(29),
          inRecord.split(regex, -1)(30),
          inRecord.split(regex, -1)(31),
          inRecord.split(regex, -1)(32),
          inRecord.split(regex, -1)(33),
          inRecord.split(regex, -1)(34),
          inRecord.split(regex, -1)(35),
          inRecord.split(regex, -1)(36),
          inRecord.split(regex, -1)(37),
          inRecord.split(regex, -1)(38))

      }
      case _ => {
        new TelcoData(
          inRecord.split(regex, -1)(0),
          inRecord.split(regex, -1)(1),
          inRecord.split(regex, -1)(2),
          inRecord.split(regex, -1)(3),
          inRecord.split(regex, -1)(4),
          inRecord.split(regex, -1)(5),
          inRecord.split(regex, -1)(6),
          inRecord.split(regex, -1)(7),
          inRecord.split(regex, -1)(8),
          inRecord.split(regex, -1)(9),
          inRecord.split(regex, -1)(10),
          inRecord.split(regex, -1)(11),
          inRecord.split(regex, -1)(12),
          inRecord.split(regex, -1)(13),
          inRecord.split(regex, -1)(14),
          inRecord.split(regex, -1)(15),
          inRecord.split(regex, -1)(16),
          inRecord.split(regex, -1)(17),
          inRecord.split(regex, -1)(18),
          inRecord.split(regex, -1)(19),
          inRecord.split(regex, -1)(20),
          inRecord.split(regex, -1)(21),
          inRecord.split(regex, -1)(22),
          inRecord.split(regex, -1)(23),
          inRecord.split(regex, -1)(24),
          inRecord.split(regex, -1)(25),
          inRecord.split(regex, -1)(26),
          inRecord.split(regex, -1)(27),
          inRecord.split(regex, -1)(28),
          inRecord.split(regex, -1)(29),
          inRecord.split(regex, -1)(30),
          inRecord.split(regex, -1)(31),
          inRecord.split(regex, -1)(32),
          inRecord.split(regex, -1)(33),
          inRecord.split(regex, -1)(34),
          inRecord.split(regex, -1)(35))

      }
    }

  }
}



