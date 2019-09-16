let addedRecords = [];

showOrderDetailsList();

$(document).ready(function () {
    $("#addOrderDetails").submit(function (event) {

        event.preventDefault();
        addOrderDetails();
    });
});

function addOrderDetails() {

    let orderDetailsInput = {};
    orderDetailsInput["orderDate"] = $("#addOrderDate").val();
    orderDetailsInput["orderAddress"] = $("#addOrderAddress").val();
    orderDetailsInput["comment"] = $("#addComment").val();

    if (orderDetailsInput.orderDate === "" && orderDetailsInput.orderAddress === "") {
        alert("Please, enter order date and order address!");
    } else if (orderDetailsInput.orderDate === "") {
        alert("Please, enter order date!");
    } else if (orderDetailsInput.orderAddress === "") {
        alert("Please, enter order address!");
    } else {
        saveOrderDetailsInDb(orderDetailsInput);

        setTimeout(function () {
            showOrderDetailsList();
        }, (300));

        document.getElementById('addOrderDetails').reset();
    }
}

function saveOrderDetailsInDb(orderDetailsInput) {

    $.ajax({
        type: "POST",
        url: "addOrderDetails",
        data: JSON.stringify(orderDetailsInput),
        contentType: 'application/json',
        success: function (data) {
            if (data.status === 'OK') {
                console.log('Order details data saved!');
                addedRecords.push(orderDetailsInput);
            } else {
                console.log('Data not saved!: ' + data.status + ', ' + data.errorMessage);
            }
        }
    });
}

function showOrderDetailsList() {

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/deliveryCoordinates",
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            let view;
            if (data.result[0] === undefined) {
                view =
                    "<tr>" +
                    "            <th scope=\"row\">List of detailed order information is empty yet!</th>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "</tr>";
                $('#orderDetailsList').html(view);
            } else {
                for (let i = 0; i < data.result.length; i++) {
                    let newLine =
                        "<tr>" +
                        "            <th scope=\"row\">" + data.result[i].orderDetailsId + "</th>\n" +
                        "            <td>" + data.result[i].orderDate + "</td>\n" +
                        "            <td>" + data.result[i].orderAddress + "</td>\n" +
                        "            <td>" + data.result[i].comment + "</td>\n" +
                        "            <td>" + data.result[i].status + "</td>\n" +
                        "            <td>" + data.result[i].authorName + "</td>\n" +
                        "</tr>";
                    if (view === undefined) {
                        view = "" + newLine;
                    } else {
                        view = view + newLine;
                    }
                }
                $('#orderDetailsList').html(view);
            }
        }
    });
}