<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<#import "parts/interface/jumbotron.ftl" as jumbotron>
<@defaultPage.defaultPageTemplate pageName="Notifications" heightTop=65 heightBottom=500>
    <@jumbotron.jumbotron image="https://mdbootstrap.com/img/Photos/Others/gradient1.jpg"
    pageName="Notifications">
    </@jumbotron.jumbotron>
    <div class="container mt-5 ml-5">
        <h4>Последние заказы:</h4>
    </div>
    <div class="container mt-5 ml-5">
        <div class="card-columns">
            <#list recentOrders as order>
                <div class="card">
                    <img src="https://i.ibb.co/ZXGmczB/w450h4001385925286-User.png" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">Поступил новый заказ от ${order.authorName}</h5>
                        <p class="card-text">Забрать посылку по адресу: ${order.firstOrderAddressPoint}
                            и доставить по адресу: ${order.secondOrderAddressPoint}<br></p>
                    </div>
                </div>
            </#list>
        </div>
    </div>
    <h4>Последние запросы от пользователей</h4>
    <script src="/js/notifications/updateNotifications.js" type="text/javascript"></script>
</@defaultPage.defaultPageTemplate>