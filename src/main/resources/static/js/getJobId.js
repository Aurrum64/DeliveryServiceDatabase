var vrp = {
    "vehicles": [
        {
            "vehicle_id": "my_vehicle",
            "start_address": {
                "location_id": "berlin",
                "lon": 13.406,
                "lat": 52.537
            }
        }
    ],
    "services": [
        {
            "id": "hamburg",
            "name": "visit_hamburg",
            "address": {
                "location_id": "hamburg",
                "lon": 9.999,
                "lat": 53.552
            }
        },
        {
            "id": "munich",
            "name": "visit_munich",
            "address": {
                "location_id": "munich",
                "lon": 11.57,
                "lat": 48.145
            }
        },
        {
            "id": "cologne",
            "name": "visit_cologne",
            "address": {
                "location_id": "cologne",
                "lon": 6.957,
                "lat": 50.936
            }
        },
        {
            "id": "frankfurt",
            "name": "visit_frankfurt",
            "address": {
                "location_id": "frankfurt",
                "lon": 8.67,
                "lat": 50.109
            }
        }
    ]
};

$.ajax({
    beforeSend: function (xhrObj) {
        xhrObj.setRequestHeader("Content-Type", "application/json");
        xhrObj.setRequestHeader("Accept", "application/json");
    },
    type: "POST",
    url: 'https://graphhopper.com/api/1/vrp/optimize?key=585def41-7ae7-4420-a2b3-b71c919bc166',
    data: JSON.stringify(vrp),
    dataType: "json",
    success: function (json) {
        console.log(json);
    }
});