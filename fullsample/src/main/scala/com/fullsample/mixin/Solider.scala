package com.fullsample.mixin

trait Solider {
  self: Gun with Bullet =>

  def fight(): Unit
}
