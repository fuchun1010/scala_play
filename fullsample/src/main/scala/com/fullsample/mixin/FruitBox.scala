package com.fullsample.mixin

abstract class FruitBox[+T <: Fruit] {

  def fruit(): T

  def contains(that: Fruit): Boolean = fruit().name().equals(that.name())
}
