let routesLayerGroup = L.layerGroup().addTo(myDeliveryServiceMap);
let routes = [];
let solutionsInfos = [];
let polylines = [];
let orderDeliveredByCourier;
let routesToFirstAddress = [];
let routesToSecondAddress = [];
let firstAddressPolyline;
let secondAddressPolyline;

$(document).ready((function () {
    $("#buildRoute").click(function () {

        buildRoute();
    });
}));

function buildRoute() {
    if (firstOrderPointMarkers[0] === undefined || secondOrderPointMarkers[0] === undefined) {
        alert("Сейчас нет ни одного заказа, который вы могли бы выполнить!");
    } else {
        for (let i = 0; i < firstOrderPointMarkers.length; i++) {
            routesToFirstAddress[i] = {
                route: L.Routing.control(
                    {
                        waypoints: [
                            L.latLng(couriersMarkers[0]._latlng),
                            L.latLng(firstOrderPointMarkers[i]._latlng)
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
                    })
            };
            routesToFirstAddress[i]["order"] = {
                order: deliveryInfos[i]
            };
        }
        for (let i = 0; i < secondOrderPointMarkers.length; i++) {
            routesToSecondAddress[i] = {
                route: L.Routing.control(
                    {
                        waypoints: [
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
                    })
            };
            routesToSecondAddress[i]["order"] = {
                order: deliveryInfos[i]
            };
        }
        routesToFirstAddress[0].route.addTo(myDeliveryServiceMap);
        routesToSecondAddress[0].route.addTo(myDeliveryServiceMap);

        routesToFirstAddress[0].route.on('routesfound', function (e) {

            solutionsInfos[0] = L.Routing.line(e.routes[0]);
            firstAddressPolyline = {
                polyline: L.polyline(e.routes[0].coordinates, {
                    color: 'red',
                    weight: 3
                }).addTo(routesLayerGroup)
            };
            firstAddressPolyline["order"] = {order: routesToFirstAddress[0].order.order};
            console.log(firstAddressPolyline);
        });
        routesToSecondAddress[0].route.on('routesfound', function (e) {

            solutionsInfos[0] = L.Routing.line(e.routes[0]);
            secondAddressPolyline = {
                polyline: L.polyline(e.routes[0].coordinates, {
                    color: 'red',
                    weight: 3
                }).addTo(routesLayerGroup)
            };
            secondAddressPolyline["order"] = {order: routesToSecondAddress[0].order.order};
            console.log(secondAddressPolyline);
        });
    }
}

$(document).ready((function () {
    $("#move").click(function () {
            if (routesToFirstAddress[0] === undefined || routesToSecondAddress[0] === undefined) {
                alert("Сперва вы должны получить от системы ближайший к вам заказ!");
            } else {
                orderDeliveredByCourier = JSON.stringify(
                    {
                        courierId: couriersInfos[0].courierId,
                        orderDetailsId: firstAddressPolyline.order.order.orderDetailsId
                    });
                assignCourierToOrder(orderDeliveredByCourier);

                let i = 0;
                let howManyTimesFirstAddress = firstAddressPolyline.polyline._latlngs.length;

                function moveToFirstAddress() {

                    routesLayerGroup.clearLayers();
                    let currentCourierInfo = JSON.stringify({
                        lat: firstAddressPolyline.polyline._latlngs[i].lat,
                        lng: firstAddressPolyline.polyline._latlngs[i].lng,
                        courierId: couriersInfos[0].courierId,
                        orderId: firstAddressPolyline.order.order.orderDetailsId
                    });
                    sendMovingCoordinates(currentCourierInfo);
                    showCourierOnMap();
                    i++;
                    if (i === firstAddressPolyline.polyline._latlngs.length) {
                        changeOrderPickedUpStatus(orderDeliveredByCourier);
                        moveToSecondAddress();
                    }
                    if (i < howManyTimesFirstAddress) {
                        setTimeout(moveToFirstAddress, 400);
                    }
                }

                moveToFirstAddress();
            }
        }
    );
}));

function moveToSecondAddress() {

    let j = 0;
    let howManyTimesSecondAddress = secondAddressPolyline.polyline._latlngs.length;

    function secondAddressMovement() {

        routesLayerGroup.clearLayers();
        let currentCourierInfo = JSON.stringify({
            lat: secondAddressPolyline.polyline._latlngs[j].lat,
            lng: secondAddressPolyline.polyline._latlngs[j].lng,
            courierId: couriersInfos[0].courierId,
            orderId: secondAddressPolyline.order.order.orderDetailsId
        });
        sendMovingCoordinates(currentCourierInfo);
        showCourierOnMap();
        j++;
        if (j === secondAddressPolyline.polyline._latlngs.length) {
            let currentOrderInfo = JSON.stringify({orderDetailsId: secondAddressPolyline.order.order.orderDetailsId});
            changeDeliveryStatus(currentOrderInfo);
            hideCouriersMarkers();
            /*setDeliveredMarkers();*/
        }
        if (j < howManyTimesSecondAddress) {
            setTimeout(secondAddressMovement, 400);
        }
    }

    secondAddressMovement();
}

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

function changeOrderPickedUpStatus(orderDeliveredByCourier) {
    $.ajax({
        type: "POST",
        url: "changeOrderPickedUpStatus",
        data: orderDeliveredByCourier,
        contentType: 'application/json',
        success: function (data) {
            if (data.status === 'OK') {
                console.log('Picked up status changed!');
            } else {
                console.log('Picked up status not changed!: ' + data.status + ', ' + data.errorMessage);
            }
        }
    });
}



