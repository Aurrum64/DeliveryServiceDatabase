let myDeliveryServiceMap;

initMap();

function initMap() {

    myDeliveryServiceMap = L.map('map', {
        center: [55.75195, 37.62165],
        zoom: 11,
        minZoom: 10,
        maxZoom: 17,
        scrollWheelZoom: false
    });

    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(myDeliveryServiceMap);
}


