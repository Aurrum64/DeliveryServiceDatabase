<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<#import "parts/interface/jumbotron.ftl" as jumbotron>
<@defaultPage.defaultPageTemplate pageName="Notifications" heightTop=65 heightBottom=200>
    <@jumbotron.jumbotron image="https://mdbootstrap.com/img/Photos/Others/gradient1.jpg"
    pageName="Notifications">
    </@jumbotron.jumbotron>
    <div class="container ml-5 mt-5">
        <form id="updateNotificationsList">
            <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Обновить входящие уведомления</button>
            </div>
        </form>
    </div>
    <div class="container ml-5 mt-5" style="height: 200px; width: 415px;">
    <div class="card">
        <img class="card-img-top" src="https://i.pinimg.com/originals/43/c5/2c/43c52c8ab8227fb42bdfcbec089e5c60.png"
             alt="Card image cap">
        <div class="card-body">
            <h4 class="card-title ml-2"><a>У вас новое уведомление!</a></h4>
            <p class="card-text ml-2">Новый пользователь хочет зарегистрироваться в системе.</p>
            <#--<a href="#" class="btn btn-primary">Подтвердить</a>
            <a href="#" class="btn btn-primary">Отказать</a>-->
            <div class="form-group row">
                <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                <div>
                    <button type="submit" class="btn btn-primary ml-4">Подтвердить</button>
                </div>
                <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                <div>
                    <button type="submit" class="btn btn-primary ml-4">Отказать</button>
                </div>
            </div>
        </div>
    </div>
    <#--    <div id="notificationsList">
            <div class="card">
                <img class="card-img-top" src="https://mdbootstrap.com/img/Photos/Others/images/43.jpg"
                     alt="Card image cap">
                <div class="card-body">
                    <h4 class="card-title"><a>Card title</a></h4>
                    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the
                        card's content.</p>
                    <a href="#" class="btn btn-primary">Button</a>
                </div>
            </div>
        </div>-->
    <script src="/js/notifications/updateNotifications.js" type="text/javascript"></script>
</@defaultPage.defaultPageTemplate>