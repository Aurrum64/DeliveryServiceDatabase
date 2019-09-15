<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="User editor" heightTop=65 heightBottom=200>
    <div class="jumbotron card card-image"
         style="background-image: url(https://mdbootstrap.com/img/Photos/Others/gradient1.jpg);">
        <div class="text-black text-center py-5 px-4">
            <div>
                <h1 class="card-title h1-responsive pt-3 mb-5 font-bold"><strong>User's editor page</strong></h1>
            </div>
        </div>
    </div>
    <div class="container mt-5 ml-5">
        <form action="/user" method="post">
            <div class="md-form form-lg">
                <input type="text" name="username" id="inputLGEx"
                       class="form-control form-control-lg" value="${user.username}">
                <label for="inputLGEx">Редактор имени пользователя:</label>
            </div>
            <div class="container mt-5 ml-3"></div>
            <#list roles as role>
            <div>
                <label>
                    <input type="checkbox" name="${role}"
                            ${user.roles?seq_contains(role)?string("checked", "")}>${role}
                </label>
            </div>
            </#list>
            <input type="hidden" value="${user.id}" name="userId">
            <button class="btn btn-outline-primary mt-5" type="submit">Save</button>
        </form>
    </div>
</@defaultPage.defaultPageTemplate>