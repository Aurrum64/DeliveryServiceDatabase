showReviewsList();

function showReviewsList() {

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/reviewsList",
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            console.log(data);

            let view;
            if (data.result[0] === undefined) {
                view =
                    "<tr>" +
                    "            <th scope=\"row\">" + "В системе пока нет ни одного отзыва :(" + "</th>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "            <td></td>\n" +
                    "</tr>";
                $('#reviewsList').html(view);
            } else {
                for (let i = 0; i < data.result.length; i++) {
                    let newLine =
                        "<tr>" +
                        "            <th scope=\"row\">" + data.result[i].reviewId + "</th>\n" +
                        "            <td>" + data.result[i].orderId + "</td>\n" +
                        "            <td>" + data.result[i].clientName + "</td>\n" +
                        "            <td>" + data.result[i].rating + "</td>\n" +
                        "            <td>" + data.result[i].review + "</td>\n" +
                        "</tr>";
                    if (view === undefined) {
                        view = "" + newLine;
                    } else {
                        view = view + newLine;
                    }
                }
                $('#reviewsList').html(view);
            }
        }
    });
}