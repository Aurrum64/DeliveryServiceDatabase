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
                               onClick='location.href="/notifications"'>
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
                <div class="card">
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
                <div class="card">
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
                        <a href="/user" class="btn btn-info">Перейти к управлению пользователями</a>
                    </div>
                </div>
            <#else>
                <h4>Список последних заявок от пользователей пока пуст :(</h4>
            </#list>
        </div>
    </div>
    <script src="/js/notifications/updateNotifications.js" type="text/javascript"></script>
</@defaultPage.defaultPageTemplate>