<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Order details" heightTop=65 heightBottom=200>
    <div class="container mt-5 ml-5">
        <div class="text mt-5 mb-3">
            <h4>Все активные заказы:</h4>
        </div>
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
            <tbody id="allActiveOrdersList">
            </tbody>
        </table>
    </div>
    <#include "parts/scriptsSources/orderDetailsScripts.ftl">
</@defaultPage.defaultPageTemplate>