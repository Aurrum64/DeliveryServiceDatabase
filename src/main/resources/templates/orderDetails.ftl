<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<#import "parts/interface/jumbotron.ftl" as jumbotron>
<@defaultPage.defaultPageTemplate pageName="Order details" heightTop=65 heightBottom=200>
    <@jumbotron.jumbotron image="https://mdbootstrap.com/img/Photos/Others/gradient1.jpg"
    pageName="Order details management page">
    </@jumbotron.jumbotron>
    <div class="container mt-5 ml-5">
        <div class="form-row">
            <div class="form-group col-md-6">
                <form id="searchOrderDetails" class="form-inline">
                    <div class="form-group row">
                        <div class="md-form form-lg ml-4">
                            <input type="text" id="searchOrderDetailsId"
                                   class="form-control form-control-lg">
                            <label for="searchOrderDetailsId">Найти по ID...</label>
                        </div>
                        <div class="md-form form-lg ml-3">
                            <input type="text" id="searchOrderDate" class="form-control form-control-lg">
                            <label for="searchOrderDate">Найти по дате...</label>
                        </div>
                        <div class="md-form form-lg ml-3">
                            <input type="text" id="searchOrderAddress" class="form-control form-control-lg">
                            <label for="searchOrderAddress">Найти по адресу...</label>
                        </div>
                        <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                        <div>
                            <button id="searchOrderDetailsBtn" type="submit" class="btn btn-primary ml-3">Найти</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="container ml-5">
        <div class="form-row">
            <div class="form-group col-md-6">
                <form id="deleteOrderDetails" class="form-inline">
                    <div class="form-group row">
                        <div class="md-form form-lg ml-4">
                            <input type="text" id="deleteOrderDetailsId" <#--id="orderDetailsDeleteForm"-->
                                   class="form-control form-control-lg">
                            <label for="deleteOrderDetailsId">Удалить по ID...</label>
                        </div>
                        <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                        <button id="deleteOrderDetailsBtn" type="submit" class="btn btn-primary ml-3">Удалить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="container mt-3 ml-5">
        <a class="btn btn-primary" data-toggle="collapse" href="#collapse1" role="button" aria-expanded="false"
           aria-controls="collapseExample">
            Открыть меню изменения существующей детальной информации о заказе
        </a>
        <div class="collapse" id="collapse1">
            <div class="form-group mt-3">
                <form id="updateOrderDetails">
                    <div class="md-form form-lg ml-2">
                        <input type="text" id="updateOrderDetailsId" class="form-control form-control-lg">
                        <label for="updateOrderDetailsId">Введите ID детальной информации о заказе, которую нужно
                            изменить...</label>
                    </div>
                    <div class="md-form form-lg ml-2">
                        <input type="text" id="updateOrderDate" class="form-control form-control-lg">
                        <label for="updateOrderDate">Изменить дату доставки...</label>
                    </div>
                    <div class="md-form form-lg ml-2">
                        <input type="text" id="updateOrderAddress" class="form-control form-control-lg">
                        <label for="updateOrderAddress">Изменить адрес доставки...</label>
                    </div>
                    <div class="md-form form-lg ml-2">
                        <input type="text" id="updateComment" class="form-control form-control-lg">
                        <label for="updateComment">Изменить комментарий...</label>
                    </div>
                    <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                    <div class="form-group">
                        <button id="updateOrderDetailsBtn" type="submit" class="btn btn-primary">Изменить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="container mt-5 ml-5">
        <a class="btn btn-primary" data-toggle="collapse" href="#collapse2" role="button" aria-expanded="false"
           aria-controls="collapseExample">
            Добавить детальную информацию о заказе
        </a>
        <div class="collapse" id="collapse2">
            <div class="form-group mt-3">
                <form id="addOrderDetails">
                    <div class="md-form form-lg ml-2">
                        <input type="text" id="addOrderDate" class="form-control form-control-lg">
                        <label for="orderDate">Введите дату доставки...</label>
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
                        <button id="addOrderDetailsBtn" type="submit" class="btn btn-primary">Добавить</button>
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