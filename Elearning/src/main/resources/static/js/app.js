
var app = angular.module('app', []);
app.controller('addUserController', function($scope, $http){
    $scope.user={};
    $http.post("SaveUsers",$scope.user)
        .success(function (data) {
            $scope.user=data;
        });
})