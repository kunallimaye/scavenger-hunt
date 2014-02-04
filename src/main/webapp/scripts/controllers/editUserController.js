

angular.module('scavengerhunt').controller('EditUserController', function($scope, $routeParams, $location, UserResource , GroupResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.user = new UserResource(self.original);
            GroupResource.queryAll(function(items) {
                $scope.groupSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.groupName
                    };
                    if($scope.user.group && item.id == $scope.user.group.id) {
                        $scope.groupSelection = labelObject;
                        $scope.user.group = wrappedObject;
                        self.original.group = $scope.user.group;
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
    
    $scope.$watch("groupSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.user.group = {};
            $scope.user.group.id = selection.value;
        }
    });
    
    $scope.get();
});