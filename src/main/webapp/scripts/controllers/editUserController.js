

angular.module('scavengerhunt').controller('EditUserController', function($scope, $routeParams, $location, UserResource , UserGroupResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.user = new UserResource(self.original);
            UserGroupResource.queryAll(function(items) {
                $scope.userGroupSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.groupDisplayName
                    };
                    if($scope.user.userGroup && item.id == $scope.user.userGroup.id) {
                        $scope.userGroupSelection = labelObject;
                        $scope.user.userGroup = wrappedObject;
                        self.original.userGroup = $scope.user.userGroup;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Users");
        };
        UserResource.get({UserId:$routeParams.UserId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.user);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.user.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Users");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Users");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.user.$remove(successCallback, errorCallback);
    };
    
    $scope.$watch("userGroupSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.user.userGroup = {};
            $scope.user.userGroup.id = selection.value;
        }
    });
    
    $scope.get();
});