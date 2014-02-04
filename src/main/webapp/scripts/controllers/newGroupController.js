
angular.module('scavengerhunt').controller('NewGroupController', function ($scope, $location, locationParser, GroupResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.group = $scope.group || {};
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Groups/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        GroupResource.save($scope.group, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Groups");
    };
});