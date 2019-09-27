$(document).ready(function () {
    $("#deleteOrderDetails").submit(function (event) {

        event.preventDefault();
        deleteOrderDetails();
    });
});

function deleteOrderDetails() {

    let orderDetailsInput = {};
    orderDetailsInput["orderDetailsId"] = $("#deleteOrderDetailsId").val();

/*    if (addedRecords[0] === undefined) {
        alert("You haven’t added any order details yet!");
    } else {*/
        if (orderDetailsInput.orderDetailsId === "") {
            alert("Please, enter order details ID, which need to delete!");
        } else {
            deleteOrderDetailsInDb(orderDetailsInput);

            setTimeout(function () {
                showOrderDetailsList();
            }, (300));

            document.getElementById('deleteOrderDetails').reset();
        }
//    }
}

function deleteOrderDetailsInDb(orderDetailsInput) {

    $.ajax({
        type: "POST",
        url: "deleteOrderDetails",
        data: JSON.stringify(orderDetailsInput),
        contentType: 'application/json',
        success: function (data) {
            if (data.status === 'OK') {
                console.log('Order details data delete!');
            } else if (data.status === 'NOT EXISTS') {
                alert("Order details with such ID does not exists!");
            } else {
                console.log('Data not delete!: ' + data.status + ', ' + data.errorMessage);
            }
        }
    });
}