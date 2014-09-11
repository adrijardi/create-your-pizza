var pizzaApp = angular.module('pizzaApp', []);

pizzaApp.controller('ToppingsCtrl', function($scope, $http, $log) {

    $scope.pizzaBase = '';
    $scope.pizzaToppings = {};
    $scope.price = 0;


    $scope.base = function() {
        $log.debug("Changed base "+$scope.pizzaBase);
        $http.put('/order/base?pizzaBase='+$scope.pizzaBase).then(function () {
            recalculatePrice();
        });
    };

    $scope.topping = function(id) {
        $log.debug("Changed topping "+ event);
        $log.debug($scope.pizzaToppings);
        var url = '/order/topping?topping='+id;

        if($scope.pizzaToppings[id]) {
            $http.put(url).then(function () {
                recalculatePrice();
            });
        } else {
            $http.delete(url).then(function () {
                recalculatePrice();
            });
        }
    };

    function recalculatePrice() {
        $http.get('/order/price').then(function (data) {
            $scope.price = data.data;
        });
    }

});