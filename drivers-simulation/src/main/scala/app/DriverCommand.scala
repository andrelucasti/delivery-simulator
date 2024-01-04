package io.andrelucas
package app

import business.driver.DriverDTO

trait DriverCommand 
  case class CreateDriverCommand(driverDTO: DriverDTO) extends DriverCommand

 
  
