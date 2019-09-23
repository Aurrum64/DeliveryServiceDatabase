<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<#import "parts/interface/jumbotron.ftl" as jumbotron>
<@defaultPage.defaultPageTemplate pageName="Заказ доставки" heightTop=65 heightBottom=200>
    <@jumbotron.jumbotron image="https://mdbootstrap.com/img/Photos/Others/gradient1.jpg"
    pageName="Заказ доставки">
    </@jumbotron.jumbotron>
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
                        <label for="orderAddress">Откуда нужно доставить?</label>
                    </div>
                    <div class="md-form form-lg ml-2">
                        <input type="text" id="addOrderAddress" class="form-control form-control-lg">
                        <label for="orderAddress">Куда нужно доставить?</label>
                    </div>
                    <div class="md-form form-lg ml-2">
                        <input type="text" id="addComment" class="form-control form-control-lg">
                        <label for="comment">Введите комментарий...</label>
                    </div>
                    <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                    <div class="form-group">
                        <button id="addOrderDetailsBtn" type="submit" class="btn btn-primary">Заказать доставку</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</@defaultPage.defaultPageTemplate>