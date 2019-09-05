var myDeliveryServiceMap = L.map('map', {
    center: [55.75195, 37.62165],
    zoom: 11.5,
    minZoom: 10,
    maxZoom: 13,
    scrollWheelZoom: false
});

L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
}).addTo(myDeliveryServiceMap);

const address = "Москва, ул. Тверская";
L.esri.Geocoding.geocode()
    .text(address)
    .run((err, results, response) => {
        console.log(results.results[0].latlng);
        const {lat, lng} = results.results[0].latlng;
        L.marker([lat, lng])
            .addTo(myDeliveryServiceMap)
            .bindPopup(address)
            .openPopup();
    });


/*L.Control.geocoder().addTo(myDeliveryServiceMap);*/

/*geocoder = new L.Control.Geocoder.Nominatim();

var addres = "Москва, ул. Тверская";

geocoder.geocode(addres, function (results) {
    markGeocode(results);*/
/*  var latLng = L.LatLng(results[0].center.lat, results[0].center.lng);
  var marker = L.marker([latLng]).addTo(myDeliveryServiceMap);*/
/*})
;*/

/*
L.Control.geocoder().addTo(myDeliveryServiceMap);

var geocoder = L.Control.geocoder({
    defaultMarkGeocode: false
})
    .on('markgeocode', function(e) {
        var bbox = e.geocode.bbox;
        var poly = L.polygon([
            bbox.getSouthEast(),
            bbox.getNorthEast(),
            bbox.getNorthWest(),
            bbox.getSouthWest()
        ]).addTo(myDeliveryServiceMap);
        map.fitBounds(poly.getBounds());
    })
    .addTo(myDeliveryServiceMap);*/
