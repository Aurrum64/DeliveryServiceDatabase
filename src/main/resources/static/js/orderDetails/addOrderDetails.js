let addedRecords = [];

showOrderDetailsListForManager();
showActiveOrdersListForUser();
showArchiveOrdersListForUser();
showActiveOrdersListForLogisticPage();

$(document).ready(function () {
    $("#addOrderDetails").submit(function (event) {

        event.preventDefault();
        addOrderDetails();
    });
});

function addOrderDetails() {

    let orderDetailsInput = {};
    orderDetailsInput["orderDate"] = $("#addOrderDate").val();
    orderDetailsInput["firstOrderAddressPoint"] = $("#addFirstOrderAddressPoint").val();
    orderDetailsInput["secondOrderAddressPoint"] = $("#addSecondOrderAddressPoint").val();
    orderDetailsInput["comment"] = $("#addComment").val();

    if (orderDetailsInput.orderDate === "" && orderDetailsInput.firstOrderAddressPoint === ""
        && orderDetailsInput.secondOrderAddressPoint === "" && orderDetailsInput.comment === "") {
        alert("Please, fill in all the fields or form!");
    } else {
        saveOrderDetailsInDb(orderDetailsInput);

        setTimeout(function () {
            showOrderDetailsListForManager();
            showActiveOrdersListForUser();
            showArchiveOrdersListForUser();
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

function showOrderDetailsListForManager() {

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/orderDetailsList",
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            let view;
            if (data.result[0] === undefined) {
                view =
                    "<tr>" +
                    "            <th scope=\"row\">Список заказов пока пуст!</th>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "</tr>";
                $('#orderDetailsList').html(view);
            } else {
                for (let i = 0; i < data.result.length; i++) {
                    let courier;
                    if (data.result[i].courier === undefined ||
                        data.result[i].courier === null) {
                        courier = "Не назначен";
                    } else {
                        courier = data.result[i].courier.firstName;
                    }
                    let newLine =
                        "<tr>" +
                        "            <th scope=\"row\">" + data.result[i].orderDetailsId + "</th>\n" +
                        "            <td>" + data.result[i].orderDate + "</td>\n" +
                        "            <td>" + data.result[i].firstOrderAddressPoint + "</td>\n" +
                        "            <td>" + data.result[i].secondOrderAddressPoint + "</td>\n" +
                        "            <td>" + data.result[i].comment + "</td>\n" +
                        "            <td>" + data.result[i].status + "</td>\n" +
                        "            <td>" + data.result[i].authorName + "</td>\n" +
                        "            <td>" + courier + "</td>\n" +
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

function showActiveOrdersListForUser() {

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/activeOrdersListForUser",
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            let view;
            if (data.result[0] === undefined) {
                view =
                    "<tr>" +
                    "            <th scope=\"row\">У вас пока нет ни одного активного заказа</th>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "</tr>";
                $('#activeOrdersListForUser').html(view);
            } else {
                for (let i = 0; i < data.result.length; i++) {
                    let courier;
                    if (data.result[i].courier === undefined ||
                        data.result[i].courier === null) {
                        courier = "Не назначен";
                    } else {
                        courier = data.result[i].courier.firstName;
                    }
                    let newLine =
                        "<tr>" +
                        "            <th scope=\"row\">" + data.result[i].orderDetailsId + "</th>\n" +
                        "            <td>" + data.result[i].orderDate + "</td>\n" +
                        "            <td>" + data.result[i].firstOrderAddressPoint + "</td>\n" +
                        "            <td>" + data.result[i].secondOrderAddressPoint + "</td>\n" +
                        "            <td>" + data.result[i].comment + "</td>\n" +
                        "            <td>" + data.result[i].status + "</td>\n" +
                        "            <td>" + data.result[i].authorName + "</td>\n" +
                        "            <td>" + courier + "</td>\n" +
                        "</tr>";
                    if (view === undefined) {
                        view = "" + newLine;
                    } else {
                        view = view + newLine;
                    }
                }
                $('#activeOrdersListForUser').html(view);
            }
        }
    });
}

function showArchiveOrdersListForUser() {

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/archiveOrdersListForUser",
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            let view;
            if (data.result[0] === undefined) {
                view =
                    "<tr>" +
                    "            <th scope=\"row\">В вашей истории заказов пока нет ни одного заказа</th>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "</tr>";
                $('#archiveOrdersListForUser').html(view);
            } else {
                for (let i = 0; i < data.result.length; i++) {
                    let courier;
                    if (data.result[i].courier === undefined ||
                        data.result[i].courier === null) {
                        courier = "Не назначен";
                    } else {
                        courier = data.result[i].courier.firstName;
                    }
                    let newLine =
                        "<tr>" +
                        "            <th scope=\"row\">" + data.result[i].orderDetailsId + "</th>\n" +
                        "            <td>" + data.result[i].orderDate + "</td>\n" +
                        "            <td>" + data.result[i].firstOrderAddressPoint + "</td>\n" +
                        "            <td>" + data.result[i].secondOrderAddressPoint + "</td>\n" +
                        "            <td>" + data.result[i].comment + "</td>\n" +
                        "            <td>" + data.result[i].status + "</td>\n" +
                        "            <td>" + data.result[i].authorName + "</td>\n" +
                        "            <td>" + courier + "</td>\n" +
                        "</tr>";
                    if (view === undefined) {
                        view = "" + newLine;
                    } else {
                        view = view + newLine;
                    }
                }
                $('#archiveOrdersListForUser').html(view);
            }
        }
    });
}

function showActiveOrdersListForLogisticPage() {

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/activeOrdersListForLogisticPage",
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            let view;
            if (data.result[0] === undefined) {
                view =
                    "<tr>" +
                    "            <th scope=\"row\">У вас пока нет ни одного активного заказа</th>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "</tr>";
                $('#activeOrdersListForLogisticPage').html(view);
            } else {
                for (let i = 0; i < data.result.length; i++) {
                    let courier;
                    if (data.result[i].courier === undefined ||
                        data.result[i].courier === null) {
                        courier = "Не назначен";
                    } else {
                        courier = data.result[i].courier.firstName;
                    }
                    let newLine =
                        "<tr>" +
                        "            <th scope=\"row\">" + data.result[i].orderDetailsId + "</th>\n" +
                        "            <td>" + data.result[i].orderDate + "</td>\n" +
                        "            <td>" + data.result[i].firstOrderAddressPoint + "</td>\n" +
                        "            <td>" + data.result[i].secondOrderAddressPoint + "</td>\n" +
                        "            <td>" + data.result[i].comment + "</td>\n" +
                        "            <td>" + data.result[i].status + "</td>\n" +
                        "            <td>" + data.result[i].authorName + "</td>\n" +
                        "            <td>" + courier + "</td>\n" +
                        "</tr>";
                    if (view === undefined) {
                        view = "" + newLine;
                    } else {
                        view = view + newLine;
                    }
                }
                $('#activeOrdersListForLogisticPage').html(view);
            }
        }
    });
}