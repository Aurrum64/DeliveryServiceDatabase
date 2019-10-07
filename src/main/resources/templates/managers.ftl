<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Менеджеры" heightTop=65 heightBottom=400>
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
                <form action="/managersFilter" method="post" class="form-inline">
                    <div class="form-group row">
                        <div class="md-form form-lg ml-4">
                            <input type="text" name="managerId" id="input" class="form-control form-control-lg">
                            <label for="input">Найти по ID...</label>
                        </div>
                        <div class="md-form form-lg ml-3">
                            <input type="text" name="firstName" id="inpu" class="form-control form-control-lg">
                            <label for="inpu">Найти по имени...</label>
                        </div>
                        <div class="md-form form-lg ml-3">
                            <input type="text" name="lastName" id="inp" class="form-control form-control-lg">
                            <label for="inp">Найти по фамилии...</label>
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
    <div class="container ml-5">
        <#if deleteIdCheck??>
            <div class="alert alert-danger alert-dismissible fade show" role="alert" style="width: 305px">
                ${deleteIdCheck!}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </#if>
        <div class="form-row">
            <div class="form-group col-md-6">
                <form action="/managersDelete" method="post" class="form-inline">
                    <div class="form-group row">
                        <div class="md-form form-lg ml-4">
                            <input type="text" name="managerId" id="in" class="form-control form-control-lg">
                            <label for="in">Удалить по ID...</label>
                        </div>
                        <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                        <button type="submit" class="btn btn-primary ml-3">Удалить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="container mt-3 ml-5">
        <#if updateIdCheck??>
            <div class="alert alert-danger alert-dismissible fade show" role="alert" style="width: 589px">
                ${updateIdCheck!}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </#if>
        <a class="btn btn-primary" data-toggle="collapse" href="#collapse1" role="button" aria-expanded="false"
           aria-controls="collapseExample">
            Открыть меню изменения информации о существующем в системе менеджере
        </a>
        <div class="collapse" id="collapse1">
            <div class="form-group mt-3">
                <form action="/managersUpdate" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" name="managerId"
                               placeholder="Введите ID менеджера, информацию о котором нужно изменить..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="firstName"
                               placeholder="Изменить имя менеджера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="lastName"
                               placeholder="Изменить фамилию менеджера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="email"
                               placeholder="Изменить e-mail менеджера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="phoneNumber"
                               placeholder="Изменить телефон менеджера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="salary"
                               placeholder="Изменить зарплату менеджера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="hireDate"
                               placeholder="Изменить дату трудоустройства менеджера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="premium"
                               placeholder="Изменить размер премии менеджера..."/>
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
            Открыть меню добавления нового менеджера в систему
        </a>
        <div class="collapse" id="collapse2">
            <div class="form-group mt-3">
                <form action="/managers" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" name="firstName"
                               placeholder="Введите имя менеджера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="lastName"
                               placeholder="Введите фамилию менеджера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="email" placeholder="Введите e-mail менеджера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="phoneNumber"
                               placeholder="Введите телефон менеджера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="salary"
                               placeholder="Введите зарплату менеджера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="hireDate"
                               placeholder="Введите дату трудоустройства менеджера..."/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="premium"
                               placeholder="Введите размер премии менеджера..."/>
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
            <h3>Менеджеры:</h3>
        </div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Имя</th>
                <th scope="col">Фамилия</th>
                <th scope="col">Почта</th>
                <th scope="col">Сотовый</th>
                <th scope="col">Зарплата</th>
                <th scope="col">Трудоустроился</th>
                <th scope="col">Премиальные</th>
                <th scope="col">Автор</th>
            </tr>
            </thead>
            <tbody>
            <#list managers as manager>
                <tr>
                    <th scope="row">${manager.managerId}</th>
                    <td>${manager.firstName}</td>
                    <td>${manager.lastName}</td>
                    <td>${manager.email}</td>
                    <td>${manager.phoneNumber}</td>
                    <td>${manager.salary}</td>
                    <td>${manager.hireDate}</td>
                    <td>${manager.premium}</td>
                    <td>${manager.authorName}</td>
                </tr>
            <#else>
                <tr>
                    <th scope="row">List of managers is empty yet!</th>
                    <td></td>
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