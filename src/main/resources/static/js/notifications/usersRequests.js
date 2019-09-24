$(document).ready(function () {
    $("#chooseProfession").submit(function (event) {

        event.preventDefault();
        getUserProfessionChoice();
    });
});

function getUserProfessionChoice() {

    let requestsInput = {};
    requestsInput["userChoice"] = document.getElementById('professionChoice').value;

    if (requestsInput.userChoice === "Кем вы хотите работать в нашей компании?") {
        alert("Выберите одну из доступных профессий!");
    } else {
        saveUserRequestInDb(requestsInput);
        alert("Ваша заявка принята и находится на рассмотрении!");
    }
}

function saveUserRequestInDb(requestsInput) {

    $.ajax({
        type: "POST",
        url: "addUsersRequests",
        data: JSON.stringify(requestsInput),
        contentType: 'application/json',
        success: function (data) {
            if (data.status === 'OK') {
                console.log('Users request data saved!');
            } else {
                console.log('Users request data not saved!: ' + data.status + ', ' + data.errorMessage);
            }
        }
    });
}