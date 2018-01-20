package com.fullsample.common

import java.util.concurrent.LinkedBlockingQueue

trait Producer[T] {

  private[this] val blockingQueue = new LinkedBlockingQueue[T]()

  def add(item: T): Boolean = blockingQueue.add(item)

  def size(): Int = blockingQueue.size()

  /**
    * 该方法取不到值的时候会阻塞
    *
    * @return
    */
  def get(): T = {
    println("consumer queue instance:" + this.blockingQueue.toString)
    blockingQueue.take()
  }

}
