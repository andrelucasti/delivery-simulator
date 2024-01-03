package io.andrelucas
package business.driver

trait DriverRepository {
  def save(driver: Driver):Unit
  def findAll():List[Driver]
}
