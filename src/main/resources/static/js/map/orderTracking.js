courierTracking();

function courierTracking() {

    let orderTrackingInput = {};
    orderTrackingInput["courierId"] = $("#courierId").val();
    orderTrackingInput["orderDetailsId"] = $("#orderDetailsId").val();

    take(orderTrackingInput);
}

function take(orderTrackingInput) {
    $.ajax({
        type: "POST",
        url: orderTrackingInput.orderDetailsId + "/courierTracking",
        data: JSON.stringify(orderTrackingInput),
        contentType: 'application/json',
        success: function (data) {

            console.log(data);
        }
    });
}