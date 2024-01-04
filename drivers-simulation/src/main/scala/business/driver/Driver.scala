package io.andrelucas
package business.driver

import java.util.UUID

case class Driver(id: UUID, name: String, modal: String, deliveryRegion: String)

//Companion Pattern
object Driver {
  def create(name: String, modal: String, deliveryRegion: String): Driver = this(UUID.randomUUID(), name, modal, deliveryRegion)
}
