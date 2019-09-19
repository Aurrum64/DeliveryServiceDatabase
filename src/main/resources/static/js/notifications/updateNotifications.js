$(document).ready(function () {
    $("#updateNotificationsList").submit(function (event) {

        event.preventDefault();
        showUsersList();
    });
});

function showUsersList() {

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/user/usersList",
        dataType: 'json',
        cache: false,
        success: function (data) {

            console.log(data);
        }
    });
}