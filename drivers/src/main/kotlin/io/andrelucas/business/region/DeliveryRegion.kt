package io.andrelucas.business.region

enum class DeliveryRegion(private val geoLocation: GeoLocation) {
    LISBON(GeoLocation(38.7166700, -9.1333300)),
    PORTO(GeoLocation(41.1496100, -8.6109900)),
    FARO(GeoLocation(37.0193700, -7.9322300))
}

data class GeoLocation(val latitude: Double, val longitude: Double)