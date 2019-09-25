<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<#import "parts/interface/jumbotron.ftl" as jumbotron>
<@defaultPage.defaultPageTemplate pageName="Notifications" heightTop=70 heightBottom=500>
    <div class="container mt-5 ml-5">
        <div class="form-row">
            <div class="form-group col">
                <div class="form-group row">
                    <form>
                        <input type="button" class="btn btn-info ml-3" value="Обновить входящие уведомления"
                               onClick='location.href="/notifications"'>
                    </form>
                    <form>
                        <input type="button" class="btn btn-info ml-5"
                               value="Открыть полный список заявок от пользователей"
                               onClick='location.href="/requests"'>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="container mt-5 ml-5">
        <h3>Последние заказы:</h3>
    </div>
    <div class="container mt-5 ml-5">
        <div class="card-columns">
            <#list recentOrders as order>
                <div class="card mt-2">
                    <div class="card-body">
                        <h5 class="card-title">Поступил новый заказ от <b>${order.authorName}</b>!</h5>
                        <p class="card-text">Забрать посылку по адресу: ${order.firstOrderAddressPoint}
                            и доставить по адресу: ${order.secondOrderAddressPoint}<br></p>
                        <a href="/orderDetails" class="btn btn-info">Перейти в заказы</a>
                    </div>
                </div>
            <#else>
                <h4>Список последних заказов пока пуст :(</h4>
            </#list>
        </div>
    </div>
    <div class="container mt-5 ml-5">
        <h3>Последние заявки от пользователей:</h3>
    </div>
    <div class="container mt-5 ml-5">
        <div class="card-columns">
            <#list recentRequests as request>
                <#if request.isConsidering()>
                    <div class="card mt-2">
                        <div class="card-body">
                            <h5 class="card-title">Поступила новая заявка от <b>${request.authorName}</b>!</h5>
                            <p class="card-text">Данный пользователь хочет устроиться на работу
                                в ваш сервис на должность
                                <#if request.courierRequest>
                                    <b>курьера</b>.
                                </#if>
                                <#if request.managerRequest>
                                    <b>менеджера</b>.
                                </#if>
                                <br></p>
                            <div class="form-group row">
                                <form action="/approveRequest" method="post">
                                    <input type="hidden" name="requestId" value="${request.requestId}">
                                    <#if request.courierRequest>
                                        <input type="hidden" name="professionChoice" value="courier">
                                    <#elseif request.managerRequest>
                                        <input type="hidden" name="professionChoice" value="manager">
                                    </#if>
                                    <input type="hidden" name="authorName" value="${request.authorName}">
                                    <button type="submit" class="btn btn-info ml-3">Одобрить</button>
                                </form>
                                <form action="/rejectRequest" method="post">
                                    <input type="hidden" name="requestId" value="${request.requestId}">
                                    <button type="submit" class="btn btn-info ml-3">Отклонить</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </#if>
            <#else>
                <h4>Список последних заявок от пользователей пока пуст :(</h4>
            </#list>
        </div>
    </div>
</@defaultPage.defaultPageTemplate>