let routesLayerGroup = L.layerGroup().addTo(myDeliveryServiceMap);
let solutionsInfos = [];
let orderDeliveredByCourier;
let routesToFirstAddress = [];
let firstAddressPolyline;
let secondAddressPolyline;
let shortestDistance;
let shortestRouteToFirstAddress;
let routeToSecondAddress;

unblockRoute();

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
            routesToFirstAddress[i].route.addTo(myDeliveryServiceMap);
        }
        solutionsInfos.length = 0;
        for (let i = 0; i < routesToFirstAddress.length; i++) {

            routesToFirstAddress[i].route.on('routesfound', function (e) {
                solutionsInfos[i] = {
                    route: L.Routing.line(e.routes[0])
                };
                solutionsInfos[i]["order"] = {
                    orderDetailsId: routesToFirstAddress[i].order.order.orderDetailsId
                };
            });
        }
        setTimeout(function () {
            for (let i = 0; i < solutionsInfos.length; i++) {
                if (i === 0) {
                    shortestDistance = solutionsInfos[0].route._route.summary.totalDistance;
                    shortestRouteToFirstAddress = solutionsInfos[0];
                } else {
                    if (shortestDistance > solutionsInfos[i].route._route.summary.totalDistance) {
                        shortestDistance = solutionsInfos[i].route._route.summary.totalDistance;
                        shortestRouteToFirstAddress = solutionsInfos[i];
                    }
                }
            }
        }, (500));
        setTimeout(function () {
            blockSelectedRoute(shortestRouteToFirstAddress.order.orderDetailsId);
            for (let i = 0; i < secondOrderPointMarkers.length; i++) {
                if (deliveryInfos[i].orderDetailsId === shortestRouteToFirstAddress.order.orderDetailsId) {
                    routeToSecondAddress = {
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
                    routeToSecondAddress["order"] = {
                        order: deliveryInfos[i]
                    };
                }
            }
            routeToSecondAddress.route.addTo(myDeliveryServiceMap);
        }, (1000));
        setTimeout(function () {
            firstAddressPolyline = {
                polyline: L.polyline(shortestRouteToFirstAddress.route._route.coordinates, {
                    color: 'red',
                    weight: 3
                }).addTo(routesLayerGroup)
            };
            firstAddressPolyline["order"] = {orderDetailsId: shortestRouteToFirstAddress.order.orderDetailsId};

            secondAddressPolyline = {
                polyline: L.polyline(routeToSecondAddress.route._selectedRoute.coordinates, {
                    color: 'red',
                    weight: 3
                }).addTo(routesLayerGroup)
            };
            secondAddressPolyline["order"] = {orderDetailsId: routeToSecondAddress.order.order.orderDetailsId};
        }, (1500));
    }
}

$(document).ready((function () {
    $("#move").click(function () {
            if (firstAddressPolyline === undefined || secondAddressPolyline === undefined) {
                alert("Сперва вы должны получить от системы ближайший к вам заказ!");
            } else {
                orderDeliveredByCourier = JSON.stringify(
                    {
                        courierId: couriersInfos[0].courierId,
                        orderDetailsId: firstAddressPolyline.order.orderDetailsId
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
                        orderId: firstAddressPolyline.order.orderDetailsId
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
            orderId: secondAddressPolyline.order.orderDetailsId
        });
        sendMovingCoordinates(currentCourierInfo);
        showCourierOnMap();
        j++;
        if (j === secondAddressPolyline.polyline._latlngs.length) {
            let currentOrderInfo = JSON.stringify({orderDetailsId: secondAddressPolyline.order.orderDetailsId});
            changeDeliveryStatus(currentOrderInfo);
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

function blockSelectedRoute(orderDetailsId) {

    let message = {
        orderDetailsId: orderDetailsId,
        courierId: couriersInfos[0].courierId
    };
    $.ajax({
        type: "POST",
        url: "blockSelectedRoute",
        data: JSON.stringify(message),
        contentType: 'application/json',
        success: function (data) {
            if (data.status === 'OK') {
                console.log('Route successfully blocked!');
            } else {
                console.log('Can\'t block route!: ' + data.status + ', ' + data.errorMessage);
            }
        }
    });
}

function unblockRoute() {

    $.ajax({
        type: "POST",
        url: "unblockRoute",
        contentType: 'application/json',
        success: function (data) {
            if (data.status === 'OK') {
                console.log('Route successfully unblocked!');
            } else {
                console.log('Can\'t unblock route!: ' + data.status + ', ' + data.errorMessage);
            }
        }
    });
}


