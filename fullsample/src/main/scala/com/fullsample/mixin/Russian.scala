package com.fullsample.mixin

class Russian extends Solider with AkBullets with AkGun {
  override def fight(): Unit = {
    println("ruassia soliders :")
    this.takeGun()
    this.takeBullets()
  }
}

object Russian {
  def apply(): Russian = new Russian()
}
