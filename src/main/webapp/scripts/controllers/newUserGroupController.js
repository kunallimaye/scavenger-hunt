
angular.module('scavengerhunt').controller('NewUserGroupController', function ($scope, $location, locationParser, UserGroupResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.userGroup = $scope.userGroup || {};
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/UserGroups/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        UserGroupResource.save($scope.userGroup, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/UserGroups");
    };
});