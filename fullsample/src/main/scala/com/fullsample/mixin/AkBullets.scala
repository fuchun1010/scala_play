package com.fullsample.mixin

trait AkBullets extends Bullet {
  override def takeBullets(): Unit = println("take ak serial bullets")
}
