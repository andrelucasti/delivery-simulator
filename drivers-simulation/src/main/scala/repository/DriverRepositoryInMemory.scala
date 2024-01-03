package io.andrelucas
package repository
import business.driver.{Driver, DriverRepository}

import scala.collection.mutable.ListBuffer
object DriverRepositoryInMemory extends DriverRepository {

  private val memory: ListBuffer[Driver] = ListBuffer()
  override def save(driver: Driver): Unit = memory += driver

  override def findAll(): List[Driver] = memory.toList
}
