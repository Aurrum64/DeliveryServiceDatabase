<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Редактировать" heightTop=65 heightBottom=600>
    <div class="container mt-5 ml-5">
        <div class="text py-4">
            <h3>Пользователь: ${user.username}</h3>
        </div>
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
            <button class="btn btn-outline-primary mt-5" type="submit">Сохранить</button>
        </form>
    </div>
</@defaultPage.defaultPageTemplate>