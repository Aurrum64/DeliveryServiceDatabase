<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Order details" heightTop=65 heightBottom=200>
    <div class="container mt-5 ml-5">
        <div class="text mt-5 mb-3">
            <h4>История заказов:</h4>
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
            </tr>
            </thead>
            <tbody id="orderDetailsList">
            </tbody>
        </table>
    </div>
    <#include "parts/scriptsSources/orderDetailsScripts.ftl">
</@defaultPage.defaultPageTemplate>