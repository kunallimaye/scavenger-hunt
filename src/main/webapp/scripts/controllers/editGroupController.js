

angular.module('scavengerhunt').controller('EditGroupController', function($scope, $routeParams, $location, GroupResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.group = new GroupResource(self.original);
        };
        var errorCallback = function() {
            $location.path("/Groups");
        };
        GroupResource.get({GroupId:$routeParams.GroupId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.group);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.group.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Groups");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Groups");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.group.$remove(successCallback, errorCallback);
    };
    
    
    $scope.get();
});