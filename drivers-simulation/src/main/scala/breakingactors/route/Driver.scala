package io.andrelucas
package breakingactors.route

import java.util.UUID

class Driver(id: UUID, name: String, modal: String, deliveryRegion: String, status: String){
  def getName: String = name
  def getModal: String = modal
  def getDeliveryRegion: String = deliveryRegion
  
  def getId: UUID = id

  def getStatus: String = status
}

//Companion Pattern
object Driver {
  def apply(name: String, modal: String, deliveryRegion: String): Driver =
    new Driver(UUID.randomUUID(), name, modal, deliveryRegion, "AVAILABLE")

  def create(name: String, modal: String, deliveryRegion: String): Driver = 
    this(name, modal, deliveryRegion)
  
  def inRoute(driver: Driver): Driver = 
    new Driver(driver.getId, driver.getName, driver.getModal, driver.getDeliveryRegion, "IN_ROUTE")
}
