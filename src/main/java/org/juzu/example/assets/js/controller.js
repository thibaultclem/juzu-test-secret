/**
 * Created by TClement on 8/23/14.
 */
var app = angular.module("SecretWall", []);

app.controller("SecretWallController", function($scope, $http) {
    $http.get('data/posts.json').
        success(function(data, status, headers, config) {
            $scope.secrets = data;
        }).
        error(function(data, status, headers, config) {
            // log error
        });
});