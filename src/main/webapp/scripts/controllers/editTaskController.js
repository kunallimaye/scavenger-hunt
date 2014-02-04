

angular.module('scavengerhunt').controller('EditTaskController', function($scope, $routeParams, $location, TaskResource , UserResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.task = new TaskResource(self.original);
            UserResource.queryAll(function(items) {
                $scope.ownerSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.userName
                    };
                    if($scope.task.owner && item.id == $scope.task.owner.id) {
                        $scope.ownerSelection = labelObject;
                        $scope.task.owner = wrappedObject;
                        self.original.owner = $scope.task.owner;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Tasks");
        };
        TaskResource.get({TaskId:$routeParams.TaskId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.task);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.task.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Tasks");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Tasks");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.task.$remove(successCallback, errorCallback);
    };
    
    $scope.$watch("ownerSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.task.owner = {};
            $scope.task.owner.id = selection.value;
        }
    });
    
    $scope.get();
});