package io.andrelucas
package breakingactors.route

import io.andrelucas.breakingactors.route.RouteStatus.ALLOCATED

import java.util.UUID

class Route(id: UUID,
            pickupPoint: Geolocation,
            deliveryPoint: Geolocation,
            routeStatus: RouteStatus,
            driverId: UUID = null) {

  def getId: UUID = id
  def getDriverId: UUID = driverId
  def getPickupPoint: Geolocation = pickupPoint
  def getDeliveryPoint: Geolocation = deliveryPoint
  def getStatus: RouteStatus = routeStatus
}

object Route {
  def create(pickupPoint: Geolocation, deliveryPoint: Geolocation): Route =
    this(UUID.randomUUID(), pickupPoint, deliveryPoint, RouteStatus.AVAILABLE)

  def restore(route: Route): Route =
    new Route(route.getId, route.getPickupPoint, route.getDeliveryPoint, route.getStatus, route.getDriverId)

  def allocate(driverId: UUID, route: Route): Route =
    new Route(route.getId, route.getPickupPoint, route.getDeliveryPoint, ALLOCATED, driverId)

}
