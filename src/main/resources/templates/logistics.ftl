<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Логистика" heightTop=70 heightBottom=400>
    <div class="container" style="height: 50px"></div>
    <button id="buildRoute" type="button" class="btn btn-secondary ml-5 mt-3">
        Найти ближайший заказ и проложить маршрут
    </button>
    <button id="move" type="button" class="btn btn-secondary ml-5 mt-3">
        Берусь доставить предложенный системой заказ
    </button>
    <div id="map" class="z-depth-1-half map-container mt-5 ml-5" style="width: 95%; height: 600px">
    </div>
    <#include "parts/scriptsSources/mapScripts.ftl">
    <#include "parts/scriptsSources/orderDetailsScripts.ftl">
    <#include "parts/scriptsSources/couriersScripts.ftl">
</@defaultPage.defaultPageTemplate>