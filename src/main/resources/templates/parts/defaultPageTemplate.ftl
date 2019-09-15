<#macro defaultPageTemplate pageName heightTop heightBottom>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <#include "interface/bootstrapHeaderLinks.ftl">
        <#include "map/mapHeaderLinksAndScripts.ftl">
        <#include "interface/security.ftl">
        <title>${pageName}</title>
    </head>
    <body>
    <#include "interface/bootstrapNavBarWithSignBtn.ftl">
    <div class="container" style="height: ${heightTop}px"></div>
    <#nested>
    <div class="container" style="height: ${heightBottom}px"></div>
    <footer class="page-footer font-small black">
        <div class="footer-copyright text-center py-3">© Delivery Service, 2019</div>
    </footer>
    <#include "interface/bootstrapFooterScripts.ftl">
    </body>
    </html>
</#macro>