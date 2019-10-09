let addedCouriers = [];

showCouriersList();
showActiveCouriersList();
showActiveCouriersListForLogisticsPage();
showRestCouriersList();

(function () {
    showActiveCouriersListForLogisticsPage();
    setTimeout(arguments.callee, 5000);
})();

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
                addedCouriers.push(couriersInput);
            } else {
                console.log('Data not saved!: ' + data.status + ', ' + data.errorMessage);
            }
        }
    });
}

function showCouriersList() {

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/couriersList",
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            let view;
            if (data.result[0] === undefined) {
                view =
                    "<tr>" +
                    "            <th scope=\"row\">List of couriers is empty yet!</th>\n" +
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
                    "            <td></td>\n" +
                    "</tr>";
                $('#couriersList').html(view);
            } else {
                for (let i = 0; i < data.result.length; i++) {
                    let fired;
                    if (data.result[i].fired === true) {
                        fired = "Уволен";
                    } else {
                        fired = "Да";
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
                        "            <td>" + data.result[i].readiness + "</td>\n" +
                        "            <td>" + fired + "</td>\n" +
                        "</tr>";
                    if (view === undefined) {
                        view = "" + newLine;
                    } else {
                        view = view + newLine;
                    }
                }
                $('#couriersList').html(view);
            }
        }
    });
}

function showActiveCouriersListForLogisticsPage() {

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/activeCouriersListForLogisticsPage",
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            let view;
            if (data.result[0] === undefined) {
                view =
                    "<tr>" +
                    "            <th scope=\"row\">Нет курьеров, готовых принять заказ :(</th>\n" +
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
                $('#activeCouriersListForLogisticsPage').html(view);
            } else {
                for (let i = 0; i < data.result.length; i++) {
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
                        "</tr>";
                    if (view === undefined) {
                        view = "" + newLine;
                    } else {
                        view = view + newLine;
                    }
                }
                $('#activeCouriersListForLogisticsPage').html(view);
            }
        }
    });
}

function showActiveCouriersList() {

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/activeCouriersList",
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            let view;
            if (data.result[0] === undefined) {
                view =
                    "<tr>" +
                    "            <th scope=\"row\">Нет курьеров, готовых принять заказ :(</th>\n" +
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
                $('#activeCouriersList').html(view);
            } else {
                for (let i = 0; i < data.result.length; i++) {
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
                        "            <td>" + data.result[i].readiness + "</td>\n" +
                        "</tr>";
                    if (view === undefined) {
                        view = "" + newLine;
                    } else {
                        view = view + newLine;
                    }
                }
                $('#activeCouriersList').html(view);
            }
        }
    });
}

function showRestCouriersList() {

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/restCouriersList",
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            let view;
            if (data.result[0] === undefined) {
                view =
                    "<tr>" +
                    "            <th scope=\"row\">Все курьеры задействованы в доставке заказов!</th>\n" +
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
                $('#restCouriersList').html(view);
            } else {
                for (let i = 0; i < data.result.length; i++) {
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
                        "            <td>" + data.result[i].readiness + "</td>\n" +
                        "</tr>";
                    if (view === undefined) {
                        view = "" + newLine;
                    } else {
                        view = view + newLine;
                    }
                }
                $('#restCouriersList').html(view);
            }
        }
    });
}