<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Подробности заказов" heightTop=65 heightBottom=400>
    <#include "parts/interface/security.ftl">
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
                        <div class="md-form form-lg ml-3" style="width: 300px">
                            <input type="text" id="searchSecondOrderAddressPoint" class="form-control form-control-lg"
                                   style="width: 300px">
                            <label for="searchSecondOrderAddressPoint">Найти по адресу доставки...</label>
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
    <#if isAdmin>
        <div class="container ml-5">
            <div class="form-row">
                <div class="form-group col-md-6">
                    <form id="deleteOrderDetails" class="form-inline">
                        <div class="form-group row">
                            <div class="md-form form-lg ml-4">
                                <input type="text" id="deleteOrderDetailsId"
                                       class="form-control form-control-lg">
                                <label for="deleteOrderDetailsId">Удалить по ID...</label>
                            </div>
                            <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                            <button type="submit" class="btn btn-primary ml-3">Удалить</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </#if>
    <#if isAdmin>
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
                            <input type="text" id="updateFirstOrderAddressPoint" class="form-control form-control-lg">
                            <label for="updateFirstOrderAddressPoint">Изменить адрес получения заказа...</label>
                        </div>
                        <div class="md-form form-lg ml-2">
                            <input type="text" id="updateSecondOrderAddressPoint" class="form-control form-control-lg">
                            <label for="updateSecondOrderAddressPoint">Изменить адрес доставки заказа...</label>
                        </div>
                        <div class="md-form form-lg ml-2">
                            <input type="text" id="updateComment" class="form-control form-control-lg">
                            <label for="updateComment">Изменить комментарий...</label>
                        </div>
                        <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary">Изменить</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </#if>
    <#if isAdmin>
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
                            <button type="submit" class="btn btn-primary">Добавить</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </#if>
    <div class="container mt-5 ml-5">
        <div class="text mt-5 mb-3">
            <h3>Детальные сведения о заказах:</h3>
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
            <tbody id="orderDetailsList">
            </tbody>
        </table>
    </div>
    <#include "parts/scriptsSources/orderDetailsScripts.ftl">
</@defaultPage.defaultPageTemplate>