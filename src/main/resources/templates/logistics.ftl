<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<#import "parts/interface/jumbotron.ftl" as jumbotron>
<@defaultPage.defaultPageTemplate pageName="Logistics" heightTop=70 heightBottom=400>
    <div class="container mt-5 ml-5">
        <div class="text mt-5 mb-3">
            <h3>Активные заказы:</h3>
        </div>
        <div class="form-row">
            <form>
                <input type="button" class="btn btn-blue-grey mr-3" value="История заказов"
                       onClick='location.href="/orderDetailsHistory"'>
            </form>
            <form>
                <input type="button" class="btn btn-blue-grey mr-3" value="Полный список активных заказов"
                       onClick='location.href="/activeOrderDetails"'>
            </form>
        </div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Дата доставки</th>
                <th scope="col">Откуда</th>
                <th scope="col">Куда</th>
                <th scope="col">Комментарий</th>
                <th scope="col">Статус доставки</th>
                <th scope="col">Автор</th>
                <th scope="col">Курьер</th>
            </tr>
            </thead>
            <tbody id="activeOrdersListForLogisticPage">
            </tbody>
        </table>
        <div class="text mt-5 mb-3">
            <h3>Курьеры, готовые принять заказ:</h3>
        </div>
        <div class="form-row">
            <form>
                <input type="button" class="btn btn-blue-grey mr-3" value="Список выходных курьеров"
                       onClick='location.href="/activeCouriers"'>
            </form>
            <form>
                <input type="button" class="btn btn-blue-grey mr-3" value="Полный список активных курьеров"
                       onClick='location.href="/restCouriers"'>
            </form>
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
            <tbody id="activeCouriersListForLogisticsPage">
            </tbody>
        </table>
    </div>
    <#include "parts/scriptsSources/orderDetailsScripts.ftl">
    <#include "parts/interface/map.ftl">
    <#include "parts/scriptsSources/mapScripts.ftl">
    <#include "parts/scriptsSources/couriersScripts.ftl">
</@defaultPage.defaultPageTemplate>