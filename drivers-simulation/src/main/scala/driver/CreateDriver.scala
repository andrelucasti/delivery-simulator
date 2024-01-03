package io.andrelucas
package driver

object CreateDriver {
  def execute(driveDTO: DriveDTO): Unit = {
    val driver = Driver.create(driveDTO.name, driveDTO.modal, driveDTO.deliveryRegion)
    println(s"Creating new Driver - ID: ${driver.getId}, Modal: ${driver.getModal}")
  }
}
