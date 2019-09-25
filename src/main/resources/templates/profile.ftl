<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<#import "parts/interface/jumbotron.ftl" as jumbotron>
<@defaultPage.defaultPageTemplate pageName="Profile" heightTop=65 heightBottom=200>
    <@jumbotron.jumbotron image="https://mdbootstrap.com/img/Photos/Others/gradient1.jpg"
    pageName="Profile">
    </@jumbotron.jumbotron>
    <#include "parts/interface/security.ftl">
    <div class="container ml-5 mt-5" style="width: 600px">
        <h2>Личный кабинет пользователя:${username!}</h2>

        <#if !isAccountActivated>
            <div class="alert alert-danger alert-dismissible fade show mt-3" role="alert"
                 style="width: 460px">
                <b>Ваша учётная запись не активна,</b><br> так как вы не подтвердили, указанный вами при
                регистрации, адрес электронной почты.<br>
                <b>Многие функции сервиса для вас недоступны.</b>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </#if>

        <#if avatar??>
            <div class="text mt-5 mb-3">
                <h4>Ваш аватар:</h4>
            </div>
            <img src="/img/${avatar}" alt="">
        <#else>
            <div class="text mt-5 mb-3">
                <h4>Базовый аватар:</h4>
            </div>
            <img src="https://i.ibb.co/ZXGmczB/w450h4001385925286-User.png" alt="">
        </#if>

        <div class="text mt-5">
            <h4>Информация о пользователе:</h4>
        </div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Идентификатор пользователя</th>
                <th scope="col">Имя</th>
                <th scope="col">Статус почты</th>
                <th scope="col">Роль</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">${user.id}</th>
                <th scope="row">${user.username}</th>
                <th scope="row">${user.emailVerification}</th>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
            </tr>
            </tbody>
        </table>

        <#if isCourier>
            <div class="text mt-5">
                <h4>Вы находитесь на должности курьера:</h4>
            </div>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Имя</th>
                    <th scope="col">Фамилия</th>
                    <th scope="col">Почта</th>
                    <th scope="col">Сотовый</th>
                    <th scope="col">Рейтинг</th>
                    <th scope="col">Зарплата</th>
                    <th scope="col">Трудоустроился</th>
                    <th scope="col">Премия</th>
                    <th scope="col">Департамент</th>
                    <th scope="col">Автор</th>
                    <th scope="col">Готовность</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="col">${courier.courierId}</th>
                    <th scope="col">${courier.firstName}</th>
                    <th scope="col">${courier.lastName}</th>
                    <th scope="col">${courier.email}</th>
                    <th scope="col">${courier.phoneNumber}</th>
                    <th scope="col">${courier.rating}</th>
                    <th scope="col">${courier.salary}</th>
                    <th scope="col">${courier.hireDate}</th>
                    <th scope="col">${courier.premium}</th>
                    <th scope="col">${courier.departmentId}</th>
                    <th scope="col">${courier.authorName}</th>
                    <#if courier.readiness>
                        <th scope="col">Готов</th>
                    <#else>
                        <th scope="col">Не готов</th>
                    </#if>
                </tr>
                </tbody>
            </table>
        </#if>

        <div class="btn-group btn-group-toggle" data-toggle="buttons">
            <label class="btn btn-primary form-check-label active">
                <input class="form-check-input" type="radio" name="options" id="option1" autocomplete="off" checked>
                Не готов
            </label>
            <label class="btn btn-primary form-check-label">
                <input class="form-check-input" type="radio" name="options" id="option2" autocomplete="off"> Готов
            </label>
        </div>

        <div class="text mt-5">
            <h4>Редактирование:</h4>
        </div>
        <form method="post" enctype="multipart/form-data">

            <div class="input-group mt-5">
                <div class="custom-file">
                    <input type="file" name="file" class="custom-file-input" id="inputGroupFile01"
                           aria-describedby="inputGroupFileAddon01">
                    <label class="custom-file-label" for="inputGroupFile01">Установить свой аватар...</label>
                </div>
            </div>

            <div class="md-form mt-5">
                <i class="fa fa-envelope prefix grey-text"></i>
                <input type="email" name="email" value="${email!''}" id="materialFormCardEmailEx"
                       class="form-control">
                <label for="materialFormCardEmailEx" class="font-weight-light">Изменить почтовый
                    адрес...</label>
            </div>

            <div class="md-form mt-4">
                <i class="fa fa-lock prefix grey-text"></i>
                <input type="password" name="password" id="materialFormCardPasswordEx" class="form-control">
                <label for="materialFormCardPasswordEx" class="font-weight-light">Изменить пароль...</label>
            </div>

            <#--<input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
            <div class="text-center py-4 mt-4">
                <button class="btn btn-outline-info" type="submit">Сохранить</button>
            </div>
        </form>
        <div class="text mt-5">
            <h4>Пользователям:</h4>
        </div>
        <div class="text mt-5">
            <h4>Будущим сотрудникам:</h4>
        </div>
        <a class="btn btn-primary" data-toggle="collapse" href="#collapse2" role="button" aria-expanded="false"
           aria-controls="collapse2">
            Я хочу у вас работать!
        </a>
        <div class="collapse" id="collapse2">
            <div class="form-group mt-3">
                <form id="chooseProfession">
                    <select id="professionChoice" class="browser-default custom-select">
                        <option selected>Кем вы хотите работать в нашей компании?</option>
                        <option value="courierRequest">Курьером</option>
                        <option value="managerRequest">Менеджером</option>
                    </select>
                    <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Отправить заявку</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="/js/notifications/usersRequests.js" type="text/javascript"></script>
</@defaultPage.defaultPageTemplate>