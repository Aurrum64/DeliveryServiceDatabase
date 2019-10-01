let allPossibleRoutes = [];
let couriers = [];
let allPossibleGraphHopperSolutions = [];
let allSimplifiedSolutions = [];

function optimizeRoutes() {

    buildAllPossibleRoutes();
    takeAllPossibleSolutions();
    findShortestSolutions();
}

function buildAllPossibleRoutes() {

    for (let i = 0; i < firstOrderPointMarkers.length; i++) {
        for (let j = 0; j < couriersMarkers.length; j++) {

            couriers.push(couriersInfos[j].courierId);
            let route = L.Routing.control(
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
                });
            allPossibleRoutes.push(route);
        }
    }
    console.log(allPossibleRoutes);
}

function takeAllPossibleSolutions() {

    for (let i = 0; i < allPossibleRoutes.length; i++) {

        allPossibleRoutes[i].addTo(myDeliveryServiceMap);
        allPossibleRoutes[i].on('routesfound', function (e) {

            let solution = L.Routing.line(e.routes[0]);
            allPossibleGraphHopperSolutions.push(solution);
            allSimplifiedSolutions[i] = allPossibleGraphHopperSolutions[i]._route;
            allSimplifiedSolutions[i]["courier"] = {courierId: couriers[i]};
        });
    }
}

function findShortestSolutions() {

    console.log(couriersMarkers);
    console.log(allPossibleGraphHopperSolutions);
    console.log(allSimplifiedSolutions);
    console.log(couriersMarkers.length);
    console.log(allSimplifiedSolutions.length);
    let couriersRoutesToOneOrder;
    for (let i = 0; i < couriersMarkers.length; i++) {
        for (let j = 0; j < allSimplifiedSolutions.length; j++) {

            console.log(allSimplifiedSolutions[j].courier.courierId);

            /*if (allSimplifiedSolutions[j].courier.courierId === i) {
                couriersRoutesToOneOrder.push(allSimplifiedSolutions[j]);
            }*/
        }
    }
}