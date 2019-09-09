let routesLayerGroup = L.layerGroup().addTo(myDeliveryServiceMap);
let routes = [];
let solutionsInfos = [];
let polylines = [];

$(document).ready((function () {
    $("#buildRoute").click(function () {

        buildRoute();
    });
}));

function buildRoute() {
    if (couriersMarkers[0] === undefined && notDeliveredMarkers[0] === undefined) {
        alert("Please, add courier with coordinates to the system first " +
            "and add courier's markers to the map!\n" +
            "Please, add order details with address to the system first " +
            "and add delivery markers to the map!");
    } else if (couriersMarkers[0] === undefined) {
        alert("Please, add courier with coordinates to the system first " +
            "and add courier marker to the map!");
    } else if (notDeliveredMarkers[0] === undefined) {
        alert("Please, add order details with address to the system first " +
            "and add delivery marker to the map!");
    } else {
        for (let i = 0; i < couriersMarkers.length; i++) {
            routes[i] = L.Routing.control(
                {
                    waypoints: [
                        L.latLng(couriersMarkers[i]._latlng),
                        L.latLng(notDeliveredMarkers[i]._latlng)
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
                solutionsInfos[i] = L.Routing.line(e.routes[0]);
                polylines[i] = L.polyline(e.routes[0].coordinates, {color: 'red', weight: 3}).addTo(routesLayerGroup);
            });
        }
    }
}


$(document).ready((function () {
    $("#move").click(function () {
        for (let i = 0; i < polylines.length; i++) {
            let j = 0;
            let howManyTimes = polylines[i]._latlngs.length;

            move();

            function move() {
                routesLayerGroup.clearLayers();
                let moveRoute = polylines[i]._latlngs.slice(j, polylines[i]._latlngs.length);
                let courierCoordinates = polylines[i]._latlngs[j];
                let currentCourierInfo = JSON.stringify({
                    lat: polylines[i]._latlngs[j].lat,
                    lng: polylines[i]._latlngs[j].lng,
                    courierId: couriersInfos[i].courierId
                });
                sendMovingCoordinates(currentCourierInfo);
                /*polyline = L.polyline(moveRoute, {color: 'green', weight: 3}).addTo(routesLayerGroup);*/
                setCouriersMarkers();
                j++;
                if (j === polylines[i]._latlngs.length) {
                    changeDeliveryStatus(courierCoordinates);
                }
                if (j < howManyTimes) {
                    setTimeout(move, 400);
                }
            }
        }
    });
}));


function sendMovingCoordinates(currentCourierInfo) {
    $.ajax({
        type: "POST",
        url: "movingCourierCoordinates",
        data: currentCourierInfo,
        contentType: 'application/json',
        success: function (data) {
            if (data.status === 'OK') {
                console.log('Courier moved!');
            } else {
                console.log('Courier moving failed!: ' + data.status + ', ' + data.errorMessage);
            }
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
            if (data.status === 'OK') {
                console.log('Delivery status changed!');
            } else {
                console.log('Failed to change delivery status!: ' + data.status + ', ' + data.errorMessage);
            }
        }
    });
}


