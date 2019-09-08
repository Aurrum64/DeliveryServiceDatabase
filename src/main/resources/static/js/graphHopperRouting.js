let routesLayerGroup = L.layerGroup().addTo(myDeliveryServiceMap);
let routes = [];
let line;
let polyline;

$(document).ready((function () {
    $("#buildRoute").click(function () {

        buildRoute();
    });
}));

function buildRoute() {
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
            routes[i] = L.Routing.control(
                {
                    waypoints: [
                        L.latLng(couriersMarkers[i]._latlng),
                        L.latLng(deliveryMarkers[i]._latlng)
                    ],
                    lineOptions: {
                        styles: [{color: 'green', opacity: 5, weight: 0}]
                    },
                    createMarker: function () {
                        return null;
                    },
                    show: false,
                    router: L.Routing.graphHopper('585def41-7ae7-4420-a2b3-b71c919bc166'),
                    routeWhileDragging: true
                });
            routes[i].addTo(myDeliveryServiceMap);
            routes[i].on('routesfound', function (e) {
                /*line = L.Routing.line(e.routes[0]).addTo(routesLayerGroup);*/
                polyline = L.polyline(e.routes[0].coordinates, {color: 'green', weight: 3}).addTo(routesLayerGroup);
            });
        }
    }
}


$(document).ready((function () {
    $("#move").click(function () {
        let i = 0;
        let howManyTimes = polyline._latlngs.length;

        move();

        function move() {
            routesLayerGroup.clearLayers();
            let moveRoute = polyline._latlngs.slice(i, polyline._latlngs.length);
            let courierCoordinates = polyline._latlngs[i];
            console.log(moveRoute);
            console.log(courierCoordinates);
            sendMovingCoordinates(courierCoordinates);
            /*polyline = L.polyline(moveRoute, {color: 'green', weight: 3}).addTo(routesLayerGroup);*/
            setCouriersMarkers();
            i++;
            if (i === polyline._latlngs.length) {
                changeDeliveryStatus(courierCoordinates);
            }
            if (i < howManyTimes) {
                setTimeout(move, 400);
            }
        }
    });
}));


function sendMovingCoordinates(courierCoordinate) {
    $.ajax({
        type: "POST",
        url: "movingCourierCoordinates",
        data: JSON.stringify(courierCoordinate),
        contentType: 'application/json',
        success: function (data) {
            console.log(data);
        }
    });
}

function changeDeliveryStatus(courierCoordinate) {
    $.ajax({
        type: "POST",
        url: "changeDeliveryStatus",
        data: JSON.stringify(courierCoordinate),
        contentType: 'application/json',
        success: function (data) {
            console.log(data);
        }
    });
}


