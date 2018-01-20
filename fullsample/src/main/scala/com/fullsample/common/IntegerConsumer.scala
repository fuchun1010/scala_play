package com.fullsample.common

trait IntegerConsumer {
  self: Producer[Int] =>

  def cost(): Unit = {
    println(self.get())
  }
}
