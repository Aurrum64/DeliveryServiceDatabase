<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Users list" heightTop=65 heightBottom=200>
    <div class="jumbotron card card-image"
         style="background-image: url(https://mdbootstrap.com/img/Photos/Others/gradient1.jpg);">
        <div class="text-black text-center py-5 px-4">
            <div>
                <h1 class="card-title h1-responsive pt-3 mb-5 font-bold"><strong>User's list page</strong></h1>
            </div>
        </div>
    </div>
    <div class="container mt-5 ml-5" style="width: 500px">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Имя</th>
                <th scope="col">Роль</th>
                <th scope="col">Редактировать</th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <th scope="row">${user.username}</th>
                    <td><#list user.roles as role>${role}<#sep>, </#list></td>
                    <td><a href="/user/${user.id}">Edit</a></td>
                </tr>
            <#else>
                <tr>
                    <th scope="row">User's list is empty!</th>
                    <td></td>
                    <td></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@defaultPage.defaultPageTemplate>