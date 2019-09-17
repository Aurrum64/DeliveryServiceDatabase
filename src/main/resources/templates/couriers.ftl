<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<#import "parts/interface/jumbotron.ftl" as jumbotron>
<@defaultPage.defaultPageTemplate pageName="Couriers" heightTop=65 heightBottom=200>
    <#include "parts/interface/security.ftl">
    <@jumbotron.jumbotron image="https://mdbootstrap.com/img/Photos/Others/gradient1.jpg"
    pageName="Couriers management page">
    </@jumbotron.jumbotron>
    <div class="container mt-5 ml-5">
        <div class="form-row">
            <div class="form-group col-md-6">
                <form id="searchCouriers" class="form-inline">
                    <div class="form-group row">
                        <div class="md-form form-lg ml-4">
                            <input type="text" id="searchCourierId" class="form-control form-control-lg">
                            <label for="searchCourierId">Найти по ID...</label>
                        </div>
                        <div class="md-form form-lg ml-3">
                            <input type="text" id="searchCourierFirstName" class="form-control form-control-lg">
                            <label for="searchCourierFirstName">Найти по имени...</label>
                        </div>
                        <div class="md-form form-lg ml-3">
                            <input type="text" id="searchCourierLastName" class="form-control form-control-lg">
                            <label for="searchCourierLastName">Найти по фамилии...</label>
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
        <div class="form-row">
            <div class="form-group col-md-6">
                <form id="deleteCouriers" class="form-inline">
                    <div class="form-group row">
                        <div class="md-form form-lg ml-4">
                            <input type="text" id="deleteCourierId" class="form-control form-control-lg">
                            <label for="deleteCourierId">Удалить по ID...</label>
                        </div>
                        <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                        <button type="submit" class="btn btn-primary ml-3">Удалить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="container mt-3 ml-5">
        <a class="btn btn-primary" data-toggle="collapse" href="#collapse1" role="button" aria-expanded="false"
           aria-controls="collapseExample">
            Открыть меню изменения информации о существующем в системе курьере
        </a>
        <div class="collapse" id="collapse1">
            <div class="form-group mt-3">
                <form id="updateCouriers">
                    <div class="form-group">
                        <input type="text" class="form-control" id="updateCourierId"
                               placeholder="Введите ID курьера, информацию о котором нужно изменить..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="updateCourierFirstName"
                               placeholder="Изменить имя курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="updateCourierLastName"
                               placeholder="Изменить фамилию курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="updateCourierEmail"
                               placeholder="Изменить e-mail курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="updateCourierPhoneNumber"
                               placeholder="Изменить телефон курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="updateCourierRating"
                               placeholder="Изменить рейтинг курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="updateCourierSalary"
                               placeholder="Изменить зарплату курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="updateCourierHireDate"
                               placeholder="Изменить дату трудоустройства курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="updateCourierPremium"
                               placeholder="Изменить размер премии курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="updateCourierDepartmentId"
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
    <#if isAdmin || isCourier>
        <div class="container mt-5 ml-5">
            <a class="btn btn-primary" data-toggle="collapse" href="#collapse2" role="button" aria-expanded="false"
               aria-controls="collapse2">
                Открыть меню добавления нового курьера в систему
            </a>
            <div class="collapse" id="collapse2">
                <div class="form-group mt-3">
                    <form id="addCouriers">
                        <div class="form-group">
                            <input type="text" class="form-control" id="addCourierFirstName"
                                   placeholder="Введите имя курьера..."/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="addCourierLastName"
                                   placeholder="Введите фамилию курьера..."/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="addCourierEmail"
                                   placeholder="Введите e-mail курьера..."/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="addCourierPhoneNumber"
                                   placeholder="Введите телефон курьера..."/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="addCourierRating"
                                   placeholder="Введите рейтинг курьера..."/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="addCourierSalary"
                                   placeholder="Введите зарплату курьера..."/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="addCourierHireDate"
                                   placeholder="Введите дату трудоустройства курьера..."/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="addCourierPremium"
                                   placeholder="Введите размер премии курьера..."/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="addCourierDepartmentId"
                                   placeholder="Введите департамент курьера..."/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="addCourierLatitude"
                                   placeholder="Введите широту..."/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="addCourierLongitude"
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
    </#if>
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
            <tbody id="couriersList">
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
    <script src="/js/couriers/addCouriers.js" type="text/javascript"></script>
    <script src="/js/couriers/updateCouriers.js" type="text/javascript"></script>
    <script src="/js/couriers/deleteCouriers.js" type="text/javascript"></script>
    <script src="/js/couriers/searchCouriers.js" type="text/javascript"></script>
</@defaultPage.defaultPageTemplate>