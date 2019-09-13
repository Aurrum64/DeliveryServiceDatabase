<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    {{> interfaceParts/bootstrapHeaderLinks}}
    <title>Locations</title>
</head>
<body>
{{> interfaceParts/bootstrapNavBarWithSignBtn}}
<div class="container" style="height: 70px">
</div>
<div class="jumbotron card card-image"
     style="background-image: url(https://mdbootstrap.com/img/Photos/Others/gradient1.jpg);">
    <div class="text-black text-center py-5 px-4">
        <div>
            <h1 class="card-title h1-responsive pt-3 mb-5 font-bold"><strong>Locations management page</strong></h1>
        </div>
    </div>
</div>
<div class="container mt-5 ml-5">
    {{#filterCheck}}
        <div class="alert alert-danger alert-dismissible fade show" role="alert" style="width: 736px">
            {{filterCheck}}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    {{/filterCheck}}
    <div class="form-row">
        <div class="form-group col-md-6">
            <form action="/locationsFilter" method="post" class="form-inline">
                <div class="form-group row">
                    <div>
                        <input type="text" name="locationId" class="form-control ml-3"
                               placeholder="Найти по ID..."/>
                    </div>
                    <div>
                        <input type="text" name="street" class="form-control ml-3"
                               placeholder="Найти по улице..."/>
                    </div>
                    <div>
                        <input type="text" name="building" class="form-control ml-3"
                               placeholder="Найти по номеру дома..."/>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div>
                        <button type="submit" class="btn btn-primary ml-3">Найти</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="container mt-5 ml-5">
    {{#deleteIdCheck}}
        <div class="alert alert-danger" role="alert" style="width: 305px">
            {{deleteIdCheck}}
        </div>
    {{/deleteIdCheck}}
    <div class="form-row">
        <div class="form-group col-md-6">
            <form action="/locationsDelete" method="post" class="form-inline">
                <div class="form-group row">
                    <div class="col-sm-6">
                        <input type="text" name="locationId" class="form-control" placeholder="Удалить по ID..."/>
                    </div>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary ml-3">Удалить</button>
            </form>
        </div>
    </div>
</div>
<div class="container mt-5 ml-5">
    {{#updateIdCheck}}
        <div class="alert alert-danger" role="alert" style="width: 589px">
            {{updateIdCheck}}
        </div>
    {{/updateIdCheck}}
    <a class="btn btn-primary" data-toggle="collapse" href="#collapse1" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Открыть меню изменения информации о существующей в системе локации
    </a>
    <div class="collapse" id="collapse1">
        <div class="form-group mt-3">
            <form action="/locationsUpdate" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" name="locationId"
                           placeholder="Введите ID локации, информацию о которой нужно изменить..."/>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="city" placeholder="Изменить город..."/>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="district" placeholder="Изменить район..."/>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="street" placeholder="Изменить улицу..."/>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="building" placeholder="Изменить номер дома..."/>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Изменить</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="container mt-5 ml-5">
    <a class="btn btn-primary" data-toggle="collapse" href="#collapse2" role="button" aria-expanded="false"
       aria-controls="collapse2">
        Открыть меню добавления новой локации в систему
    </a>
    <div class="collapse" id="collapse2">
        <div class="form-group mt-3">
            <form action="/locations" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" name="city" placeholder="Введите город..."/>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="district" placeholder="Введите район..."/>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="street" placeholder="Введите имя улицу..."/>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="building" placeholder="Введите номер дома..."/>
                </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="container mt-5 ml-5">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Город</th>
            <th scope="col">Район</th>
            <th scope="col">Улица</th>
            <th scope="col">Здание</th>
        </tr>
        </thead>
        <tbody>
        {{#locations}}
            <tr>
                <th scope="row">{{locationId}}</th>
                <td>{{city}}</td>
                <td>{{district}}</td>
                <td>{{street}}</td>
                <td>{{building}}</td>
            </tr>
        {{/locations}}
        {{^locations}}
            <tr>
                <th scope="row">List of locations is empty yet!</th>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
        {{/locations}}
        </tbody>
    </table>
</div>
<div class="container" style="height: 500px">
</div>
<footer class="page-footer font-small black">
    <div class="footer-copyright text-center py-3">© Delivery Service, 2019:</div>
</footer>
{{> interfaceParts/bootstrapFooterScripts}}
</body>
</html>