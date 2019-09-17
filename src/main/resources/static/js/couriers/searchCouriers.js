$(document).ready(function () {
    $("#searchCouriers").submit(function (event) {

        event.preventDefault();
        searchCouriers();
    });
});

function searchCouriers() {

    let couriersInput = {};
    couriersInput["courierId"] = $("#searchCourierId").val();
    couriersInput["firstName"] = $("#searchCourierFirstName").val();
    couriersInput["lastName"] = $("#searchCourierLastName").val();

    if (couriersInput.courierId === "" & couriersInput.firstName === ""
        & couriersInput.lastName === "") {
        showCouriersList();
        console.log("All couriers list shown.")
    } else {
        searchCouriersInDb(couriersInput);

        document.getElementById('searchCouriers').reset();
    }
}

function searchCouriersInDb(couriersInput) {

    $.ajax({
        type: "POST",
        url: "searchCouriers",
        data: JSON.stringify(couriersInput),
        contentType: 'application/json',
        success: function (data) {

            let view;
            if (data.result[0] === undefined) {
                view =
                    "<tr>" +
                    "            <th scope=\"row\">Nothing was found by query result!</th>\n" +
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