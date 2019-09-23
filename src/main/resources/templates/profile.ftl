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
</@defaultPage.defaultPageTemplate>