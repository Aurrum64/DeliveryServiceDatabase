let routesLayerGroup = L.layerGroup().addTo(myDeliveryServiceMap);
let routes = [];
let solutionsInfos = [];
let polylines = [];
let orderDeliveredByCourier;

$(document).ready((function () {
    $("#buildRoute").click(function () {

        buildRoute();
    });
}));

function buildRoute() {
    if (couriersMarkers[0] === undefined || firstOrderPointMarkers[0] === undefined ||
        secondOrderPointMarkers[0] === undefined) {
        alert("Нет курьеров, готовых принять заказ или отсутствуют активные заказы!");
    } else {
        optimizeRoutes();
        /*for (let i = 0; i < secondOrderPointMarkers.length; i++) {
            routes[i] = L.Routing.control(
                {
                    waypoints: [
                        L.latLng(couriersMarkers[0]._latlng),
                        L.latLng(firstOrderPointMarkers[i]._latlng),
                        L.latLng(secondOrderPointMarkers[i]._latlng)
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

                console.log(couriersInfos[0].courierId);
                console.log(deliveryInfos[i].orderDetailsId);

                orderDeliveredByCourier = JSON.stringify(
                    {
                        courierId: couriersInfos[0].courierId,
                        orderDetailsId: deliveryInfos[i].orderDetailsId
                    });
                assignCourierToOrder(orderDeliveredByCourier);
                setTimeout(function () {
                    showActiveOrdersListForLogisticPage();
                }, (300));
                solutionsInfos[i] = L.Routing.line(e.routes[0]);
                polylines[i] = L.polyline(e.routes[0].coordinates, {color: 'red', weight: 3}).addTo(routesLayerGroup);
            });
        }*/
    }
}

$(document).ready((function () {
    $("#move").click(function () {
        if (couriersMarkers[0] === undefined || firstOrderPointMarkers[0] === undefined ||
            secondOrderPointMarkers[0] === undefined) {
            alert("Please, build a route!");
        } else {
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
                    showCouriersList();
                    setCouriersMarkers();
                    j++;
                    if (j === polylines[i]._latlngs.length) {
                        let currentOrderInfo = JSON.stringify({orderDetailsId: deliveryInfos[i].orderDetailsId});
                        changeDeliveryStatus(currentOrderInfo);
                        hideCouriersMarkers();
                        setDeliveredMarkers();
                        showActiveOrdersListForLogisticPage();
                    }
                    if (j < howManyTimes) {
                        setTimeout(move, 400);
                    }
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

function changeDeliveryStatus(currentOrderInfo) {
    $.ajax({
        type: "POST",
        url: "changeDeliveryStatus",
        data: currentOrderInfo,
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

function assignCourierToOrder(orderDeliveredByCourier) {
    $.ajax({
        type: "POST",
        url: "assignCourierToOrder",
        data: orderDeliveredByCourier,
        contentType: 'application/json',
        success: function (data) {
            if (data.status === 'OK') {
                console.log('Courier assigned to order!');
            } else {
                console.log('Failed to assign courier to order!: ' + data.status + ', ' + data.errorMessage);
            }
        }
    });
}


