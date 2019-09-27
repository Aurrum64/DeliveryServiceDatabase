$(document).ready(function () {
    $("#deleteCouriers").submit(function (event) {

        event.preventDefault();
        deleteCouriers();
    });
});

function deleteCouriers() {

    let couriersInput = {};
    couriersInput["courierId"] = $("#deleteCourierId").val();

/*    if (addedCouriers[0] === undefined) {
        alert("You haven’t added any couriers yet!");
    } else {*/
        if (couriersInput.courierId === "") {
            alert("Please, enter courier ID, which need to delete!");
        } else {
            deleteCourierFromDb(couriersInput);

            setTimeout(function () {
                showCouriersList();
            }, (300));

            document.getElementById('deleteCouriers').reset();
        }
//    }
}

function deleteCourierFromDb(couriersInput) {

    $.ajax({
        type: "POST",
        url: "deleteCouriers",
        data: JSON.stringify(couriersInput),
        contentType: 'application/json',
        success: function (data) {
            if (data.status === 'OK') {
                console.log('Courier data delete!');
            } else if (data.status === 'NOT EXISTS') {
                alert("Courier with such ID does not exists!");
            } else {
                console.log('Data not delete!: ' + data.status + ', ' + data.errorMessage);
            }
        }
    });
}