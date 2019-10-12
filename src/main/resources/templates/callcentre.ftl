<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Департаменты" heightTop=65 heightBottom=400>
    <div class="container mt-5 ml-5">
        <#if filterCheck??>
            <div class="alert alert-danger" role="alert" style="width: 736px">
                ${filterCheck!}
            </div>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
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
            <div class="alert alert-danger" role="alert" style="width: 305px">
                ${deleteIdCheck!}
            </div>
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
            <div class="alert alert-danger" role="alert" style="width: 589px">
                ${updateIdCheck!}
            </div>
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
        <div class="text mt-5 mb-3">
            <h3>Департаменты:</h3>
        </div>
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
                <tr>
                    <th scope="row">${callcentre.departmentId}</th>
                    <td>${callcentre.managerId}</td>
                    <td>${callcentre.locationId}</td>
                    <td>${callcentre.name}</td>
                    <td>${callcentre.deliveryRegion}</td>
                </tr>
            <#else>
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
            </#list>
            </tbody>
        </table>
    </div>
</@defaultPage.defaultPageTemplate>

