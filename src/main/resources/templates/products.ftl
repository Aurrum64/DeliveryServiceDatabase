<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<#import "parts/interface/jumbotron.ftl" as jumbotron>
<@defaultPage.defaultPageTemplate pageName="Products" heightTop=65 heightBottom=200>
    <@jumbotron.jumbotron image="https://mdbootstrap.com/img/Photos/Others/gradient1.jpg"
    pageName="Products management page">
    </@jumbotron.jumbotron>
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
                <form action="/productsFilter" method="post" class="form-inline">
                    <div class="form-group row">
                        <div>
                            <input type="text" name="productId" class="form-control ml-3"
                                   placeholder="Найти по ID..."/>
                        </div>
                        <div>
                            <input type="text" name="title" class="form-control ml-3"
                                   placeholder="Найти по названию..."/>
                        </div>
                        <div>
                            <input type="text" name="price" class="form-control ml-3"
                                   placeholder="Найти по цене..."/>
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
                <form action="/productsDelete" method="post" class="form-inline">
                    <div class="form-group row">
                        <div class="col-sm-6">
                            <input type="text" name="productId" class="form-control" placeholder="Удалить по ID..."/>
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
            Открыть меню изменения информации о существующем в системе товаре
        </a>
        <div class="collapse" id="collapse1">
            <div class="form-group mt-3">
                <form action="/productsUpdate" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" name="productId"
                               placeholder="Введите ID товара, информацию о которой нужно изменить..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="title" placeholder="Изменить название..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="price" placeholder="Изменить цену..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="amount" placeholder="Изменить количество доступных..."/>
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
            Открыть меню добавления нового товара в систему
        </a>
        <div class="collapse" id="collapse2">
            <div class="form-group mt-3">
                <form action="/products" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" name="title" placeholder="Введите название..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="price" placeholder="Введите цену..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="amount" placeholder="Введите количество доступных..."/>
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
                <th scope="col">ID</th>
                <th scope="col">Название</th>
                <th scope="col">Цена</th>
                <th scope="col">Кол-во</th>
            </tr>
            </thead>
            <tbody>
            <#list products as product>
                <tr>
                    <th scope="row">${product.productId}</th>
                    <td>${product.title}</td>
                    <td>${product.price}</td>
                    <td>${product.amount}</td>
                </tr>
            <#else>
                <tr>
                    <th scope="row">List of products is empty yet!</th>
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