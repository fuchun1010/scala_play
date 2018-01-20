package com.fullsample.mixin

trait AkGun extends Gun {

  override def takeGun(): Unit = println("take Ak Gun")
}
