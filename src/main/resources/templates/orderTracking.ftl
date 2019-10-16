<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Отслеживание заказа" heightTop=100 heightBottom=0>
    <div class="text-white text-center py-5 px-4">
    <div>
        <h1 class="pink-text"><i class="fas fa-chart-pie"></i>Отслеживание заказа</h1>
    </div>
    <input type="hidden" id="courierId" value="${courierId}">
    <input type="hidden" id="orderDetailsId" value="${orderDetailsId}">
    <div id="map" class="z-depth-1-half map-container ml-5" style="width: 94%; height: 600px">
    </div>
    <div class="container" style="height: 100px">
    </div>
    <script src="/js/map/initMap.js" type="text/javascript"></script>
    <script src="/js/map/icons.js" type="text/javascript"></script>
    <script src="/js/map/orderTracking.js" type="text/javascript"></script>
</@defaultPage.defaultPageTemplate>