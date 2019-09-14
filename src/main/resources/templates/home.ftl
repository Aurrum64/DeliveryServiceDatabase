<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<#import "interfaceParts/bootstrapHeaderLinks.ftl" as bootstrapHeaderLinks>
<#import "interfaceParts/bootstrapNavBar.ftl" as navBar>
<#import "interfaceParts/bootstrapNavBarWithSignBtn.ftl" as navBarWithSignBtn>
<#import "interfaceParts/bootstrapFooterScripts.ftl" as bootstrapFooterScripts>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <@bootstrapHeaderLinks.bootstrapHeaderLinks/>
    <title>Home</title>
</head>
<body>
<@navBarWithSignBtn.bootstrapNavbarWithSignBtn/>
<div class="view">
    <img src="https://images.wallpaperscraft.ru/image/doroga_noch_svet_125999_1920x1080.jpg" class="img-fluid"
         alt="" style="width: 100%; height: 100%">
    <div class="mask pattern-1 flex-center waves-effect waves-light">
        <h1><p class="white-text">Welcome to our delivery service!</p></h1>
    </div>
</div>
<footer class="page-footer font-small black">
    <div class="footer-copyright text-center py-3">© Delivery Service, 2019:</div>
</footer>
<@bootstrapFooterScripts.bootstrapFooterScripts/>
</body>
</html>