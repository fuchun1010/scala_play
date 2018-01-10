package com.fullsample.app

import com.fullsample.domain.DirToolKit
import org.apache.spark.sql.{DataFrame, SnappySession, SparkSession}

import scala.util.{Failure, Success, Try}


object Main extends App with DirToolKit {

  def createSnappySession: Try[SnappySession] = {
    val sparkSession = SparkSession.builder()
      .appName("Snappy")
      .master("local")
      .config("backup", snappyDir())
      .getOrCreate()
    Try(new SnappySession(sparkSession.sparkContext))
  }

  def loadDataFrame(path: String, dataType: String = "parquet")(implicit session: SnappySession): DataFrame = {
    dataType.toLowerCase match {
      case "parquet" => session.read.parquet(path)
      case _ => throw new Exception("not supported")
    }
  }

  def measureCost(block: => Unit): Unit = {
    val start = System.currentTimeMillis()
    block
    val end = System.currentTimeMillis()
    println("cost time:" + (end - start) / 1000 + "s")
  }

  measureCost {
    createSnappySession match {
      case Success(snappySession) => {
        val parquetPath = dataDir("d-3000000")
        implicit val session = snappySession
        val df = loadDataFrame(parquetPath)
        val columns = List("edu", "city", "bloodtype", "cost")
        val groupColumns = columns match {
          case List(a, b, c, _*) => List(a, b, c)
          case _ => Nil
        }
        //df.schema.fields.foreach(field => println(field.name))
        val count = df.select(columns.head, columns.tail: _*).filter(row => row.getAs[String]("bloodtype").equalsIgnoreCase("A")).groupBy(groupColumns.head, groupColumns.tail: _*).count().count()
        println("count = " + count)
      }
      case Failure(e) => {
        e.printStackTrace()
      }
    }

  }


}

