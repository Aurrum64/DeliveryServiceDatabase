let isOrderDetailsPage = document.getElementById("orderDetailsList");
let isOrderDeliveryPage = document.getElementById("activeOrdersListForUser");
let isLogisticsPage = document.getElementById("activeOrdersListForLogisticPage");
let isOrderDetailsHistoryPage = document.getElementById("allArchiveOrdersList");
let isActiveOrderDetailsPage = document.getElementById("allActiveOrdersList");

if (isOrderDetailsPage !== null) {
    showOrderDetailsList();
}
if (isOrderDeliveryPage !== null) {
    showActiveOrdersListForUser();
    /*showArchiveOrdersListForUser();*/
}
if (isLogisticsPage !== null) {
    showActiveOrdersListForLogisticsPage();
    (function () {
        showActiveOrdersListForLogisticsPage();
        setTimeout(arguments.callee, 5000);
    })();
}
if (isOrderDetailsHistoryPage !== null) {
    showAllArchiveOrdersList();
}
if (isActiveOrderDetailsPage !== null) {
    showAllActiveOrdersList();
}

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
        alert("Должны быть заполнены все поля формы!");
    } else {
        saveOrderDetailsInDb(orderDetailsInput);

        setTimeout(function () {
            showOrderDetailsList();
            showActiveOrdersListForUser();
        }, (300));

        document.getElementById('addOrderDetails').reset();
    }
}

let addedRecords = [];

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

    let url = "/orderDetailsList";
    let htmlId = '#orderDetailsList';
    let emptyTableExpression = "Список заказов пока пуст!";

    takeOrderDetailsDataFromDb(url, htmlId, emptyTableExpression);
}

function showActiveOrdersListForUser() {

    let url = "/activeOrdersListForUser";
    let htmlId = '#activeOrdersListForUser';
    let emptyTableExpression = "У вас пока нет ни одного активного заказа";

    takeOrderDetailsDataFromDb(url, htmlId, emptyTableExpression);
}

/*function showArchiveOrdersListForUser() {

    let url = "/archiveOrdersListForUser";
    let htmlId = '#archiveOrdersListForUser';
    let emptyTableExpression = "В вашей истории заказов пока нет ни одного заказа";

    takeOrderDetailsDataFromDb(url, htmlId, emptyTableExpression);
}*/

function showActiveOrdersListForLogisticsPage() {

    let url = "/activeOrdersListForLogisticsPage";
    let htmlId = '#activeOrdersListForLogisticPage';
    let emptyTableExpression = "У вас пока нет ни одного активного заказа";

    takeOrderDetailsDataFromDb(url, htmlId, emptyTableExpression);
}

function showAllArchiveOrdersList() {

    let url = "/allArchiveOrdersList";
    let htmlId = '#allArchiveOrdersList';
    let emptyTableExpression = "В истории заказов пока нет ни одного заказа";

    takeOrderDetailsDataFromDb(url, htmlId, emptyTableExpression);
}

function showAllActiveOrdersList() {

    let url = "/allActiveOrdersList";
    let htmlId = '#allActiveOrdersList';
    let emptyTableExpression = "У вас пока нет ни одного активного заказа";

    takeOrderDetailsDataFromDb(url, htmlId, emptyTableExpression);
}

function takeOrderDetailsDataFromDb(url, htmlId, emptyTableExpression) {

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: url,
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            orderDetailsTableView(data, htmlId, emptyTableExpression);
        }
    });
}

function orderDetailsTableView(data, htmlId, emptyTableExpression) {

    let view;
    if (data.result[0] === undefined) {
        view =
            "<tr>" +
            "            <th scope=\"row\">" + emptyTableExpression + "</th>\n" +
            "            <td></td>\n" +
            "            <td></td>\n" +
            "            <td></td>\n" +
            "            <td></td>\n" +
            "            <td></td>\n" +
            "            <td></td>\n" +
            "            <td></td>\n" +
            "            <td></td>\n" +
            "</tr>";
        $(htmlId).html(view);
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
                "<td><a href=\"/order/" + data.result[i].orderDetailsId + "\">Подробнее</a></td>" +
                "</tr>";
            if (view === undefined) {
                view = "" + newLine;
            } else {
                view = view + newLine;
            }
        }
        $(htmlId).html(view);
    }
}