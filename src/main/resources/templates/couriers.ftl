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
                    <div class="md-form form-lg ml-2">
                        <input type="text" id="updateCourierId" class="form-control form-control-lg">
                        <label for="updateCourierId">Введите ID курьера, информацию о котором нужно изменить...</label>
                    </div>
                    <div class="md-form form-lg ml-2">
                        <input type="text" id="updateCourierFirstName" class="form-control form-control-lg">
                        <label for="updateCourierFirstName">Изменить имя курьера...</label>
                    </div>
                    <div class="md-form form-lg ml-2">
                        <input type="text" id="updateCourierLastName" class="form-control form-control-lg">
                        <label for="updateCourierLastName">Изменить фамилию курьера...</label>
                    </div>
                    <div class="md-form form-lg ml-2">
                        <input type="text" id="updateCourierEmail" class="form-control form-control-lg">
                        <label for="updateCourierEmail">Изменить e-mail курьера...</label>
                    </div>
                    <div class="md-form form-lg ml-2">
                        <input type="text" id="updateCourierPhoneNumber" class="form-control form-control-lg">
                        <label for="updateCourierPhoneNumber">Изменить телефон курьера...</label>
                    </div>
                    <div class="md-form form-lg ml-2">
                        <input type="text" id="updateCourierRating" class="form-control form-control-lg">
                        <label for="updateCourierRating">Изменить рейтинг курьера...</label>
                    </div>
                    <div class="md-form form-lg ml-2">
                        <input type="text" id="updateCourierSalary" class="form-control form-control-lg">
                        <label for="updateCourierSalary">Изменить зарплату курьера...</label>
                    </div>
                    <div class="md-form form-lg ml-2">
                        <input type="text" id="updateCourierHireDate" class="form-control form-control-lg">
                        <label for="updateCourierHireDate">Изменить дату трудоустройства курьера...</label>
                    </div>
                    <div class="md-form form-lg ml-2">
                        <input type="text" id="updateCourierPremium" class="form-control form-control-lg">
                        <label for="updateCourierPremium">Изменить размер премии курьера...</label>
                    </div>
                    <div class="md-form form-lg ml-2">
                        <input type="text" id="updateCourierDepartmentId" class="form-control form-control-lg">
                        <label for="updateCourierDepartmentId">Изменить департамент курьера...</label>
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
                        <div class="md-form form-lg ml-2">
                            <input type="text" id="addCourierFirstName" class="form-control form-control-lg">
                            <label for="addCourierFirstName">Добавить имя курьера...</label>
                        </div>
                        <div class="md-form form-lg ml-2">
                            <input type="text" id="addCourierLastName" class="form-control form-control-lg">
                            <label for="addCourierLastName">Добавить фамилию курьера...</label>
                        </div>
                        <div class="md-form form-lg ml-2">
                            <input type="text" id="addCourierEmail" class="form-control form-control-lg">
                            <label for="addCourierEmail">Добавить электронную почту курьера...</label>
                        </div>
                        <div class="md-form form-lg ml-2">
                            <input type="text" id="addCourierPhoneNumber" class="form-control form-control-lg">
                            <label for="addCourierPhoneNumber">Добавить номер телефона курьера...</label>
                        </div>
                        <div class="md-form form-lg ml-2">
                            <input type="text" id="addCourierRating" class="form-control form-control-lg">
                            <label for="addCourierRating">Добавить рейтинг курьера...</label>
                        </div>
                        <div class="md-form form-lg ml-2">
                            <input type="text" id="addCourierSalary" class="form-control form-control-lg">
                            <label for="addCourierSalary">Добавить зарплату курьера...</label>
                        </div>
                        <div class="md-form form-lg ml-2">
                            <input type="text" id="addCourierHireDate" class="form-control form-control-lg">
                            <label for="addCourierHireDate">Добавить дату трудоустройства курьера...</label>
                        </div>
                        <div class="md-form form-lg ml-2">
                            <input type="text" id="addCourierPremium" class="form-control form-control-lg">
                            <label for="addCourierPremium">Добавить премиальные курьера...</label>
                        </div>
                        <div class="md-form form-lg ml-2">
                            <input type="text" id="addCourierDepartmentId" class="form-control form-control-lg">
                            <label for="addCourierDepartmentId">Добавить департамент курьера...</label>
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
    <#include "parts/interface/map.ftl">
    <#include "parts/scriptsSources/mapScripts.ftl">
    <#include "parts/scriptsSources/couriersScripts.ftl">
</@defaultPage.defaultPageTemplate>