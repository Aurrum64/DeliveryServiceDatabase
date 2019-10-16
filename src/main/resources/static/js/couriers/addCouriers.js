let isCouriersPage = document.getElementById("couriersList");
let isActiveCouriersPage = document.getElementById("activeCouriersList");
let isOfficeWorkPageCouriers = document.getElementById("activeCouriersListForOfficeWorkPage");

//let addedCouriers = [];

if (isCouriersPage != null) {
    showCouriersList();
}
if (isActiveCouriersPage != null) {
    showActiveCouriersList();
}
if (isOfficeWorkPageCouriers != null) {
    showCouriersListForOfficeWorkPage();
    (function () {
        showCouriersListForOfficeWorkPage();
        setTimeout(arguments.callee, 5000);
    })();
}

$(document).ready(function () {
    $("#addCouriers").submit(function (event) {

        event.preventDefault();
        addCouriers();
    });
});

function addCouriers() {

    let couriersInput = {};
    couriersInput["firstName"] = $("#addCourierFirstName").val();
    couriersInput["lastName"] = $("#addCourierLastName").val();
    couriersInput["email"] = $("#addCourierEmail").val();
    couriersInput["phoneNumber"] = $("#addCourierPhoneNumber").val();
    couriersInput["rating"] = $("#addCourierRating").val();
    couriersInput["salary"] = $("#addCourierSalary").val();
    couriersInput["hireDate"] = $("#addCourierHireDate").val();
    couriersInput["premium"] = $("#addCourierPremium").val();
    couriersInput["departmentId"] = $("#addCourierDepartmentId").val();

    if (couriersInput.firstName === "" && couriersInput.lastName === ""
        && couriersInput.email === "") {
        alert("You did not enter first name, last name or e-mail!");
    } else {
        saveCouriersInDb(couriersInput);

        setTimeout(function () {
            showCouriersList();
        }, (300));

        document.getElementById('addCouriers').reset();
    }
}

function saveCouriersInDb(couriersInput) {

    $.ajax({
        type: "POST",
        url: "addCouriers",
        data: JSON.stringify(couriersInput),
        contentType: 'application/json',
        success: function (data) {
            if (data.status === 'OK') {
                console.log('Couriers data saved!');
//                addedCouriers.push(couriersInput);
            } else {
                console.log('Data not saved!: ' + data.status + ', ' + data.errorMessage);
            }
        }
    });
}

function showCouriersList() {

    let url = "/couriersList";
    let htmlId = '#couriersList';
    let emptyTableExpression = "Список курьеров пока пуст :(";

    takeCouriersDataFromDb(url, htmlId, emptyTableExpression);
}

function showActiveCouriersList() {

    let url = "/activeCouriersList";
    let htmlId = '#activeCouriersList';
    let emptyTableExpression = "Пока нет курьеров, готовых принять заказ :(";

    takeCouriersDataFromDb(url, htmlId, emptyTableExpression);
}

function showCouriersListForOfficeWorkPage() {

    let url = "/activeCouriersListForOfficeWorkPage";
    let htmlId = '#activeCouriersListForOfficeWorkPage';
    let emptyTableExpression = "Пока нет курьеров, готовых принять заказ :(";

    takeCouriersDataFromDb(url, htmlId, emptyTableExpression);
}

function takeCouriersDataFromDb(url, htmlId, emptyTableExpression) {

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: url,
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            couriersTableView(data, htmlId, emptyTableExpression);
        }
    });
}

function couriersTableView(data, htmlId, emptyTableExpression) {

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
            "            <td></td>\n" +
            "            <td></td>\n" +
            "            <td></td>\n" +
            "            <td></td>\n" +
            "            <td></td>\n" +
            "</tr>";
        $(htmlId).html(view);
    } else {
        for (let i = 0; i < data.result.length; i++) {
            let fired;
            if (isCouriersPage != null) {
                if (data.result[i].fired === true) {
                    fired = "Уволен";
                } else {
                    fired = "Да";
                }
            } else {
                fired = "";
            }
            let readiness;
            if (data.result[i].readiness === true) {
                readiness = "Готов";
            } else {
                readiness = "Не готов";
            }
            let newLine =
                "<tr>" +
                "            <th scope=\"row\">" + data.result[i].courierId + "</th>\n" +
                "            <td>" + data.result[i].firstName + "</td>\n" +
                "            <td>" + data.result[i].lastName + "</td>\n" +
                "            <td>" + data.result[i].email + "</td>\n" +
                "            <td>" + data.result[i].phoneNumber + "</td>\n" +
                "            <td>" + data.result[i].rating + "</td>\n" +
                "            <td>" + data.result[i].salary + "</td>\n" +
                "            <td>" + data.result[i].hireDate + "</td>\n" +
                "            <td>" + data.result[i].premium + "</td>\n" +
                "            <td>" + data.result[i].departmentId + "</td>\n" +
                "            <td>" + data.result[i].latitude + "</td>\n" +
                "            <td>" + data.result[i].longitude + "</td>\n" +
                "            <td>" + data.result[i].authorName + "</td>\n" +
                "            <td>" + readiness + "</td>\n" +
                "            <td>" + fired + "</td>\n" +
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

