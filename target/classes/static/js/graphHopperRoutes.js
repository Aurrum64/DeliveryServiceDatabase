L.Routing.control({
    waypoints: [
        L.latLng(55.79163, 37.63453),
        L.latLng(55.84988, 37.56672)
    ],
    router: L.Routing.graphHopper('585def41-7ae7-4420-a2b3-b71c919bc166'),
    routeWhileDragging: true
}).addTo(myDeliveryServiceMap);