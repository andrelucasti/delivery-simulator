package io.andrelucas
package business.driver

class CreateDriver(private val driverRepository: DriverRepository) {
  def execute(driveDTO: DriverDTO): Unit = {
    val driver = Driver.create(driveDTO.name, driveDTO.modal, driveDTO.deliveryRegion)
    driverRepository.save(driver)
  }
}