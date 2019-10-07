<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Пользователи" heightTop=65 heightBottom=600>
    <div class="container mt-5 ml-5" style="width: 700px">
        <div class="text py-4">
            <h3>Пользователи:</h3>
        </div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Имя</th>
                <th scope="col">Почтовый адрес</th>
                <th scope="col">Статус почты</th>
                <th scope="col">Роль</th>
                <th scope="col">Редактировать</th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <th scope="row">${user.username}</th>
                    <th scope="row">${user.email}</th>
                    <th scope="row">${user.emailVerification}</th>
                    <td><#list user.roles as role>${role}<#sep>, </#list></td>
                    <td><a href="/user/${user.id}">Edit</a></td>
                </tr>
            <#else>
                <tr>
                    <th scope="row">User's list is empty!</th>
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