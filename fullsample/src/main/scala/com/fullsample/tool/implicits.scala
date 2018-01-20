package com.fullsample.tool

import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

object implicits {

  val simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")

  implicit class DateUtils(dateStr: String) {

    def toDate: Date = simpleDateFormat.parse(dateStr)
  }

  implicit class FileUtils(filePath: String) {
    def toFile: File = new File(filePath)
  }

  implicit def functionBody2Runnable(fun: () => Unit): Runnable = new Runnable {
    override def run(): Unit = fun()
  }


}
