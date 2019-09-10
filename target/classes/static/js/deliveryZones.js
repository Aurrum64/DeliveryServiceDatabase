let deliveryZonesLayerGroup = L.layerGroup().addTo(myDeliveryServiceMap);

$(document).ready((function () {
    $("#showDeliveryZones").click(function () {

        showDeliveryZones();
    });
}));

function showDeliveryZones() {

    let deliveryZone = L.polygon([
        [55.79163, 37.63453],
        [55.81874, 37.63933],
        [55.84101, 37.66319],
        [55.84487, 37.64963],
        [55.84824, 37.5741],
        [55.84988, 37.56672],
        [55.83455, 37.57273],
        [55.82231, 37.57307],
        [55.80475, 37.58264],
        [55.79231, 37.58457]
    ], {
        fillColor: 'blue',
        fillOpacity: 0.1
    }).addTo(deliveryZonesLayerGroup);

    deliveryZone.bindPopup("<b>Зона доставки №1</b><br>Цена: 200 рублей");
}

$(document).ready((function () {
    $("#hideDeliveryZones").click(function () {

        deliveryZonesLayerGroup.clearLayers();
    });
}));
