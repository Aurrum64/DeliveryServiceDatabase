<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="ISO-8859-1" />
    <title>PIE</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />

    <!-- Optional theme -->
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />

</head>
<body>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-3d.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>

<div id="container" style="height: 400px"></div>
<script th:inline="javascript">
    chart= new Highcharts.chart('container', {
        chart : {
            type : 'pie',
            options3d : {
                enabled : true,
                alpha : 50,
                beta : 0
            }
        },
        title : {
            text : 'Result Status'
        },
        tooltip : {
            pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions : {
            pie : {
                allowPointSelect : true,
                cursor : 'pointer',
                depth : 35,
                dataLabels : {
                    enabled : true,
                    format : '{point.name}'
                }
            }
        },
        series : [ {
            type : 'pie',
            name : 'Ratio',
            data : [ [ 'Success', [[${pass}]] ], [ 'Failure', [[${fail}]] ]  ]
        } ]
    });
</script>
</body>
</html>