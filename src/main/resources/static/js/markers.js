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
        url: "/couriersCoordinates",
        type: "GET",
        dataType: 'json',
        success: function (data) {
            couriersMarkersLayerGroup.clearLayers();
            if (data.result[0] === undefined) {
                alert("Please, add courier with coordinates to the system first!");
            } else {
                for (let i = 0; i <= data.toString().length - 1; i++) {
                    if (data.result[i] !== undefined) {
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
        }
    })
}

let deliveryMarkersLayerGroup = L.layerGroup().addTo(myDeliveryServiceMap);
let notDeliveredMarkers = [];
let deliveredMarkers = [];

$(document).ready((function () {
    $("#deliveryCoordinates").click(function () {

        setNotDeliveredMarkers();
    });
}));

function setNotDeliveredMarkers() {
    $.ajax({
        url: "/deliveryCoordinates",
        type: "GET",
        dataType: 'json',
        success: function (data) {
            deliveryMarkersLayerGroup.clearLayers();
            if (data.result[0] === undefined) {
                alert("Please, add order details with delivery address to the system first!");
            } else {
                for (let i = 0; i <= data.toString().length - 1; i++) {
                    if (data.result[i] !== undefined) {
                        if (data.result[i].status === "Заказ не доставлен") {
                            let address = data.result[i].orderAddress;
                            L.esri.Geocoding.geocode()
                                .text(address)
                                .run((err, results) => {
                                    let latitude = results.results[0].latlng.lat;
                                    let longitude = results.results[0].latlng.lng;
                                    let deliveryMarker = L.marker([latitude, longitude],
                                        {icon: notDeliveredOrderPoint}).addTo(deliveryMarkersLayerGroup);
                                    deliveryMarker.bindPopup("<b>Заказ по адресу:</b> " + address + "<br>" +
                                        "<b>Комментарий заказчика:</b> " + data.result[i].comment);
                                    notDeliveredMarkers[i] = deliveryMarker;
                                });
                        }
                    }
                }
            }
        }
    })
}

$(document).ready((function () {
    $("#showDeliveredOrders").click(function () {

        setDeliveredMarkers();
    });
}));

function setDeliveredMarkers() {
    $.ajax({
        url: "/deliveryCoordinates",
        type: "GET",
        dataType: 'json',
        success: function (data) {
            deliveryMarkersLayerGroup.clearLayers();
            if (data.result[0] === undefined) {
                alert("Please, add order details with delivery address to the system first!");
            } else {
                for (let i = 0; i <= data.toString().length - 1; i++) {
                    if (data.result[i] !== undefined) {
                        if (data.result[i].status === "Заказ доставлен") {
                            let address = data.result[i].orderAddress;
                            L.esri.Geocoding.geocode()
                                .text(address)
                                .run((err, results) => {
                                    let latitude = results.results[0].latlng.lat;
                                    let longitude = results.results[0].latlng.lng;
                                    let deliveryMarker = L.marker([latitude, longitude],
                                        {icon: deliveredOrderPoint}).addTo(deliveryMarkersLayerGroup);
                                    deliveryMarker.bindPopup("<b>Заказ доставлен по адресу:</b> " + address + "<br>" +
                                        "<b>Общее расстояние:</b> " + Math.round(solutionsInfos[i]._route.summary.totalDistance / 1000) + " километров<br>" +
                                        "<b>Время в пути:</b> " + Math.round(solutionsInfos[i]._route.summary.totalTime % 3600 / 60) + " минут");
                                    deliveredMarkers[i] = deliveryMarker;
                                });
                        }
                    }
                }
            }
        }
    })
}
