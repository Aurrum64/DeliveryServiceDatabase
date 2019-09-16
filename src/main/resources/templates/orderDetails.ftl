<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Order details" heightTop=65 heightBottom=200>
    <div class="jumbotron card card-image"
         style="background-image: url(https://mdbootstrap.com/img/Photos/Others/gradient1.jpg);">
        <div class="text-black text-center py-5 px-4">
            <div>
                <h1 class="card-title h1-responsive pt-3 mb-5 font-bold"><strong>Order details management page</strong>
                </h1>
            </div>
        </div>
    </div>
    <div class="container mt-5 ml-5">
        <#if filterCheck??>
            <div class="alert alert-danger alert-dismissible fade show" role="alert" style="width: 736px">
                ${filterCheck!}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </#if>
        <div class="form-row">
            <div class="form-group col-md-6">
                <form action="/orderDetailsFilter" method="post" class="form-inline">
                    <div class="form-group row">
                        <div class="md-form form-lg ml-4">
                            <input type="text" name="orderDetailsId" id="inputLGEx"
                                   class="form-control form-control-lg">
                            <label for="inputLGEx">Найти по ID...</label>
                        </div>
                        <div class="md-form form-lg ml-3">
                            <input type="text" name="orderDate" id="inputLGE" class="form-control form-control-lg">
                            <label for="inputLGE">Найти по дате...</label>
                        </div>
                        <div class="md-form form-lg ml-3">
                            <input type="text" name="orderAddress" id="inputLG" class="form-control form-control-lg">
                            <label for="inputLG">Найти по адресу...</label>
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
    <div class="container ml-5">
        <#if deleteIdCheck??>
            <div class="alert alert-danger alert-dismissible fade show" role="alert" style="width: 305px">
                ${deleteIdCheck!}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </#if>
        <div class="form-row">
            <div class="form-group col-md-6">
                <form action="/orderDetailsDelete" method="post" class="form-inline">
                    <div class="form-group row">
                        <div class="md-form form-lg ml-4">
                            <input type="text" name="orderDetailsId" id="inputL" class="form-control form-control-lg">
                            <label for="inputL">Удалить по ID...</label>
                        </div>
                        <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                        <button type="submit" class="btn btn-primary ml-3">Удалить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="container mt-3 ml-5">
        <#if updateIdCheck??>
            <div class="alert alert-danger alert-dismissible fade show" role="alert" style="width: 579px">
                ${updateIdCheck!}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </#if>
        <a class="btn btn-primary" data-toggle="collapse" href="#collapse1" role="button" aria-expanded="false"
           aria-controls="collapseExample">
            Открыть меню изменения существующей детальной информации о заказе
        </a>
        <div class="collapse" id="collapse1">
            <div class="form-group mt-3">
                <form id="updateOrderDetails">
                    <div class="form-group">
                        <input type="text" class="form-control" id="updateOrderDetailsId"
                               placeholder="Введите ID детальной информации о заказе, которую нужно изменить..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="updateOrderDate"
                               placeholder="Изменить дату доставки..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="updateOrderAddress"
                               placeholder="Изменить адрес доставки..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="updateComment" placeholder="Изменить комментарий..."/>
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
                        <input type="text" id="addOrderAddress" class="form-control form-control-lg">
                        <label for="orderAddress">Введите адрес доставки...</label>
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
                <th scope="col">Адрес доставки</th>
                <th scope="col">Комментарий</th>
                <th scope="col">Статус доставки</th>
                <th scope="col">Автор</th>
            </tr>
            </thead>
            <tbody id="orderDetailsList">
            </tbody>
        </table>
    </div>
    <script src="/js/orderDetails/updateOrderDetails.js" type="text/javascript"></script>
    <script src="/js/orderDetails/addOrderDetails.js" type="text/javascript"></script>
</@defaultPage.defaultPageTemplate>