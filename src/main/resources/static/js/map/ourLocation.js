let deliveryServiceLocation = L.marker([55.7299, 37.6457],
    {icon: netCrackerLogo}).addTo(myDeliveryServiceMap);
deliveryServiceLocation.bindPopup("<b>Головной офис Delivery Service</b><br>" +
    "<b>По адресу:</b> Кожевническая ул., 7, Москва").openPopup();

$(document).ready((function () {
    $("#buildRouteFromUnderground").click(function () {

        let wayFromGreenLineUnderGround = L.Routing.control(
            {
                waypoints: [
                    L.latLng(55.7299, 37.6394),
                    L.latLng(55.7299, 37.6457),
                ],
                lineOptions: {
                    styles: [{color: 'green', opacity: 5, weight: 5}]
                },
                createMarker: function () {
                    return null;
                },
                show: false,
                router: L.Routing.graphHopper('585def41-7ae7-4420-a2b3-b71c919bc166'),
                routeWhileDragging: true
            });
        wayFromGreenLineUnderGround.addTo(myDeliveryServiceMap);

        let wayFromCircleLineUnderGround = L.Routing.control(
            {
                waypoints: [
                    L.latLng(55.7310, 37.6365),
                    L.latLng(55.7299, 37.6457),
                ],
                lineOptions: {
                    styles: [{color: 'brown', opacity: 5, weight: 5}]
                },
                createMarker: function () {
                    return null;
                },
                show: false,
                router: L.Routing.graphHopper('585def41-7ae7-4420-a2b3-b71c919bc166'),
                routeWhileDragging: true
            });
        wayFromCircleLineUnderGround.addTo(myDeliveryServiceMap);

        let greenLineUnderground = L.marker([55.7300, 37.6394],
            {icon: metro}).addTo(myDeliveryServiceMap);
        greenLineUnderground.bindPopup("<b>Станция метро:</b> Павелецкая<br>" +
            "Зеленая линия метро");

        let circleLineUnderground = L.marker([55.7311, 37.6363],
            {icon: metro}).addTo(myDeliveryServiceMap);
        circleLineUnderground.bindPopup("<b>Станция метро:</b> Павелецкая<br>" +
            "Кольцевая линия метро");
    });
}));

