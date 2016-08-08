package com.eigenroute.testmavenrepo

import SomeTestConfigParams._

object Hello {
  def main(args: Array[String]): Unit = {
    println("Hello from com.eigenroute.testmavenrepo - 0.1-SNAPSHOT")
    println(s"1. $configParam1")
    println(s"2. $configParam2")
  }
}
