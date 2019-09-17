$(document).ready(function () {
    $("#updateCouriers").submit(function (event) {

        event.preventDefault();
        updateCouriers();
    });
});

function updateCouriers() {

    let couriersInput = {};
    couriersInput["courierId"] = $("#updateCourierId").val();
    couriersInput["firstName"] = $("#updateCourierFirstName").val();
    couriersInput["lastName"] = $("#updateCourierLastName").val();
    couriersInput["email"] = $("#updateCourierEmail").val();
    couriersInput["phoneNumber"] = $("#updateCourierPhoneNumber").val();
    couriersInput["rating"] = $("#updateCourierRating").val();
    couriersInput["salary"] = $("#updateCourierSalary").val();
    couriersInput["hireDate"] = $("#updateCourierHireDate").val();
    couriersInput["premium"] = $("#updateCourierPremium").val();
    couriersInput["departmentId"] = $("#updateCourierDepartmentId").val();

    if (addedCouriers[0] === undefined) {
        alert("You haven’t added any couriers yet!");
    } else {
        if (couriersInput.courierId === "") {
            alert("Please, enter courier ID, which need to be update!");
        } else if (couriersInput.firstName === ""
            & couriersInput.lastName === ""
            & couriersInput.email === ""
            & couriersInput.phoneNumber === ""
            & couriersInput.rating === ""
            & couriersInput.salary === ""
            & couriersInput.hireDate === ""
            & couriersInput.premium === ""
            & couriersInput.departmentId === "") {
            alert("Fill in at least one field!");
        } else {
            updateCouriersInDb(couriersInput);

            setTimeout(function () {
                showCouriersList();
            }, (300));

            document.getElementById('updateCouriers').reset();
        }
    }
}

function updateCouriersInDb(couriersInput) {

    $.ajax({
        type: "POST",
        url: "updateCouriers",
        data: JSON.stringify(couriersInput),
        contentType: 'application/json',
        success: function (data) {
            if (data.status === 'OK') {
                console.log('Couriers data update!');
            } else if (data.status === 'NOT EXISTS') {
                alert("Courier with such ID does not exists!");
            } else {
                console.log('Data not update!: ' + data.status + ', ' + data.errorMessage);
            }
        }
    });
}