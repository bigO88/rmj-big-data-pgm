package com.rmj.challenge.one.model

import org.scalatest.FlatSpec
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import com.rmj.challenge.one.model._

/**
 * Desc: This is the test class to test the TelcoData class.
 */
class TelcoDataTest extends FlatSpec with BeforeAndAfter {

  "apply " should "return TelcoData object representation of input data" in {

    val input = "usage_start,0,0,2017-03-25 23:44:51 +0400,2017-03-25 23:44:59 +0400,tfake12-SHD-32K-Offline-2.telecomComp,d9a45e5509a74b80,-1896530231,-1916638914,-1490190653,Mobile,test12345,1,42402,2.48.0.5,0124F420F03CC4F7,355355082097270,iPhone 7 Plus (A1784),Apple,GSM;35377908,iOS,false,appleimessage,RealTimeCommunication,1148,7908,9056,1,137,,,,,,,,,"

    val telcoObj = TelcoData(input)

    assert(telcoObj.subscriberId === "-1896530231")
    assert(telcoObj.startTime === "2017-03-25 23:44:51 +0400")
    assert(telcoObj.endTime === "2017-03-25 23:44:59 +0400")
    assert(telcoObj.productArity == 36)
    assert(telcoObj.productElement(7) === "-1896530231")
    assert(telcoObj.productElement(3) === "2017-03-25 23:44:51 +0400")
    assert(telcoObj.productElement(4) === "2017-03-25 23:44:59 +0400")
    assert(!telcoObj.canEqual(""))
  }

}