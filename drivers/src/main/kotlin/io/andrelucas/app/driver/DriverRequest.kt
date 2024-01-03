package io.andrelucas.app.driver

import io.andrelucas.business.region.DeliveryRegion
import io.andrelucas.business.vehicle.VehicleType

data class DriverRequest(val name: String,
                         val vehicle: VehicleType,
                         val deliveryRegion: DeliveryRegion)