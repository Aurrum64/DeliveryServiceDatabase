let isOrderDetailsPage = document.getElementById("orderDetailsList");
let isOrderDeliveryPage = document.getElementById("activeOrdersListForUser");
let isLogisticsPage = document.getElementById("activeOrdersListForLogisticPage");
let isOrderDetailsHistoryPage = document.getElementById("allArchiveOrdersList");
let isActiveOrderDetailsPage = document.getElementById("allActiveOrdersList");
let isWaitingOrderDetailsPage = document.getElementById("waitingOrderDetailsList");

if (isOrderDetailsPage !== null) {
    showOrderDetailsList();
}
if (isOrderDeliveryPage !== null) {
    showActiveOrdersListForUser();
    showWaitingOrdersListForUser();
    showArchiveOrdersListForUser();
    (function () {
        showActiveOrdersListForUser();
        setTimeout(arguments.callee, 5000);
    })();
    (function () {
        showWaitingOrdersListForUser();
        setTimeout(arguments.callee, 5000);
    })();
    (function () {
        showArchiveOrdersListForUser();
        setTimeout(arguments.callee, 5000);
    })();
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
if (isWaitingOrderDetailsPage !== null) {
    showWaitingOrdersList();
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

    let inputDate = new Date(orderDetailsInput.orderDate.toLocaleString());
    let now = new Date();

    if (orderDetailsInput.orderDate === "" && orderDetailsInput.firstOrderAddressPoint === ""
        && orderDetailsInput.secondOrderAddressPoint === "" && orderDetailsInput.comment === "") {
        alert("Должны быть заполнены все поля формы!");
    } else if (inputDate < now) {
        alert("Указанное вами время выезда курьера уже прошло!");
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

function showWaitingOrdersListForUser() {

    let url = "/waitingOrdersListForUser";
    let htmlId = '#waitingOrdersListForUser';
    let emptyTableExpression = "У вас пока нет ни одного заказа на определенное время";

    takeOrderDetailsDataFromDb(url, htmlId, emptyTableExpression);
}

function showArchiveOrdersListForUser() {

    let url = "/archiveOrdersListForUser";
    let htmlId = '#archiveOrdersListForUser';
    let emptyTableExpression = "В вашей истории заказов пока нет ни одного заказа";

    takeOrderDetailsDataFromDb(url, htmlId, emptyTableExpression);
}

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

function showWaitingOrdersList() {

    let url = "/waitingOrdersList";
    let htmlId = '#waitingOrderDetailsList';
    let emptyTableExpression = "У вас пока нет ни одного заказа на определенное время";

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
            let simplifiedOrderDate;
            let orderDate = new Date(data.result[i].orderDate);
            if (orderDate.toLocaleDateString() === "01.01.1970") {
                simplifiedOrderDate = "Без указания даты";
            } else {
                let utcOrderDate = new Date(orderDate.getUTCFullYear(), orderDate.getUTCMonth(), orderDate.getUTCDate(), orderDate.getUTCHours(), orderDate.getUTCMinutes());
                let options = {
                    year: 'numeric',
                    month: 'long',
                    day: 'numeric',
                    weekday: 'long',
                    timezone: 'UTC',
                    hour: 'numeric',
                    minute: 'numeric'
                };
                simplifiedOrderDate = utcOrderDate.toLocaleString("ru", options);
            }
            let courier;
            if (data.result[i].courier === undefined ||
                data.result[i].courier === null) {
                courier = "Не назначен";
            } else {
                courier = data.result[i].courier.firstName;
            }
            console.log(data.result[i]);
            let reviewButton;
            if (data.result[i].reviewWritten === true) {
                reviewButton = "";
            } else {
                reviewButton = "<td><form action=\"/reviews\" method=\"post\">\n" +
                    "                                <input type=\"hidden\" name=\"orderDetailsId\" value=\"" + data.result[i].orderDetailsId  + "\">\n" +
                    "                                <input type=\"hidden\" name=\"authorName\" value=\"" + data.result[i].author.username + "\">\n" +
                    "                                <button type=\"submit\" class=\"btn btn-info ml-3\">Оставить отзыв</button>\n" +
                    "                            </form></td>";
            }
            let newLine =
                "<tr>" +
                "            <th scope=\"row\">" + data.result[i].orderDetailsId + "</th>\n" +
                "            <td>" + simplifiedOrderDate + "</td>\n" +
                "            <td>" + data.result[i].firstOrderAddressPoint + "</td>\n" +
                "            <td>" + data.result[i].secondOrderAddressPoint + "</td>\n" +
                "            <td>" + data.result[i].comment + "</td>\n" +
                "            <td>" + data.result[i].status + "</td>\n" +
                "            <td>" + data.result[i].authorName + "</td>\n" +
                "            <td>" + courier + "</td>\n" +
                "<td><a href=\"/order/" + data.result[i].orderDetailsId + "\">Подробнее</a></td>" +
                reviewButton +
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