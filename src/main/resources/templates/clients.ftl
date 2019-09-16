<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<#import "parts/interface/jumbotron.ftl" as jumbotron>
<@defaultPage.defaultPageTemplate pageName="Clients" heightTop=65 heightBottom=200>
    <@jumbotron.jumbotron image="https://mdbootstrap.com/img/Photos/Others/gradient1.jpg"
    pageName="Clients management page">
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
                <form action="/clientsFilter" method="post" class="form-inline">
                    <div class="form-group row">
                        <div>
                            <input type="text" name="surname" class="form-control ml-3"
                                   placeholder="Найти по фамилии..."/>
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
                <form action="/clientsDelete" method="post" class="form-inline">
                    <div class="form-group row">
                        <div class="col-sm-6">
                            <input type="text" name="clientId" class="form-control" placeholder="Удалить по ID..."/>
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
            Открыть меню изменения информации о существующем в системе клиенте
        </a>
        <div class="collapse" id="collapse1">
            <div class="form-group mt-3">
                <form action="/clientsUpdate" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" name="clientId"
                               placeholder="Введите ID клиента, информацию о котором нужно изменить..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="name" placeholder="Изменить имя клиента..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="surname"
                               placeholder="Изменить фамилию клиента..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="email" placeholder="Изменить e-mail клиента..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="telephone"
                               placeholder="Изменить телефон клиента..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="rating"
                               placeholder="Изменить рейтинг клиента..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="address"
                               placeholder="Изменить адрес клиента..."/>
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
            Открыть меню добавления нового клиента в систему
        </a>
        <div class="collapse" id="collapse2">
            <div class="form-group mt-3">
                <form action="/clients" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" name="name" placeholder="Введите имя клиента..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="surname"
                               placeholder="Введите фамилию клиента..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="email" placeholder="Введите e-mail клиента..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="telephone"
                               placeholder="Введите телефон клиента..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="rating" placeholder="Введите рейтинг клиента..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="address"
                               placeholder="Введите адрес клиента..."/>
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
                <th scope="col">Имя</th>
                <th scope="col">Фамилия</th>
                <th scope="col">Почта</th>
                <th scope="col">Сотовый</th>
                <th scope="col">Рейтинг</th>
                <th scope="col">Адрес</th>
            </tr>
            </thead>
            <tbody>
            <#list clients as client>
                <tr>
                    <th scope="row">${client.clientId}</th>
                    <td>${client.name}</td>
                    <td>${client.surname}</td>
                    <td>${client.email}</td>
                    <td>${client.telephone}</td>
                    <td>${client.rating}</td>
                    <td>${client.address}</td>
                </tr>
            <#else>
                <tr>
                    <th scope="row">List of clients is empty yet!</th>
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