package com.fullsample.tool

import java.text.SimpleDateFormat
import java.util.Date

object implicits {

  implicit class DateUtils(dateStr: String) {

    def toDate: Date = {
      val simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
      simpleDateFormat.parse(dateStr)
    }
  }

}
