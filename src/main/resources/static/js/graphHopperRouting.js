$(document).ready((function () {
    $("#buildRoute").click(function () {
        if (couriersMarkers[0] === undefined && deliveryMarkers[0] === undefined) {
            alert("Please, add courier with coordinates to the system first " +
                "and add courier's markers to the map!\n" +
                "Please, add order details with address to the system first " +
                "and add delivery markers to the map!");
        } else if (couriersMarkers[0] === undefined) {
            alert("Please, add courier with coordinates to the system first " +
                "and add courier marker to the map!");
        } else if (deliveryMarkers[0] === undefined) {
            alert("Please, add order details with address to the system first " +
                "and add delivery marker to the map!");
        } else {
            for (let i = 0; i < couriersMarkers.length; i++) {
                L.Routing.control({
                    waypoints: [
                        L.latLng(couriersMarkers[i]._latlng),
                        L.latLng(deliveryMarkers[i]._latlng)
                    ],
                    lineOptions: {
                        styles: [{color: 'green', opacity: 1, weight: 4}]
                    },
                    router: L.Routing.graphHopper('585def41-7ae7-4420-a2b3-b71c919bc166'),
                    routeWhileDragging: true
                }).addTo(myDeliveryServiceMap);
            }
        }
    });
}));