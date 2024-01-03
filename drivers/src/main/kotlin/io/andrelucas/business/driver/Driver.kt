package io.andrelucas.business.driver

import io.andrelucas.business.region.DeliveryRegion
import io.andrelucas.business.vehicle.VehicleType
import java.util.UUID

data class Driver(val id: UUID = UUID.randomUUID(), val name: String, val vehicleType: VehicleType, val deliveryRegion: DeliveryRegion, val status: DriverStatus = DriverStatus.AVAILABLE)