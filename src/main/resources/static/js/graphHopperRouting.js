L.Routing.control({
    waypoints: [
        L.latLng(55.7422, 37.5719),
        L.latLng(55.687086, 37.529789)
    ],
    lineOptions: {
        styles: [{color: 'green', opacity: 1, weight: 4}]
    },
    router: L.Routing.graphHopper('585def41-7ae7-4420-a2b3-b71c919bc166'),
    routeWhileDragging: true
}).addTo(myDeliveryServiceMap);

L.Routing.control({
    waypoints: [
        L.latLng(55.684758, 37.738521),
        L.latLng(55.826479, 37.487208),
        L.latLng(55.694843, 37.435023),
        L.latLng(55.790139, 37.814052)
    ],
    lineOptions: {
        styles: [{color: 'blue', opacity: 1, weight: 4}]
    },
    router: L.Routing.graphHopper('585def41-7ae7-4420-a2b3-b71c919bc166'),
    routeWhileDragging: true
}).addTo(myDeliveryServiceMap);

L.Routing.control({
    waypoints: [
        L.latLng(55.833436, 37.715175),
        L.latLng(55.782392, 37.614924),
        L.latLng(55.642063, 37.656123)
    ],
    lineOptions: {
        styles: [{color: 'red', opacity: 1, weight: 4}]
    },
    router: L.Routing.graphHopper('585def41-7ae7-4420-a2b3-b71c919bc166'),
    routeWhileDragging: true
}).addTo(myDeliveryServiceMap);