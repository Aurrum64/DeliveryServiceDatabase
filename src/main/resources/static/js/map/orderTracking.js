let trackedCourierLayer = L.layerGroup().addTo(myDeliveryServiceMap);
let trackedOrderLayer = L.layerGroup().addTo(myDeliveryServiceMap);

courierTracking();

function courierTracking() {

    let orderTrackingInput = {};
    orderTrackingInput["courierId"] = $("#courierId").val();
    orderTrackingInput["orderDetailsId"] = $("#orderDetailsId").val();

    showOrderPoints(orderTrackingInput);

    (function () {
        updateTrackedCourierCoordinates(orderTrackingInput);
        setTimeout(arguments.callee, 1000);
    })();
}

function updateTrackedCourierCoordinates(orderTrackingInput) {

    $.ajax({
        type: "POST",
        url: orderTrackingInput.orderDetailsId + "/courierTracking",
        data: JSON.stringify(orderTrackingInput),
        contentType: 'application/json',
        success: function (data) {

            trackedCourierLayer.clearLayers();
            let latitude = data.result[0].latitude;
            let longitude = data.result[0].longitude;
            let trackedCourierMarker = L.marker([latitude, longitude],
                {icon: cargo}).addTo(trackedCourierLayer);
            trackedCourierMarker.bindPopup(data.result[0].firstName + " " + data.result[0].lastName);
        }
    });
}

function showOrderPoints(orderTrackingInput) {

    $.ajax({
        type: "POST",
        url: orderTrackingInput.orderDetailsId + "/trackedOrder",
        data: JSON.stringify(orderTrackingInput),
        contentType: 'application/json',
        success: function (data) {

            trackedOrderLayer.clearLayers();
            let firstOrderAddressPoint = data.result[0].firstOrderAddressPoint;
            let secondOrderAddressPoint = data.result[0].secondOrderAddressPoint;
            L.esri.Geocoding.geocode()
                .text(firstOrderAddressPoint)
                .run((err, results) => {
                    let latitude = parseFloat(results.results[0].latlng.lat.toFixed(4));
                    let longitude = parseFloat(results.results[0].latlng.lng.toFixed(4));
                    let firstAddressMarker = L.marker([latitude, longitude],
                        {icon: notDeliveredFirstOrderPoint}).addTo(trackedOrderLayer);
                    firstAddressMarker.bindPopup("<b>Забрать ваш заказ по адресу:</b> " + firstOrderAddressPoint + "<br>");
                });
            L.esri.Geocoding.geocode()
                .text(secondOrderAddressPoint)
                .run((err, results) => {
                    let latitude = parseFloat(results.results[0].latlng.lat.toFixed(4));
                    let longitude = parseFloat(results.results[0].latlng.lng.toFixed(4));
                    let secondAddressMarker = L.marker([latitude, longitude],
                        {icon: notDeliveredSecondOrderPoint}).addTo(trackedOrderLayer);
                    secondAddressMarker.bindPopup("<b>Доставить ваш заказ по адресу:</b> " + secondOrderAddressPoint + "<br>");
                });
        }
    });
}