let couriersMarkersLayerGroup = L.layerGroup().addTo(myDeliveryServiceMap);
let couriersMarkers = [];
let couriersInfos = [];

$(document).ready((function () {
    $("#couriersCoordinates").click(function () {

        setCouriersMarkers();
    });
}));

function setCouriersMarkers() {
    $.ajax({
        url: "/couriersList",
        type: "GET",
        dataType: 'json',
        success: function (data) {
            couriersMarkersLayerGroup.clearLayers();
            if (data.result[0] === undefined) {
                alert("Please, add courier with coordinates to the system first!");
            } else {
                for (let i = 0; i < data.result.length; i++) {
                    let latitude = data.result[i].latitude;
                    let longitude = data.result[i].longitude;
                    couriersInfos[i] = data.result[i];
                    let courierMarker = L.marker([latitude, longitude],
                        {icon: cargo}).addTo(couriersMarkersLayerGroup);
                    courierMarker.bindPopup("Курьер №" + [i + 1] + "<br>" +
                        data.result[i].firstName + " " + data.result[i].lastName);
                    couriersMarkers[i] = courierMarker;
                }
            }
        }
    })
}

let firstOrderPointMarkersLayerGroup = L.layerGroup().addTo(myDeliveryServiceMap);
let secondOrderPointMarkersLayerGroup = L.layerGroup().addTo(myDeliveryServiceMap);
let firstOrderPointMarkers = [];
let secondOrderPointMarkers = [];
let deliveryInfos = [];

$(document).ready((function () {
    $("#deliveryCoordinates").click(function () {

        setNotDeliveredMarkers();
    });
}));

function setNotDeliveredMarkers() {
    $.ajax({
        url: "/orderDetailsList",
        type: "GET",
        dataType: 'json',
        success: function (data) {
            firstOrderPointMarkersLayerGroup.clearLayers();
            secondOrderPointMarkersLayerGroup.clearLayers();
            if (data.result[0] === undefined) {
                alert("Please, add order details with delivery addresses to the system first!");
            } else {
                for (let i = 0; i < data.result.length; i++) {
                    if (data.result[i].status === "Заказ не доставлен") {
                        let firstOrderAddressPoint = data.result[i].firstOrderAddressPoint;
                        let secondOrderAddressPoint = data.result[i].secondOrderAddressPoint;
                        L.esri.Geocoding.geocode()
                            .text(firstOrderAddressPoint)
                            .run((err, results) => {
                                let latitude = results.results[0].latlng.lat;
                                let longitude = results.results[0].latlng.lng;
                                deliveryInfos[i] = data.result[i];
                                let deliveryMarker = L.marker([latitude, longitude],
                                    {icon: notDeliveredFirstOrderPoint}).addTo(firstOrderPointMarkersLayerGroup);
                                deliveryMarker.bindPopup("<b>Забрать заказ по адресу:</b> " + firstOrderAddressPoint + "<br>" +
                                    "<b>Комментарий заказчика:</b> " + data.result[i].comment);
                                firstOrderPointMarkers[i] = deliveryMarker;
                            });
                        L.esri.Geocoding.geocode()
                            .text(secondOrderAddressPoint)
                            .run((err, results) => {
                                let latitude = results.results[0].latlng.lat;
                                let longitude = results.results[0].latlng.lng;
                                deliveryInfos[i] = data.result[i];
                                let deliveryMarker = L.marker([latitude, longitude],
                                    {icon: notDeliveredSecondOrderPoint}).addTo(secondOrderPointMarkersLayerGroup);
                                deliveryMarker.bindPopup("<b>Доставить заказ по адресу:</b> " + secondOrderAddressPoint + "<br>" +
                                    "<b>Комментарий заказчика:</b> " + data.result[i].comment);
                                secondOrderPointMarkers[i] = deliveryMarker;
                            });
                    }
                }
            }
        }
    })
}

let deliveredMarkersLayerGroup = L.layerGroup().addTo(myDeliveryServiceMap);
let deliveredMarkers = [];

$(document).ready((function () {
    $("#showDeliveredOrders").click(function () {

        setDeliveredMarkers();
    });
}));

function setDeliveredMarkers() {
    $.ajax({
        url: "/orderDetailsList",
        type: "GET",
        dataType: 'json',
        success: function (data) {
            deliveredMarkersLayerGroup.clearLayers();
            if (data.result[0] === undefined) {
                alert("List of delivered orders is empty, finish one or more orders!");
            } else {
                for (let i = 0; i < data.result.length; i++) {
                    if (data.result[i].status === "Заказ доставлен") {
                        let address = data.result[i].orderAddress;
                        L.esri.Geocoding.geocode()
                            .text(address)
                            .run((err, results) => {
                                let latitude = results.results[0].latlng.lat;
                                let longitude = results.results[0].latlng.lng;
                                let deliveryMarker = L.marker([latitude, longitude],
                                    {icon: deliveredOrderPoint}).addTo(deliveredMarkersLayerGroup);
                                deliveryMarker.bindPopup("<b>Заказ доставлен по адресу:</b> " + address + "<br>" +
                                    "<b>Общее расстояние:</b> " + Math.round(solutionsInfos[i]._route.summary.totalDistance / 1000) + " километров<br>" +
                                    "<b>Время в пути:</b> " + Math.round(solutionsInfos[i]._route.summary.totalTime % 3600 / 60) + " минут");
                                deliveredMarkers[i] = deliveryMarker;
                            });
                    }
                }
            }
        }
    })
}

$(document).ready((function () {
    $("#hideCouriersMarkers").click(function () {

        hideCouriersMarkers();
    });
}));

function hideCouriersMarkers() {
    couriersMarkersLayerGroup.clearLayers();
}

$(document).ready((function () {
    $("#hideNotDeliveredOrderPoints").click(function () {

        hideNotDeliveredOrderPoints();
    });
}));

function hideNotDeliveredOrderPoints() {
    notDeliveredMarkersLayerGroup.clearLayers();
}


$(document).ready((function () {
    $("#hideDeliveredOrderPoints").click(function () {

        hideDeliveredOrderPoints();
    });
}));

function hideDeliveredOrderPoints() {
    deliveredMarkersLayerGroup.clearLayers();
}