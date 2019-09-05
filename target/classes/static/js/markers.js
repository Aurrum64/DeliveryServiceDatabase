/*var courier1 = L.marker([55.7422, 37.5719],
    {icon: oldMan}).addTo(myDeliveryServiceMap);
var courier2 = L.marker([55.684758, 37.738521],
    {icon: women}).addTo(myDeliveryServiceMap);
var courier3 = L.marker([55.833436, 37.715175],
    {icon: businessMan}).addTo(myDeliveryServiceMap);

var destination1 = L.marker([55.687086, 37.529789]).addTo(myDeliveryServiceMap);
var destination2 = L.marker([55.782392, 37.614924]).addTo(myDeliveryServiceMap);
var destination3 = L.marker([55.642063, 37.656123]).addTo(myDeliveryServiceMap);
var destination4 = L.marker([55.826479, 37.487208]).addTo(myDeliveryServiceMap);
var destination5 = L.marker([55.694843, 37.435023]).addTo(myDeliveryServiceMap);
var destination6 = L.marker([55.790139, 37.814052]).addTo(myDeliveryServiceMap);*/

var couriersMarkersLayerGroup = L.layerGroup().addTo(myDeliveryServiceMap);
var courierMarker;

jQuery(document).ready(function ($) {
    $("#couriersCoordinates").click(function () {
        $.ajax({
            url: "/couriersCoordinates",
            type: "GET",
            dataType: 'json',
            success: function (data) {
                couriersMarkersLayerGroup.clearLayers();
                for (var i = 0; i <= data.toString().length - 1; i++) {
                    var latitude = data.result[i].latitude;
                    var longitude = data.result[i].longitude;
                    courierMarker = L.marker([latitude, longitude],
                        {icon: oldMan}).addTo(couriersMarkersLayerGroup);
                    courierMarker.bindPopup("Курьер №" + [i + 1] + "<br>" +
                        data.result[i].firstName + " " + data.result[i].lastName);
                }
            }
        })
    });
});

var deliveryMarkersLayerGroup = L.layerGroup().addTo(myDeliveryServiceMap);
var deliveryMarker;
var address;

jQuery(document).ready(function ($) {
    $("#deliveryCoordinates").click(function () {
        $.ajax({
            url: "/deliveryCoordinates",
            type: "GET",
            dataType: 'json',
            success: function (data) {
                deliveryMarkersLayerGroup.clearLayers();
                for (var i = 0; i <= data.toString().length - 1; i++) {
                    address = data.result[i].orderAddress;
                    L.esri.Geocoding.geocode()
                        .text(address)
                        .run((err, results) => {
                            var {lat, lng} = results.results[0].latlng;
                            deliveryMarker = L.marker([lat, lng]).addTo(deliveryMarkersLayerGroup);
                            deliveryMarker.bindPopup(address);
                        });
                }
            }
        })
    });
});


