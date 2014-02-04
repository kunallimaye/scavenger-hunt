

angular.module('scavengerhunt').controller('EditUserGroupController', function($scope, $routeParams, $location, UserGroupResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.userGroup = new UserGroupResource(self.original);
        };
        var errorCallback = function() {
            $location.path("/UserGroups");
        };
        UserGroupResource.get({UserGroupId:$routeParams.UserGroupId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.userGroup);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.userGroup.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/UserGroups");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/UserGroups");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.userGroup.$remove(successCallback, errorCallback);
    };
    
    
    $scope.get();
});