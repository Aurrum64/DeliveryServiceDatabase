<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Call centre" heightTop=65 heightBottom=200>
    <div class="jumbotron card card-image"
         style="background-image: url(https://mdbootstrap.com/img/Photos/Others/gradient1.jpg);">
        <div class="text-black text-center py-5 px-4">
            <div>
                <h1 class="card-title h1-responsive pt-3 mb-5 font-bold"><strong>CallCentres management page</strong>
                </h1>
            </div>
        </div>
    </div>
    <div class="container mt-5 ml-5">
        <#if filterCheck??>
        <#--{{#filterCheck}}-->
            <div class="alert alert-danger" role="alert" style="width: 736px">
                ${filterCheck!}
                <#--{{filterCheck}}-->
            </div>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        <#--{{/filterCheck}}-->
        </#if>
        <div class="form-row">
            <div class="form-group col-md-6">
                <form action="/callcentreFilter" method="post" class="form-inline">
                    <div class="form-group row">
                        <div>
                            <input type="text" name="name" class="form-control ml-3"
                                   placeholder="Найти по названию..."/>
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
        <#--{{#deleteIdCheck}}-->
            <div class="alert alert-danger" role="alert" style="width: 305px">
                ${deleteIdCheck!}
                <#--{{deleteIdCheck}}-->
            </div>
        <#--{{/deleteIdCheck}}-->
        </#if>
        <div class="form-row">
            <div class="form-group col-md-6">
                <form action="/callcentreDelete" method="post" class="form-inline">
                    <div class="form-group row">
                        <div class="col-sm-6">
                            <input type="text" name="departmentId" class="form-control" placeholder="Удалить по ID..."/>
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
        <#--{{#updateIdCheck}}-->
            <div class="alert alert-danger" role="alert" style="width: 589px">
                ${updateIdCheck!}
                <#--{{updateIdCheck}}-->
            </div>
        <#--{{/updateIdCheck}}-->
        </#if>
        <a class="btn btn-primary" data-toggle="collapse" href="#collapse1" role="button" aria-expanded="false"
           aria-controls="collapseExample">
            Открыть меню изменения информации о существующем в системе колл-центре
        </a>
        <div class="collapse" id="collapse1">
            <div class="form-group mt-3">
                <form action="/callcentreUpdate" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" name="departmentId"
                               placeholder="Введите ID департамента, информацию о котором нужно изменить..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="name" placeholder="Изменить название..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="managerId"
                               placeholder="Изменить ID менеджера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="deliveryRegion"
                               placeholder="Изменить область доставки..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="locationId"
                               placeholder="Изменить ID локации..."/>
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
            Открыть меню добавления нового колл-центра в систему
        </a>
        <div class="collapse" id="collapse2">
            <div class="form-group mt-3">
                <form action="/callcentre" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" name="name" placeholder="Введите название..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="deliveryRegion"
                               placeholder="Введите область доставки..."/>
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
                <th scope="col">Department ID</th>
                <th scope="col">Manager ID</th>
                <th scope="col">Location ID</th>
                <th scope="col">Название</th>
                <th scope="col">Область доставки</th>
            </tr>
            </thead>
            <tbody>
            <#list callcentres as callcentre>
            <#--{{#callcentres}}-->
                <tr>
                    <th scope="row">${callcentre.departmentId}</th>
                    <td>${callcentre.managerId}</td>
                    <td>${callcentre.locationId}</td>
                    <td>${callcentre.name}</td>
                    <td>${callcentre.deliveryRegion}</td>
                </tr>
            <#--{{/callcentres}}-->
            <#else>
            <#--{{^callcentres}}-->
                <tr>
                    <th scope="row">List of callcentres is empty yet!</th>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            <#--{{/callcentres}}-->
            </#list>
            </tbody>
        </table>
    </div>
</@defaultPage.defaultPageTemplate>

