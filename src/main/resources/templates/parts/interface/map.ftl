<div class="container" style="height: 50px">
</div>
<div class="container mt-2 ml-5">
    <div class="text mb-3">
        <h3>Логистическая карта:</h3>
    </div>
</div>
<div class="form-group row mt-4 ml-3">
    <div class="btn-group" role="group">
        <button id="btnGroupDrop1" type="button" class="btn btn-secondary dropdown-toggle ml-5 mt-3"
                data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
            Курьеры
        </button>
        <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
            <a class="dropdown-item" id="couriersCoordinates">Показать курьеров на карте</a>
            <a class="dropdown-item" id="hideCouriersMarkers">Скрыть курьеров</a>
        </div>
    </div>
    <div class="btn-group" role="group">
        <button id="btnGroupDrop2" type="button" class="btn btn-secondary dropdown-toggle ml-5 mt-3"
                data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
            Активные заказы
        </button>
        <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
            <a class="dropdown-item" id="deliveryCoordinates">Показать активные заказы на карте</a>
            <a class="dropdown-item" id="hideNotDeliveredOrderPoints">Скрыть активные заказы</a>
        </div>
    </div>
    <div class="btn-group" role="group">
        <button id="btnGroupDrop3" type="button" class="btn btn-secondary dropdown-toggle ml-5 mt-3"
                data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
            Архивные заказы
        </button>
        <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
            <a class="dropdown-item" id="showDeliveredOrders">Показать архивные заказы на карте</a>
            <a class="dropdown-item" id="hideDeliveredOrderPoints">Скрыть архивные заказы</a>
        </div>
    </div>
    <div class="btn-group" role="group">
        <button id="btnGroupDrop4" type="button" class="btn btn-secondary dropdown-toggle ml-5 mt-3"
                data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
            Зоны доставки
        </button>
        <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
            <a class="dropdown-item" id="showDeliveryZones">Показать зоны доставки на карте</a>
            <a class="dropdown-item" id="hideDeliveryZones">Скрыть зоны доставки</a>
        </div>
    </div>
    <button id="buildRoute" type="button" class="btn btn-secondary ml-5 mt-3">
        Построить маршрут
    </button>
    <button id="move" type="button" class="btn btn-secondary ml-5 mt-3">
        В путь!
    </button>
</div>
<div class="container mt-5 ml-5" style="width: 1450px; height: 600px">
    <div id="map" style="width: 1450px; height: 600px"></div>
</div>