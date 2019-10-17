<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Сделать заказ" heightTop=85 heightBottom=600>
    <div class="container mt-5 ml-5">
        <a class="btn btn-primary" data-toggle="collapse" href="#collapse2" role="button" aria-expanded="false"
           aria-controls="collapseExample">
            Сделать заказ
        </a>
        <div class="collapse" id="collapse2">
            <div class="form-group mt-3">
                <form id="addOrderDetails">
                    <div class="md-form form-lg ml-2">
                        <input type="datetime-local" id="addOrderDate" class="form-control form-control-lg">
                        <label for="orderDate"></label>
                    </div>
                    <div class="md-form form-lg ml-2">
                        <input type="text" id="addFirstOrderAddressPoint" class="form-control form-control-lg">
                        <label for="addFirstOrderAddressPoint">Откуда забрать заказ?</label>
                    </div>
                    <div class="md-form form-lg ml-2">
                        <input type="text" id="addSecondOrderAddressPoint" class="form-control form-control-lg">
                        <label for="addSecondOrderAddressPoint">Куда доставить заказ?</label>
                    </div>
                    <div class="md-form form-lg ml-2">
                        <input type="text" id="addComment" class="form-control form-control-lg">
                        <label for="comment">Введите комментарий...</label>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Подтвердить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="container mt-5 ml-5">
        <h4>Ваши активные заказы:</h4>
        <table class="table table-striped" style="width: 1250px">
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
            <tbody id="activeOrdersListForUser">
            </tbody>
        </table>
    </div>
    <div class="container mt-5 ml-5">
        <h4>Заказы на определенное время:</h4>
        <table class="table table-striped" style="width: 1250px">
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
            <tbody id="waitingOrdersListForUser">
            </tbody>
        </table>
    </div>
    <div class="container mt-5 ml-5">
        <h4>История ваших заказов:</h4>
        <table class="table table-striped" style="width: 1250px">
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
            <tbody id="archiveOrdersListForUser">
            </tbody>
        </table>
    </div>
    <#include "parts/scriptsSources/orderDetailsScripts.ftl">
</@defaultPage.defaultPageTemplate>