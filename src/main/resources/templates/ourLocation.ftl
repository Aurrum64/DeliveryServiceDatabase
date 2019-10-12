<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Наше расположение" heightTop=80 heightBottom=0>
    <div class="text-white text-center pt-5 px-4">
        <div>
            <h1 class="pink-text"><i class="fas fa-chart-pie"></i>Наше расположение</h1>
        </div>
    </div>
    <div id="map" class="z-depth-1-half map-container mt-5 ml-5" style="width: 95%; height: 600px">
    </div>
    <div class="text-grey text-center py-5 px-4">
        <button id="buildRouteFromUnderground" type="button" class="btn btn-pink ml-5 mt-3">
            Как пройти от ближайшей станции метрополитена?
        </button>
    </div>
    <div class="container" style="height: 100px">
    </div>
    <script src="/js/map/initMap.js" type="text/javascript"></script>
    <script src="/js/map/icons.js" type="text/javascript"></script>
    <script src="/js/map/ourLocation.js" type="text/javascript"></script>
</@defaultPage.defaultPageTemplate>