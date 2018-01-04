package com.fullsample.tool

import java.text.SimpleDateFormat
import java.util.Date

object implicits {
  
  val simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")

  implicit class DateUtils(dateStr: String) {

    def toDate: Date = simpleDateFormat.parse(dateStr)
  }

}
