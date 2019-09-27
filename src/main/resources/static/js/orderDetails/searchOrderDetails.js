$(document).ready(function () {
    $("#searchOrderDetails").submit(function (event) {

        event.preventDefault();
        searchOrderDetails();
    });
});

function searchOrderDetails() {

    let orderDetailsInput = {};
    orderDetailsInput["orderDetailsId"] = $("#searchOrderDetailsId").val();
    orderDetailsInput["orderDate"] = $("#searchOrderDate").val();
    orderDetailsInput["secondOrderAddressPoint"] = $("#searchSecondOrderAddressPoint").val();

    if (orderDetailsInput.orderDetailsId === "" & orderDetailsInput.orderDate === ""
        & orderDetailsInput.secondOrderAddressPoint === "") {
        showOrderDetailsList();
        console.log("All order details list shown.")
    } else {
        searchOrderDetailsInDb(orderDetailsInput);

        document.getElementById('addOrderDetails').reset();
    }
}

function searchOrderDetailsInDb(orderDetailsInput) {

    $.ajax({
        type: "POST",
        url: "searchOrderDetails",
        data: JSON.stringify(orderDetailsInput),
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
                    "</tr>";
                $('#orderDetailsList').html(view);
            } else {
                for (let i = 0; i < data.result.length; i++) {
                    let newLine =
                        "<tr>" +
                        "            <th scope=\"row\">" + data.result[i].orderDetailsId + "</th>\n" +
                        "            <td>" + data.result[i].orderDate + "</td>\n" +
                        "            <td>" + data.result[i].firstOrderAddressPoint + "</td>\n" +
                        "            <td>" + data.result[i].secondOrderAddressPoint + "</td>\n" +
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
