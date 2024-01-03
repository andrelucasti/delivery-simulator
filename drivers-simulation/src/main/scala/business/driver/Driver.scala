package io.andrelucas
package business.driver

import java.util.UUID

class Driver(id: UUID, name: String, modal: String, deliveryRegion: String){
  def getName: String = name
  def getModal: String = modal
  def getDeliveryRegion: String = deliveryRegion
  
  def getId: UUID = id
}

//Companion Pattern
object Driver {
  def apply(name: String, modal: String, deliveryRegion: String): Driver =
    new Driver(UUID.randomUUID(), name, modal, deliveryRegion)

  def create(name: String, modal: String, deliveryRegion: String): Driver = this(name, modal, deliveryRegion)
}
