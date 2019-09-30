let allPossibleRoutes = [];
let couriers = [];
let allPossibleRoutesWithCouriersInfo = [];
let allPossibleGraphHopperSolutions = [];
let allSimplifiedSolutions = [];

function optimizeRoutes() {

    buildAllPossibleRoutes();
    takeAllPossibleDistances();
    /*findShortestRoutes();*/
}

function buildAllPossibleRoutes() {

    for (let i = 0; i < firstOrderPointMarkers.length; i++) {
        for (let j = 0; j < couriersMarkers.length; j++) {

            couriers.push(couriersInfos[j].courierId);
            allPossibleRoutes.push(L.Routing.control(
                {
                    waypoints: [
                        L.latLng(couriersMarkers[j]._latlng),
                        L.latLng(firstOrderPointMarkers[i]._latlng),
                        L.latLng(secondOrderPointMarkers[i]._latlng)
                    ],
                    lineOptions: {
                        styles: [{color: 'green', opacity: 5, weight: 0}]
                    },
                    createMarker: function () {
                        return null;
                    },
                    show: false,
                    router: L.Routing.graphHopper('585def41-7ae7-4420-a2b3-b71c919bc166'),
                    routeWhileDragging: true
                }));
        }
    }
}

function takeAllPossibleDistances() {

    for (let i = 0; i < allPossibleRoutes.length; i++) {

        allPossibleRoutes[i].addTo(myDeliveryServiceMap);
        allPossibleRoutes[i].on('routesfound', function (e) {

            allPossibleGraphHopperSolutions[i] = L.Routing.line(e.routes[0]);
            allSimplifiedSolutions[i] = allPossibleGraphHopperSolutions[i]._route;
            allSimplifiedSolutions[i].push(couriers[i]);
            console.log(allSimplifiedSolutions[i]);
        });
    }
}

function findShortestRoutes() {

    console.log(allSimplifiedSolutions);
    for (let i = 0; i < allSimplifiedSolutions.length; i++) {
        console.log(allSimplifiedSolutions[i]);
    }
}

/*    for (let i = 0; i < allPossibleGraphHopperSolutions.length; i + couriersMarkers.length) {
        let allRoutesToOneOrder = allPossibleGraphHopperSolutions.slice(i, couriersMarkers.length);
        console.log(allRoutesToOneOrder);
    }*/
/*allPossibleDistances[i] = allPossibleGraphHopperSolutions[i]._route.summary.totalDistance;
console.log(allPossibleDistances[i]);*/
/*polylines[i] = L.polyline(e.routes[0].coordinates, {color: 'red', weight: 3}).addTo(routesLayerGroup);*/

for (let i = 0; i < secondOrderPointMarkers.length; i++) {
    routes[i] = L.Routing.control(
        {
            waypoints: [
                L.latLng(couriersMarkers[0]._latlng),
                L.latLng(firstOrderPointMarkers[i]._latlng),
                L.latLng(secondOrderPointMarkers[i]._latlng)
            ],
            lineOptions: {
                styles: [{color: 'green', opacity: 5, weight: 0}]
            },
            createMarker: function () {
                return null;
            },
            show: false,
            router: L.Routing.graphHopper('585def41-7ae7-4420-a2b3-b71c919bc166'),
            routeWhileDragging: true
        });
    routes[i].addTo(myDeliveryServiceMap);
    routes[i].on('routesfound', function (e) {

        console.log(couriersInfos[0].courierId);
        console.log(deliveryInfos[i].orderDetailsId);

        orderDeliveredByCourier = JSON.stringify(
            {
                courierId: couriersInfos[0].courierId,
                orderDetailsId: deliveryInfos[i].orderDetailsId
            });
        assignCourierToOrder(orderDeliveredByCourier);
        setTimeout(function () {
            showActiveOrdersListForLogisticPage();
        }, (300));
        solutionsInfos[i] = L.Routing.line(e.routes[0]);
        polylines[i] = L.polyline(e.routes[0].coordinates, {color: 'red', weight: 3}).addTo(routesLayerGroup);
    });
}