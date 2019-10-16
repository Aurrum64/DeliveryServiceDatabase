<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Все активные курьеры" heightTop=95 heightBottom=600>
    <div class="container mt-5 ml-5">
        <div class="text mt-5">
            <h3>Полный список активных курьеров:</h3>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <form id="searchCouriers" class="form-inline">
                    <div class="form-group row">
                        <div class="md-form form-lg ml-3">
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
                        <div>
                            <button type="submit" class="btn btn-primary ml-3">Найти</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
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
                <th scope="col">Готовность</th>
            </tr>
            </thead>
            <tbody id="activeCouriersList">
            </tbody>
        </table>
    </div>
    <#include "parts/scriptsSources/couriersScripts.ftl">
</@defaultPage.defaultPageTemplate>