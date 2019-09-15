<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Couriers" heightTop=65 heightBottom=200>
    <div class="jumbotron card card-image"
         style="background-image: url(https://mdbootstrap.com/img/Photos/Others/gradient1.jpg);">
        <div class="text-black text-center py-5 px-4">
            <div>
                <h1 class="card-title h1-responsive pt-3 mb-5 font-bold"><strong>Couriers management page</strong></h1>
            </div>
        </div>
    </div>
    <div class="container mt-5 ml-5">
        <#if filterCheck??>
            <div class="alert alert-danger alert-dismissible fade show" role="alert" style="width: 736px">
                ${filterCheck!}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </#if>
        <div class="form-row">
            <div class="form-group col-md-6">
                <form action="/couriersFilter" method="post" class="form-inline">
                    <div class="form-group row">
                        <div class="md-form form-lg ml-4">
                            <input type="text" name="courierId" id="inputLGE" class="form-control form-control-lg">
                            <label for="inputLGE">Найти по ID...</label>
                        </div>
                        <div class="md-form form-lg ml-3">
                            <input type="text" name="firstName" id="inputLG" class="form-control form-control-lg">
                            <label for="inputLG">Найти по имени...</label>
                        </div>
                        <div class="md-form form-lg ml-3">
                            <input type="text" name="lastName" id="inputL" class="form-control form-control-lg">
                            <label for="inputL">Найти по фамилии...</label>
                        </div>
                        <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                        <div>
                            <button type="submit" class="btn btn-primary ml-3">Найти</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="container ml-5">
        <#if deleteIdCheck??>
            <div class="alert alert-danger alert-dismissible fade show" role="alert" style="width: 305px">
                ${deleteIdCheck!}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </#if>
        <div class="form-row">
            <div class="form-group col-md-6">
                <form action="/couriersDelete" method="post" class="form-inline">
                    <div class="form-group row">
                        <div class="md-form form-lg ml-4">
                            <input type="text" name="courierId" id="input" class="form-control form-control-lg">
                            <label for="input">Удалить по ID...</label>
                        </div>
                        <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                        <button type="submit" class="btn btn-primary ml-3">Удалить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="container mt-3 ml-5">
        <#if updateIdCheck??>
            <div class="alert alert-danger alert-dismissible fade show" role="alert" style="width: 589px">
                ${updateIdCheck!}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </#if>
        <a class="btn btn-primary" data-toggle="collapse" href="#collapse1" role="button" aria-expanded="false"
           aria-controls="collapseExample">
            Открыть меню изменения информации о существующем в системе курьере
        </a>
        <div class="collapse" id="collapse1">
            <div class="form-group mt-3">
                <form action="/couriersUpdate" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" name="courierId"
                               placeholder="Введите ID курьера, информацию о котором нужно изменить..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="firstName" placeholder="Изменить имя курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="lastName"
                               placeholder="Изменить фамилию курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="email" placeholder="Изменить e-mail курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="phoneNumber"
                               placeholder="Изменить телефон курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="rating"
                               placeholder="Изменить рейтинг курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="salary"
                               placeholder="Изменить зарплату курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="hireDate"
                               placeholder="Изменить дату трудоустройства курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="premium"
                               placeholder="Изменить размер премии курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="departmentId"
                               placeholder="Изменить департамент курьера..."/>
                    </div>
                    <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
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
            Открыть меню добавления нового курьера в систему
        </a>
        <div class="collapse" id="collapse2">
            <div class="form-group mt-3">
                <form action="/couriers" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" name="firstName" placeholder="Введите имя курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="lastName"
                               placeholder="Введите фамилию курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="email" placeholder="Введите e-mail курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="phoneNumber"
                               placeholder="Введите телефон курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="rating" placeholder="Введите рейтинг курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="salary"
                               placeholder="Введите зарплату курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="hireDate"
                               placeholder="Введите дату трудоустройства курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="premium"
                               placeholder="Введите размер премии курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="departmentId"
                               placeholder="Введите департамент курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="latitude"
                               placeholder="Введите широту..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="longitude"
                               placeholder="Введите долготу..."/>
                    </div>
                    <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
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
                <th scope="col">Имя</th>
                <th scope="col">Фамилия</th>
                <th scope="col">Почта</th>
                <th scope="col">Сотовый</th>
                <th scope="col">Рейтинг</th>
                <th scope="col">Зарплата</th>
                <th scope="col">Трудоустроился</th>
                <th scope="col">Премия</th>
                <th scope="col">Департамент</th>
                <th scope="col">X</th>
                <th scope="col">Y</th>
                <th scope="col">Автор</th>
            </tr>
            </thead>
            <tbody>
            <#list couriers as courier>
                <tr>
                    <th scope="row">${courier.courierId}</th>
                    <td>${courier.firstName}</td>
                    <td>${courier.lastName}</td>
                    <td>${courier.email}</td>
                    <td>${courier.phoneNumber}</td>
                    <td>${courier.rating}</td>
                    <td>${courier.salary}</td>
                    <td>${courier.hireDate}</td>
                    <td>${courier.premium}</td>
                    <td>${courier.departmentId}</td>
                    <td>${courier.latitude}</td>
                    <td>${courier.longitude}</td>
                    <td>${courier.authorName}</td>
                </tr>
            <#else>
                <tr>
                    <th scope="row">List of couriers is empty yet!</th>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <div class="container" style="height: 50px">
    </div>
    <div class="jumbotron jumbotron-fluid mt-5">
        <div class="text-black text-center">
            <h5 class="display-4">Delivery map</h5>
        </div>
    </div>
    <div id="map" class="z-depth-1-half map-container ml-5 mt-5" style="width: 94%; height: 600px"></div>
    <div class="form-group row mt-5 ml-5">
        <div class="btn-group" role="group">
            <button id="btnGroupDrop1" type="button" class="btn btn-secondary dropdown-toggle ml-5 mt-3"
                    data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                Курьеры
            </button>
            <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                <a class="dropdown-item" id="couriersCoordinates">Показать курьеров на карте</a>
                <a class="dropdown-item" id="hideCouriersMarkers">Скрыть курьеров</a>
            </div>
        </div>
        <div class="btn-group" role="group">
            <button id="btnGroupDrop2" type="button" class="btn btn-secondary dropdown-toggle ml-5 mt-3"
                    data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                Активные заказы
            </button>
            <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                <a class="dropdown-item" id="deliveryCoordinates">Показать активные заказы на карте</a>
                <a class="dropdown-item" id="hideNotDeliveredOrderPoints">Скрыть активные заказы</a>
            </div>
        </div>
        <div class="btn-group" role="group">
            <button id="btnGroupDrop3" type="button" class="btn btn-secondary dropdown-toggle ml-5 mt-3"
                    data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                Архивные заказы
            </button>
            <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                <a class="dropdown-item" id="showDeliveredOrders">Показать архивные заказы на карте</a>
                <a class="dropdown-item" id="hideDeliveredOrderPoints">Скрыть архивные заказы</a>
            </div>
        </div>
        <div class="btn-group" role="group">
            <button id="btnGroupDrop4" type="button" class="btn btn-secondary dropdown-toggle ml-5 mt-3"
                    data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                Зоны доставки
            </button>
            <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                <a class="dropdown-item" id="showDeliveryZones">Показать зоны доставки на карте</a>
                <a class="dropdown-item" id="hideDeliveryZones">Скрыть зоны доставки</a>
            </div>
        </div>
        <button id="buildRoute" type="button" class="btn btn-secondary ml-5 mt-3">
            Построить маршрут
        </button>
        <button id="move" type="button" class="btn btn-secondary ml-5 mt-3">
            В путь!
        </button>
    </div>
    <#include "parts/map/mapScriptsSources.ftl">
</@defaultPage.defaultPageTemplate>