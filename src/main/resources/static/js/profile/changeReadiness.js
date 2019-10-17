let ReadinessInputOnPageStart = {};
ReadinessInputOnPageStart["courierId"] = $("#courierId").val();

showCourierReadiness(ReadinessInputOnPageStart);

$(document).ready(function () {
    $("#changeReadiness").click(function (event) {

        event.preventDefault();
        changeReadiness();
    });
});

function changeReadiness() {

    let readinessInput = {};
    readinessInput["courierId"] = $("#courierId").val();
    if (document.getElementById('option1').checked) {
        readinessInput["readiness"] = document.getElementById('option1').value;
    } else {
        readinessInput["readiness"] = document.getElementById('option2').value;
    }
    saveReadinessInDb(readinessInput);

    setTimeout(function () {
        showCourierReadiness(readinessInput);
    }, (300));
}

function saveReadinessInDb(readinessInput) {

    $.ajax({
        type: "POST",
        url: "changeCourierReadiness",
        data: JSON.stringify(readinessInput),
        contentType: 'application/json',
        success: function (data) {
            if (data.status === 'OK') {
                console.log('Courier readiness data saved!');
            } else {
                console.log('Courier readiness data not saved!: ' + data.status + ', ' + data.errorMessage);
            }
        }
    });
}

function showCourierReadiness(readinessInput) {

    $.ajax({
        type: "POST",
        url: "findCourierProfileOwner",
        data: JSON.stringify(readinessInput),
        contentType: 'application/json',
        success: function (data) {

            let readiness = data.result[0].readiness;
            if (data.result[0].readiness === true) {
                readiness = "Готов";
            } else {
                readiness = "Не готов"
            }
            $('#readinessStatus').html(readiness);
        }
    });
}