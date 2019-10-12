<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Делопроизводство" heightTop=70 heightBottom=400>
    <#include "parts/interface/security.ftl">
    <#if isAdmin || isManager>
        <div class="container mt-4 ml-5">
            <div class="text py-4">
                <h3>Активные заказы:</h3>
            </div>
            <div class="form-row">
                <form>
                    <input type="button" class="btn btn-blue-grey" value="Полный список активных заказов"
                           onClick='location.href="/activeOrderDetails"'>
                </form>
                <form>
                    <input type="button" class="btn btn-blue-grey ml-4" value="Заказы в ожидании"
                           onClick='location.href="/waitingOrderDetails"'>
                </form>
                <form>
                    <input type="button" class="btn btn-blue-grey ml-4" value="История заказов"
                           onClick='location.href="/orderDetailsHistory"'>
                </form>
            </div>
            <table class="table table-striped mt-3" style="width: 1200px">
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
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody id="activeOrdersListForLogisticPage">
                </tbody>
            </table>
            <div class="text py-4">
                <h3>Курьеры, готовые принять заказ:</h3>
            </div>
            <form>
                <input type="button" class="btn btn-blue-grey mr-3" value="Полный список активных курьеров"
                       onClick='location.href="/activeCouriers"'>
            </form>
            <table class="table table-striped mt-3">
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
        <#include "parts/scriptsSources/couriersScripts.ftl">
    <#else>
        <div class="container" style="height: 50px"></div>
        <button id="buildRoute" type="button" class="btn btn-secondary ml-5 mt-3">
            Найти ближайший заказ и проложить маршрут
        </button>
        <button id="move" type="button" class="btn btn-secondary ml-5 mt-3">
            Берусь доставить предложенный системой заказ
        </button>
        <div id="map" class="z-depth-1-half map-container mt-5 ml-5" style="width: 95%; height: 600px">
        </div>
        <#include "parts/scriptsSources/mapScripts.ftl">
        <#include "parts/scriptsSources/orderDetailsScripts.ftl">
        <#include "parts/scriptsSources/couriersScripts.ftl">
    </#if>
</@defaultPage.defaultPageTemplate>