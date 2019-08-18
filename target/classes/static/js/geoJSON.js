var geojson = {
    "copyrights": ["GraphHopper", "OpenStreetMap contributors"],
    "job_id": "32bf725c-8cee-40b4-ae5f-1fd44589c038",
    "status": "finished",
    "waiting_time_in_queue": 0,
    "processing_time": 190,
    "solution": {
        "costs": 1235,
        "distance": 1873654,
        "time": 63759,
        "transport_time": 63759,
        "completion_time": 63759,
        "max_operation_time": 63759,
        "waiting_time": 0,
        "service_duration": 0,
        "preparation_time": 0,
        "no_vehicles": 1,
        "no_unassigned": 0,
        "routes": [{
            "vehicle_id": "my_vehicle",
            "distance": 1873654,
            "transport_time": 63759,
            "completion_time": 63759,
            "waiting_time": 0,
            "service_duration": 0,
            "preparation_time": 0,
            "activities": [{
                "type": "start",
                "location_id": "berlin",
                "address": {
                    "location_id": "berlin",
                    "lat": 52.537,
                    "lon": 13.406
                },
                "end_time": 0,
                "end_date_time": null,
                "distance": 0,
                "driving_time": 0,
                "preparation_time": 0,
                "waiting_time": 0,
                "load_after": [0]
            }, {
                "type": "service",
                "id": "munich",
                "location_id": "munich",
                "address": {
                    "location_id": "munich",
                    "lat": 48.145,
                    "lon": 11.57
                },
                "arr_time": 19634,
                "arr_date_time": null,
                "end_time": 19634,
                "end_date_time": null,
                "waiting_time": 0,
                "distance": 587521,
                "driving_time": 19634,
                "preparation_time": 0,
                "load_before": [0],
                "load_after": [0]
            }, {
                "type": "service",
                "id": "frankfurt",
                "location_id": "frankfurt",
                "address": {
                    "location_id": "frankfurt",
                    "lat": 50.109,
                    "lon": 8.67
                },
                "arr_time": 33029,
                "arr_date_time": null,
                "end_time": 33029,
                "end_date_time": null,
                "waiting_time": 0,
                "distance": 978720,
                "driving_time": 33029,
                "preparation_time": 0,
                "load_before": [0],
                "load_after": [0]
            }, {
                "type": "service",
                "id": "cologne",
                "location_id": "cologne",
                "address": {
                    "location_id": "cologne",
                    "lat": 50.936,
                    "lon": 6.957
                },
                "arr_time": 39519,
                "arr_date_time": null,
                "end_time": 39519,
                "end_date_time": null,
                "waiting_time": 0,
                "distance": 1166976,
                "driving_time": 39519,
                "preparation_time": 0,
                "load_before": [0],
                "load_after": [0]
            }, {
                "type": "service",
                "id": "hamburg",
                "location_id": "hamburg",
                "address": {
                    "location_id": "hamburg",
                    "lat": 53.552,
                    "lon": 9.999
                },
                "arr_time": 53440,
                "arr_date_time": null,
                "end_time": 53440,
                "end_date_time": null,
                "waiting_time": 0,
                "distance": 1591560,
                "driving_time": 53440,
                "preparation_time": 0,
                "load_before": [0],
                "load_after": [0]
            }, {
                "type": "end",
                "location_id": "berlin",
                "address": {
                    "location_id": "berlin",
                    "lat": 52.537,
                    "lon": 13.406
                },
                "arr_time": 63759,
                "arr_date_time": null,
                "distance": 1873654,
                "driving_time": 63759,
                "preparation_time": 0,
                "waiting_time": 0,
                "load_before": [0]
            }]
        }],
        "unassigned": {
            "services": [],
            "shipments": [],
            "breaks": [],
            "details": []
        }
    }
};

L.geoJSON(geojson).add(myDeliveryServiceMap);