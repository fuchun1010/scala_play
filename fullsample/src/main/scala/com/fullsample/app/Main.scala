package com.fullsample.app

import com.fullsample.domain.DirToolKit
import org.apache.spark.sql.{DataFrame, SparkSession}

import scala.util.{Failure, Success, Try}


object Main extends App with DirToolKit {

  def createSparkSession: Try[SparkSession] = {
    val session = SparkSession.builder().appName("SparkSession").master("local[*]").getOrCreate()
    Try(session)
  }

  def loadDataFrame(path: String, dataType: String = "parquet")(implicit session: SparkSession): DataFrame = {
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

  def executeDf(): Unit = {
    measureCost {
      createSparkSession match {
        case Success(snappySession) => {
          val parquetPath = dataDir("d-3000000")
          implicit val session = snappySession
          val df = loadDataFrame(parquetPath)
          val columns = List("edu", "city", "bloodtype", "cost")
          val groupColumns = columns match {
            case List(a, b, c, _*) => List(a, b, c)
            case _ => Nil
          }
          val count = df.select(columns.head, columns.tail: _*).filter(row => row.getAs[String]("bloodtype").equalsIgnoreCase("A")).groupBy(groupColumns.head, groupColumns.tail: _*).count().count()
          println("count = " + count)
        }
        case Failure(e) => {
          e.printStackTrace()
        }
      }

    }
  }

  def cpuCores(): Int = Runtime.getRuntime.availableProcessors()

  val session = createSparkSession


}

