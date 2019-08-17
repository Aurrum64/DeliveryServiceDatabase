var myDeliveryServiceMap = L.map('map').setView([55.75195, 37.62165], 11.5);

//OneBox maps tiles - !!! ТАРИФИЦИРУЮТСЯ !!!
/*
L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=' +
    'pk.eyJ1IjoiYXVycnVtIiwiYSI6ImNqemUwZXFqbTAyNjczYm9id3Fia3g1Z3UifQ.UoaEszu5I-aX3iVxzQpTdw', {
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors,' +
        ' <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    maxZoom: 18,
    id: 'mapbox.streets',
    accessToken: 'pk.eyJ1IjoiYXVycnVtIiwiYSI6ImNqemUwZXFqbTAyNjczYm9id3Fia3g1Z3UifQ.UoaEszu5I-aX3iVxzQpTdw'
}).addTo(myDeliveryServiceMap);*/

//Open street maps tiles - !!! БЕСПЛАТНЫЕ !!!
L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
}).addTo(myDeliveryServiceMap);