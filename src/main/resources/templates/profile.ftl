<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Профиль" heightTop=65 heightBottom=200>
    <#include "parts/interface/security.ftl">
    <div class="container ml-5 mt-5" style="width: 600px">
        <div class="text py-4">
            <h3>Личный кабинет пользователя:${username!}</h3>
        </div>
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
            <div class="text mt-2 mb-3">
                <h4>Ваш аватар:</h4>
            </div>
            <img src="/img/${avatar}" alt="">
        <#else>
            <div class="text mt-2 mb-3">
                <h4>Базовый аватар:</h4>
            </div>
            <img src="https://i.ibb.co/Xk93fQh/avatar1.png" alt="">
        </#if>

        <div class="container">
            <a class="btn btn-primary mt-4 ml-4" data-toggle="collapse" href="#collapse2" role="button"
               aria-expanded="false"
               aria-controls="collapse2">
                Я хочу у вас работать!
            </a>
            <div class="collapse ml-4" id="collapse2">
                <div class="form-group mt-4">
                    <form id="chooseProfession">
                        <select id="professionChoice" class="browser-default custom-select">
                            <option selected>Кем вы хотите работать в нашей компании?</option>
                            <option value="courierRequest">Курьером</option>
                        </select>
                        <div class="text-center mt-4">
                            <#if !user.wasFired>
                            <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                                <button type="submit" class="btn btn-primary">Отправить заявку</button>
                            <#else>
                                <button type="submit" class="btn btn-primary" disabled>Отправить заявку</button>
                            </#if>
                        </div>
                    </form>
                </div>
            </div>
        </div>
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
                </tr>
                </tbody>
            </table>
            <table class="table table-striped">
                <thead>
                <tr>
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
                    <th scope="col">${courier.salary}</th>
                    <th scope="col">${courier.hireDate}</th>
                    <th scope="col">${courier.premium}</th>
                    <th scope="col">${courier.departmentId}</th>
                    <th scope="col">${courier.authorName}</th>
                    <th scope="col">
                        <div id="readinessStatus"></div>
                    </th>
                </tr>
                </tbody>
            </table>
            <#if courier.readiness>
                <div class="text-center py-4 mt-4">
                    <form id="changeReadiness">
                        <input type="hidden" id="courierId" value="${courier.courierId}">
                        <div class="btn-group btn-group-toggle" id="readiness" data-toggle="buttons">
                            <label class="btn btn-blue-grey form-check-label">
                                <input class="form-check-input" type="radio" name="options" id="option1" value="false"
                                       autocomplete="off">
                                Не готов
                            </label>
                            <label class="btn btn-light-green form-check-label active">
                                <input class="form-check-input" type="radio" name="options" id="option2" value="true"
                                       autocomplete="off" checked>
                                Жду заказов
                            </label>
                        </div>
                    </form>
                </div>
            <#else>
                <div class="text-center py-4 mt-4">
                    <form id="changeReadiness">
                        <input type="hidden" id="courierId" value="${courier.courierId}">
                        <div class="btn-group btn-group-toggle" id="readiness" data-toggle="buttons">
                            <label class="btn btn-blue-grey form-check-label active">
                                <input class="form-check-input" type="radio" name="options" id="option1" value="false"
                                       autocomplete="off" checked>
                                Не готов
                            </label>
                            <label class="btn btn-light-green form-check-label">
                                <input class="form-check-input" type="radio" name="options" id="option2" value="true"
                                       autocomplete="off">
                                Жду заказов
                            </label>
                        </div>
                    </form>
                </div>
            </#if>
        </#if>

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
    </div>
    <script src="/js/notifications/usersRequests.js" type="text/javascript"></script>
    <script src="/js/profile/changeReadiness.js" type="text/javascript"></script>
</@defaultPage.defaultPageTemplate>