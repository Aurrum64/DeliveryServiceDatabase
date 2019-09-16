<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<#import "parts/interface/jumbotron.ftl" as jumbotron>
<@defaultPage.defaultPageTemplate pageName="Orders" heightTop=65 heightBottom=200>
    <@jumbotron.jumbotron image="https://mdbootstrap.com/img/Photos/Others/gradient1.jpg"
    pageName="Orders management page">
    </@jumbotron.jumbotron>
    <div class="container mt-5 ml-5">
        <#if filterCheck??>
            <div class="alert alert-danger" role="alert" style="width: 736px">
                ${filterCheck!}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </#if>
        <div class="form-row">
            <div class="form-group col-md-6">
                <form action="/ordersFilter" method="post" class="form-inline">
                    <div class="form-group row">
                        <div>
                            <input type="text" name="paymentMethod" class="form-control ml-3"
                                   placeholder="Найти по способу оплаты..."/>
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
    <div class="container mt-5 ml-5">
        <#if deleteIdCheck??>
            <div class="alert alert-danger" role="alert" style="width: 305px">
                ${deleteIdCheck!}
            </div>
        </#if>
        <div class="form-row">
            <div class="form-group col-md-6">
                <form action="/ordersDelete" method="post" class="form-inline">
                    <div class="form-group row">
                        <div class="col-sm-6">
                            <input type="text" name="orderId" class="form-control" placeholder="Удалить по ID..."/>
                        </div>
                    </div>
                    <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                    <button type="submit" class="btn btn-primary ml-3">Удалить</button>
                </form>
            </div>
        </div>
    </div>
    <div class="container mt-5 ml-5">
        <#if updateIdCheck??>
            <div class="alert alert-danger" role="alert" style="width: 589px">
                ${updateIdCheck!}
            </div>
        </#if>
        <a class="btn btn-primary" data-toggle="collapse" href="#collapse1" role="button" aria-expanded="false"
           aria-controls="collapseExample">
            Открыть меню изменения информации о существующем в системе заказе
        </a>
        <div class="collapse" id="collapse1">
            <div class="form-group mt-3">
                <form action="/ordersUpdate" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" name="orderId"
                               placeholder="Введите ID заказа, информацию о котором нужно изменить..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="departmentId"
                               placeholder="Изменить ID департамента..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="managerId"
                               placeholder="Изменить ID менеджера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="courierId" placeholder="Изменить ID курьера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="paymentMethod"
                               placeholder="Изменить способ оплаты..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="orderPrice"
                               placeholder="Изменить стоимость заказа..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="discount"
                               placeholder="Изменить скидку..."/>
                    </div>
                    <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Изменить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="container mt-5 ml-5">
        <a class="btn btn-primary" data-toggle="collapse" href="#collapse2" role="button" aria-expanded="false"
           aria-controls="collapse2">
            Открыть меню добавления нового заказа в систему
        </a>
        <div class="collapse" id="collapse2">
            <div class="form-group mt-3">
                <form action="/orders" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" name="paymentMethod"
                               placeholder="Введите способ оплаты..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="orderPrice"
                               placeholder="Введите стоимость заказа..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="discount" placeholder="Введите скидку..."/>
                    </div>
                    <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Добавить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="container mt-5 ml-5">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Order ID</th>
                <th scope="col">Department ID</th>
                <th scope="col">Manager ID</th>
                <th scope="col">Courier ID</th>
                <th scope="col">Способ оплаты</th>
                <th scope="col">Стоимость заказа</th>
                <th scope="col">Скидка</th>
            </tr>
            </thead>
            <tbody>
            <#list orders as order>
                <tr>
                    <th scope="row">${order.orderId}</th>
                    <td>${order.departmentId}</td>
                    <td>${order.managerId}</td>
                    <td>${order.courierId}</td>
                    <td>${order.paymentMethod}</td>
                    <td>${order.orderPrice}</td>
                    <td>${order.discount}</td>
                </tr>
            <#else>
                <tr>
                    <th scope="row">List of orders is empty yet!</th>
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
</@defaultPage.defaultPageTemplate>