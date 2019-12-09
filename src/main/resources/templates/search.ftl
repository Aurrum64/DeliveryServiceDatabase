<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<#import "parts/interface/jumbotron.ftl" as jumbotron>
<@defaultPage.defaultPageTemplate pageName="Search" heightTop=65 heightBottom=200>
    <@jumbotron.jumbotron image="https://mdbootstrap.com/img/Photos/Others/gradient1.jpg"
    pageName="Search page">
    </@jumbotron.jumbotron>
    <div class="container ml-3">
        <div class="text">
            <h3>Выберите таблицу для поиска:</h3>
        </div>
        <form action="/search?table=managers" method="post">
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Менеджеры</button>
            </div>
        </form>
        <form action="/search?table=products" method="post">
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Товары</button>
            </div>
        </form>
        <form action="/search?table=orderdetails" method="post">
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Детали заказа</button>
            </div>
        </form>
        <form action="/search?table=couriers" method="post">
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Курьеры</button>
            </div>
        </form>
        <form action="/search?table=locations" method="post">
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Места</button>
            </div>
        </form>
    </div>

</@defaultPage.defaultPageTemplate>