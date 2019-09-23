<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<#import "parts/interface/jumbotron.ftl" as jumbotron>
<@defaultPage.defaultPageTemplate pageName="Profile" heightTop=65 heightBottom=200>
    <@jumbotron.jumbotron image="https://mdbootstrap.com/img/Photos/Others/gradient1.jpg"
    pageName="Profile">
    </@jumbotron.jumbotron>
    <div class="container ml-5 mt-5" style="width: 600px">
        <h3>Личный кабинет пользователя:${username!}</h3>

        <#if avatar??>
            <div class="text mt-5">
                <h5>Ваш аватар:</h5>
            </div>
            <img src="/img/${avatar}" alt="">
        <#else>
            <div class="text mt-5">
                <h5>Базовый аватар:</h5>
            </div>
            <img src="https://i.ibb.co/ZXGmczB/w450h4001385925286-User.png" alt="">
        </#if>

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