let couriersMarkersLayerGroup = L.layerGroup().addTo(myDeliveryServiceMap);
let couriersMarkers = [];
let couriersInfos = [];

showCourierOnMap();

(function () {
    setNotDeliveredMarkers();
    setTimeout(arguments.callee, 4000);
})();

function showCourierOnMap() {
    $.ajax({
        url: "/currentCourier",
        type: "GET",
        dataType: 'json',
        success: function (data) {
            couriersMarkersLayerGroup.clearLayers();
            if (data.result !== null) {
                for (let i = 0; i < data.result.length; i++) {
                    let latitude = data.result[i].latitude;
                    let longitude = data.result[i].longitude;
                    couriersInfos[i] = data.result[i];
                    let courierMarker = L.marker([latitude, longitude],
                        {icon: cargo}).addTo(couriersMarkersLayerGroup);
                    courierMarker.bindPopup("Вы находитесь здесь");
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

function setNotDeliveredMarkers() {
    $.ajax({
        url: "/activeOrdersListForMap",
        type: "GET",
        dataType: 'json',
        success: function (data) {
            firstOrderPointMarkers.length = 0;
            secondOrderPointMarkers.length = 0;
            deliveryInfos.length = 0;

            firstOrderPointMarkersLayerGroup.clearLayers();
            secondOrderPointMarkersLayerGroup.clearLayers();
            if (data.result[0] !== undefined) {
                for (let i = 0; i < data.result.length; i++) {
                    let firstOrderAddressPoint = data.result[i].firstOrderAddressPoint;
                    let secondOrderAddressPoint = data.result[i].secondOrderAddressPoint;
                    if (!data.result[i].orderSpecification.orderPickedUp) {
                        L.esri.Geocoding.geocode()
                            .text(firstOrderAddressPoint)
                            .run((err, results) => {
                                let latitude = parseFloat(results.results[0].latlng.lat.toFixed(4));
                                let longitude = parseFloat(results.results[0].latlng.lng.toFixed(4));
                                let deliveryMarker = L.marker([latitude, longitude],
                                    {icon: notDeliveredFirstOrderPoint}).addTo(firstOrderPointMarkersLayerGroup);
                                deliveryMarker.bindPopup("<b>Заказ №" + data.result[i].orderDetailsId + "</b><br>" +
                                    "<b>Забрать заказ по адресу:</b> " + firstOrderAddressPoint + "<br>" +
                                    "<b>Комментарий заказчика:</b> " + data.result[i].comment);
                                firstOrderPointMarkers[i] = deliveryMarker;
                            });
                    }
                    L.esri.Geocoding.geocode()
                        .text(secondOrderAddressPoint)
                        .run((err, results) => {
                            let latitude = parseFloat(results.results[0].latlng.lat.toFixed(4));
                            let longitude = parseFloat(results.results[0].latlng.lng.toFixed(4));
                            deliveryInfos[i] = data.result[i];
                            let deliveryMarker = L.marker([latitude, longitude],
                                {icon: notDeliveredSecondOrderPoint}).addTo(secondOrderPointMarkersLayerGroup);
                            deliveryMarker.bindPopup("<b>Заказ №" + data.result[i].orderDetailsId + "</b><br>" +
                                "<b>Доставить заказ по адресу:</b> " + secondOrderAddressPoint + "<br>" +
                                "<b>Комментарий заказчика:</b> " + data.result[i].comment);
                            secondOrderPointMarkers[i] = deliveryMarker;
                        });
                }
            }
        }
    })
}