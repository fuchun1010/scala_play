package com.fullsample.app

import java.util.concurrent.CountDownLatch

import com.fullsample.domain.DirToolKit


object Main extends App with DirToolKit {


  def start(block: (Int) => Unit)(implicit mockNum: Int): Unit = {
    val countDownLatch = new CountDownLatch(mockNum)

    implicit def fun2Runnable(fun: () => Unit): Runnable = new Runnable {
      override def run(): Unit = fun()
    }

    for (i <- 1 to mockNum) {
      new Thread(() => {
        countDownLatch.await()
        block(i)
      }).start()
    }

    for (i <- 1 to mockNum) {
      countDownLatch.countDown()
    }
  }

  implicit val mockNum = 100
  start {
    println(_)
  }

}

