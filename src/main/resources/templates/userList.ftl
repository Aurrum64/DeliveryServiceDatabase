<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<#import "interfaceParts/bootstrapHeaderLinks.ftl" as bootstrapHeaderLinks>
<#import "interfaceParts/bootstrapNavBar.ftl" as navBar>
<#import "interfaceParts/bootstrapFooterScripts.ftl" as bootstrapFooterScripts>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <@bootstrapHeaderLinks.bootstrapHeaderLinks/>
    <title>Home</title>
</head>
<body>
<@navBar.bootstrapNavBar/>
<div class="container" style="height: 100px">
</div>
Users list
<div class="container mt-5 ml-5">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Имя</th>
            <th scope="col">Роль</th>
            <th scope="col"></th>
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
<div class="container" style="height: 1000px">
</div>
<footer class="page-footer font-small black">
    <div class="footer-copyright text-center py-3">© Delivery Service, 2019:</div>
</footer>
<@bootstrapFooterScripts.bootstrapFooterScripts/>
</body>
</html>