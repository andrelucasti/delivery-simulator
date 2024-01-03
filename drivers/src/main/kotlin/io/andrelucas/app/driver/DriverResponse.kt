package io.andrelucas.app.driver

import io.andrelucas.business.driver.DriverStatus
import io.andrelucas.business.region.DeliveryRegion
import io.andrelucas.business.vehicle.VehicleType
import java.util.UUID

data class DriverResponse(val id: UUID,
                          val name: String,
                          val vehicle: VehicleType,
                          val deliveryRegion: DeliveryRegion,
                          val status: DriverStatus) {
}