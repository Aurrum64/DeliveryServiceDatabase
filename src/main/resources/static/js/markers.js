/*
var courier1 = L.marker([55.7422, 37.5719],
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
var destination6 = L.marker([55.790139, 37.814052]).addTo(myDeliveryServiceMap);

courier1.bindPopup("Курьер №1");
courier2.bindPopup("Курьер №2");
courier3.bindPopup("Курьер №3");

destination1.bindPopup("Точка доставки №1");
destination2.bindPopup("Точка доставки №2");
destination3.bindPopup("Точка доставки №3");
destination4.bindPopup("Точка доставки №4");
destination5.bindPopup("Точка доставки №5");
destination6.bindPopup("Точка доставки №6");*/

var courierMarker;

jQuery(document).ready(function ($) {
    $("#test").click(function () {
        $.ajax({
            url: "/couriersCoordinates",
            type: "GET",
            dataType: 'json',
            success: function (data) {
                for (var i = 0; i <= data.toString().length - 1; i++) {
                    var latitude = data.result[i].latitude;
                    var longitude = data.result[i].longitude;
                    courierMarker = L.marker([latitude, longitude],
                        {icon: oldMan}).addTo(myDeliveryServiceMap);
                    courierMarker.bindPopup("Курьер №" + [i + 1] + "<br>" +
                        data.result[i].firstName + " " + data.result[i].lastName);
                }
            }
        })
    });
});
