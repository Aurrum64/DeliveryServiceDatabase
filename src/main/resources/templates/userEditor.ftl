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

User editor

<form action="/user" method="post">
    <input type="text" name="username" value="${user.username}">
    <#list roles as role>
        <div>
            <label>
                <input type="checkbox" name="${role}"
                        ${user.roles?seq_contains(role)?string("checked", "")}>${role}
            </label>
        </div>
    </#list>
    <input type="hidden" value="${user.id}" name="userId">
    <button type="submit">Save</button>
</form>

<div class="container" style="height: 1000px">
</div>
<footer class="page-footer font-small black">
    <div class="footer-copyright text-center py-3">© Delivery Service, 2019:</div>
</footer>
<@bootstrapFooterScripts.bootstrapFooterScripts/>
</body>
</html>