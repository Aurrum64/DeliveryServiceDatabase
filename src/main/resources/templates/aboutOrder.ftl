<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Детализация" heightTop=100 heightBottom=600>
    <div class="text-center mt-5">
        <h3>Детализация заказа: ${order.orderDetailsId}</h3>
    </div>
    <div class="row mt-2 ml-2" style="width: 92%">
        <div class="col-md-12">
            <ul class="stepper stepper-horizontal">
                <#if !specification.isCourierWent()>
                    <li class="completed">
                        <a>
                            <span class="circle">1</span>
                            <span class="label">Поиск курьера</span>
                        </a>
                    </li>
                <#else>
                    <li class="completed">
                        <a>
                            <span class="circle">1</span>
                            <span class="label">Курьер найден</span>
                        </a>
                    </li>
                </#if>
                <#if !specification.isCourierWent()>
                    <li>
                        <a>
                            <span class="circle">2</span>
                            <span class="label">Отправление</span>
                        </a>
                    </li>
                <#else>
                    <li class="completed">
                        <a>
                            <span class="circle">2</span>
                            <span class="label">Курьер уже в пути</span>
                        </a>
                    </li>
                </#if>
                <#if !specification.isOrderPickedUp()>
                    <li>
                        <a>
                            <span class="circle">3</span>
                            <span class="label">Получение заказа</span>
                        </a>
                    </li>
                <#else>
                    <li class="completed">
                        <a>
                            <span class="circle">3</span>
                            <span class="label">Заказ получен</span>
                        </a>
                    </li>
                </#if>
                <#if !specification.isOrderDelivered()>
                    <li>
                        <a>
                            <span class="circle">4</span>
                            <span class="label">Доставка заказа</span>
                        </a>
                    </li>
                <#else>
                    <li class="completed">
                        <a>
                            <span class="circle">4</span>
                            <span class="label">Заказ доставлен</span>
                        </a>
                    </li>
                </#if>
                <#if !specification.isOrderConfirmed()>
                    <li>
                        <a>
                            <span class="circle">5</span>
                            <span class="label">Подтверждение</span>
                        </a>
                    </li>
                <#else>
                    <li class="completed">
                        <a>
                            <span class="circle">5</span>
                            <span class="label">Заказ подтвержден</span>
                        </a>
                    </li>
                </#if>
            </ul>
        </div>
    </div>
    <#if specification.isCourierWent()>
        <div class="card mt-3 ml-5" style="width: 87%">
            <div class="card-body">
                <h5 class="card-title ml-2">На ваш заказ найден курьер: <b>${order.courier.firstName}</b>!</h5>
                <p class="card-text ml-2">При необходимости вы можете связаться с ним по телефону
                    <b>${order.courier.phoneNumber}</b> или написать ему на почту <b>${order.courier.email}</b><br></p>
            </div>
        </div>
    </#if>
    <#if specification.isCourierWent()>
        <div class="card mt-3 ml-5" style="width: 87%">
            <div class="card-body">
                <h5 class="card-title ml-2">Курьер уже в пути!</h5>
                <p class="card-text ml-2">Курьер отправлен забрать заказ по адресу:
                    <b>${order.firstOrderAddressPoint}</b>
                </p>
                <#if !specification.isOrderDelivered()>
                    <form action="/order/orderTracking/${order.orderDetailsId}" method="post">
                        <input type="hidden" name="orderDetailsId" value="${order.orderDetailsId}">
                        <input type="hidden" name="courierId" value="${order.courier.courierId}">
                        <button type="submit" class="btn btn-primary">Отслеживание курьера на карте</button>
                    </form>
                </#if>
            </div>
        </div>
    </#if>
    <#if specification.isOrderPickedUp()>
        <div class="card mt-3 ml-5" style="width: 87%">
            <div class="card-body">
                <h5 class="card-title ml-2">Курьер забрал ваш заказ!</h5>
                <p class="card-text ml-2">Курьер отправлен доставить заказ по адресу:
                    <b>${order.secondOrderAddressPoint}</b><br>
                </p>
            </div>
        </div>
    </#if>
    <#if specification.isOrderDelivered()>
        <div class="card mt-3 ml-5" style="width: 87%">
            <div class="card-body">
                <h5 class="card-title ml-2">Заказ доставлен!</h5>
                <p class="card-text ml-2">Остался последний шаг, вам необходимо подтвердить, что вы получили заказ.</p>
                <#if order.status == "Заказ доставляется" && order.authorName == username>
                    <form action="/order/orderConfirmation" method="post">
                        <input type="hidden" name="source" value="aboutOrderPage">
                        <input type="hidden" name="orderDetailsId" value="${order.orderDetailsId}">
                        <button type="submit" class="btn btn-primary">Подтверждаю, что получил(а) заказ</button>
                    </form>
                </#if>
            </div>
        </div>
    </#if>
    <#if specification.isOrderConfirmed()>
        <div class="card mt-3 ml-5" style="width: 87%">
            <div class="card-body">
                <h5 class="card-title ml-2">Спасибо за то, что выбрали наш сервис!</h5>
                <p class="card-text ml-2">Пожалуйста оставьте комментарий о работе нашего сервиса.</p>
                <#if !order.reviewWritten && order.authorName == username>
                    <form action="/reviews" method="post">
                        <input type="hidden" name="orderDetailsId" value="${order.orderDetailsId}">
                        <input type="hidden" name="authorName" value="${order.authorName}">
                        <button type="submit" class="btn btn-primary">Оставить отзыв</button>
                    </form>
                </#if>
            </div>
        </div>
    </#if>
</@defaultPage.defaultPageTemplate>