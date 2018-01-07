package com.fullsample.domain

import scala.collection.mutable.ListBuffer

case class Node[T](id: String,
                   data: Option[T],
                   desc: Option[String],
                   children: Option[ListBuffer[Node[T]]]
                  ) {

  def isParentNode: Boolean = data match {
    case Some(_) => true
    case _ => false
  }

  def addChild(node: Node[T]): Unit = {
    children match {
      case Some(parent) => parent += node
      case _ => ()
    }
  }

  override def toString = {
    val sb = new StringBuilder
    sb.append(" id: ")
    sb.append(id)
    sb.append(" type: ")
    sb.append(data match {
      case Some(_) => "parent Node"
      case _ => "child node"
    })
    sb.append(desc match {
      case Some(desc) => s"""desc: ${desc}"""
      case _ => ""
    })

    sb.toString()
  }
}


object Node {
  def apply[T](id: String,
               desc: Option[String]
              ): Node[T] = new Node(id, Option.empty, desc, Option(new ListBuffer[Node[T]]))

  def apply[T](id: String,
               data: T,
               desc: Option[String]
              ): Node[T] = new Node(id, Option(data), desc, Option.empty)
}
