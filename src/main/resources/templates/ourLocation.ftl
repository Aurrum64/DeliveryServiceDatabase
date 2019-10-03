<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Home" heightTop=100 heightBottom=0>
    <div class="text-white text-center pt-5 px-4">
        <div>
            <h1 class="pink-text"><i class="fas fa-chart-pie"></i>Наше расположение</h1>
        </div>
    </div>
    <#include "parts/interface/map.ftl">
    <script src="/js/map/initMap.js" type="text/javascript"></script>
    <script src="/js/map/icons.js" type="text/javascript"></script>
    <script src="/js/map/ourLocation.js" type="text/javascript"></script>
</@defaultPage.defaultPageTemplate>