<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<#import "parts/interface/jumbotron.ftl" as jumbotron>
<@defaultPage.defaultPageTemplate pageName="Заказ доставки" heightTop=65 heightBottom=200>
    <@jumbotron.jumbotron image="https://mdbootstrap.com/img/Photos/Others/gradient1.jpg"
    pageName="Заказ доставки">
    </@jumbotron.jumbotron>
    <div class="container mt-5 ml-5">
        <a class="btn btn-primary" data-toggle="collapse" href="#collapse2" role="button" aria-expanded="false"
           aria-controls="collapseExample">
            Сделать заказ
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
                        <button type="submit" class="btn btn-primary">Подтвердить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="container mt-5 ml-5">
        <h4>Ваши активные заказы</h4>
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
            <tbody id="activeOrdersListForUser">
            </tbody>
        </table>
    </div>
    <#include "parts/scriptsSources/orderDetailsScripts.ftl">
</@defaultPage.defaultPageTemplate>