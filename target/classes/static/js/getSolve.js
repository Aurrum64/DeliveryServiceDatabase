$.ajax({
    beforeSend: function (xhrObj) {
        xhrObj.setRequestHeader("Content-Type", "application/json");
        xhrObj.setRequestHeader("Accept", "application/json");
    },
    type: "GET",
    url: 'https://graphhopper.com/api/1/vrp/solution/32bf725c-8cee-40b4-ae5f-1fd44589c038?key=585def41-7ae7-4420-a2b3-b71c919bc166',
    data: JSON.stringify,
    dataType: "json",
    success: function (json) {
        console.log(json);
    }
});