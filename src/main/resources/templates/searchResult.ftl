<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<#import "parts/interface/jumbotron.ftl" as jumbotron>
<@defaultPage.defaultPageTemplate pageName="Search" heightTop=65 heightBottom=200>
    <@jumbotron.jumbotron image="https://mdbootstrap.com/img/Photos/Others/gradient1.jpg"
    pageName="Search page">
    </@jumbotron.jumbotron>
    <#if managersTable??>
        <div class="container mt-5 ml-5">
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
                        <th scope="row">No manager was found!</th>
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
    </#if>

</@defaultPage.defaultPageTemplate>