function sendNotificationToManager(detailsInfoForNotification) {

    $.ajax({
        type: "POST",
        url: "/addNotifications",
        data: JSON.stringify(detailsInfoForNotification),
        contentType: 'application/json',
        success: function (data) {
            if (data.status === 'OK') {
                console.log('Notification successfully send!');
            } else {
                console.log('Can\'t send notification!: ' + data.status + ', ' + data.errorMessage);
            }
        }
    });
}