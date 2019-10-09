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
                    <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
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
            <tbody>
            <#list archiveOrdersListForCurrentUser as archiveOrder>
                <tr>
                    <th scope="row">${archiveOrder.orderDetailsId}</th>
                    <td>${archiveOrder.orderDate}</td>
                    <td>${archiveOrder.firstOrderAddressPoint}</td>
                    <td>${archiveOrder.secondOrderAddressPoint}</td>
                    <td>${archiveOrder.comment}</td>
                    <td>${archiveOrder.status}</td>
                    <td>${archiveOrder.authorName}</td>
                    <td>${archiveOrder.courier.firstName}</td>
                    <td><a href="/order/${archiveOrder.orderDetailsId}">Подробнее</a></td>
                    <#if !archiveOrder.isReviewWritten()>
                        <td>
                            <form action="/reviews" method="post">
                                <input type="hidden" name="orderDetailsId" value="${archiveOrder.orderDetailsId}">
                                <input type="hidden" name="authorName" value="${archiveOrder.authorName}">
                                <button type="submit" class="btn btn-info ml-3">Оставить отзыв</button>
                            </form>
                        </td>
                    </#if>
                </tr>
            <#else>
                <tr>
                    <th scope="row">В вашей истории заказов пока нет ни одного заказа :(</th>
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
    <#include "parts/scriptsSources/orderDetailsScripts.ftl">
</@defaultPage.defaultPageTemplate>