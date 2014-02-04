
angular.module('scavengerhunt').controller('NewTaskController', function ($scope, $location, locationParser, TaskResource , UserResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.task = $scope.task || {};
    
    $scope.ownerList = UserResource.queryAll(function(items){
        $scope.ownerSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.displayName
            });
        });
    });
    $scope.$watch("ownerSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.task.owner = {};
            $scope.task.owner.id = selection.value;
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Tasks/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        TaskResource.save($scope.task, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Tasks");
    };
});