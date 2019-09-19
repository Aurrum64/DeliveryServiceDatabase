showReviewsList();

$(document).ready(function () {
    $("#addReviews").submit(function (event) {

        event.preventDefault();
        addReviews();
    });
});

function addReviews() {

    let reviewsInput = {};
    reviewsInput["orderId"] = $("#addOrderId").val();
    reviewsInput["clientName"] = $("#addClientName").val();
    reviewsInput["reviewSubject"] = $("#addReviewSubject").val();
    reviewsInput["review"] = $("#addReview").val();

    if (reviewsInput.orderId === "" && reviewsInput.clientName === ""
        && reviewsInput.reviewSubject === "" && reviewsInput.review === "") {
        alert("Please, fill in all fields of the add review form!");
    } else {
        saveReviewInDb(reviewsInput);

        setTimeout(function () {
            showReviewsList();
        }, (300));

        document.getElementById('addReviews').reset();
    }
}

function saveReviewInDb(reviewsInput) {

    $.ajax({
        type: "POST",
        url: "addReviews",
        data: JSON.stringify(reviewsInput),
        contentType: 'application/json',
        success: function (data) {
            if (data.status === 'OK') {
                console.log('Reviews data saved!');
            } else {
                console.log('Data not saved!: ' + data.status + ', ' + data.errorMessage);
            }
        }
    });
}

function showReviewsList() {

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/reviewsList",
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            let view;
            if (data.result[0] === undefined) {
                view = "<div class='container ml-5 mt-5'>" +
                    "<h2>Пока никто не оставлял комментариев :(</h2>" +
                    "</div>";
                $('#reviewsList').html(view);
            } else {
                for (let i = 0; i < data.result.length; i++) {
                    let newLine =
                        "<div class=\"media mt-5\">\n" +
                        "            <img class=\"d-flex rounded-circle avatar z-depth-1-half mr-3\"\n" +
                        "                 src=\"https://mdbootstrap.com/img/Photos/Avatars/avatar-10.jpg\"\n" +
                        "                 alt=\"Avatar\">\n" +
                        "            <div class=\"media-body\">\n" +
                        "                <h5 class=\"mt-0 font-weight-bold blue-text\">" + data.result[i].clientName + "</h5>\n" +
                        "      <i class=\"fas fa-star blue-text\"> </i>\n" +
                        "      <i class=\"fas fa-star blue-text\"> </i>\n" +
                        "      <i class=\"fas fa-star blue-text\"> </i>\n" +
                        "      <i class=\"fas fa-star blue-text\"> </i>\n" +
                        "      <i class=\"fas fa-star blue-text\"> </i><br><br>" +
                        "<b>Отзыв: </b>" + data.result[i].review + "\n" +
                        "            </div>\n" +
                        "        </div>";
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