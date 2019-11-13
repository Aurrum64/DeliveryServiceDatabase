$(function() {
chart = new Highcharts.chart('container', {
        chart: {
            type: 'line',
            width: 500
        },

        title: {
            text: 'Width is set to 300px'
        },

        xAxis: {
            categories: ["Jan", "Feb"]
        },

        tooltip: {
            formatter: function() {
                return '<strong>'+this.x+': </strong>'+ this.y;
            }
        },

        series: [{
            data: [10,20]
        }]
    });
}
    )

