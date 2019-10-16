$(document).ready(function () {
    $("#updateOrderDetails").submit(function (event) {

        event.preventDefault();
        updateOrderDetails();
    });
});

function updateOrderDetails() {

    let orderDetailsInput = {};
    orderDetailsInput["orderDetailsId"] = $("#updateOrderDetailsId").val();
    orderDetailsInput["orderDate"] = $("#updateOrderDate").val();
    orderDetailsInput["firstOrderAddressPoint"] = $("#updateFirstOrderAddressPoint").val();
    orderDetailsInput["secondOrderAddressPoint"] = $("#updateSecondOrderAddressPoint").val();
    orderDetailsInput["comment"] = $("#updateComment").val();

    if (orderDetailsInput.orderDetailsId === "") {
        alert("Please, enter order details ID, which need to be update!");
    } else if (orderDetailsInput.firstOrderAddressPoint === ""
        & orderDetailsInput.secondOrderAddressPoint === ""
        & orderDetailsInput.comment === "") {
        alert("Fill in at least one field!");
    } else {
        updateOrderDetailsInDb(orderDetailsInput);

        setTimeout(function () {
            showOrderDetailsList();
        }, (300));

        document.getElementById('updateOrderDetails').reset();
    }
}

function updateOrderDetailsInDb(orderDetailsInput) {

    $.ajax({
        type: "POST",
        url: "updateOrderDetails",
        data: JSON.stringify(orderDetailsInput),
        contentType: 'application/json',
        success: function (data) {
            if (data.status === 'OK') {
                console.log('Order details data update!');
            } else if (data.status === 'NOT EXISTS') {
                alert("Order details with such ID does not exists!");
            } else {
                console.log('Data not update!: ' + data.status + ', ' + data.errorMessage);
            }
        }
    });
}