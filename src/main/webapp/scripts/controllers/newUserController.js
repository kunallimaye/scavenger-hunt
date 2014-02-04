
angular.module('scavengerhunt').controller('NewUserController', function ($scope, $location, locationParser, UserResource , GroupResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.user = $scope.user || {};
    
    $scope.groupList = GroupResource.queryAll(function(items){
        $scope.groupSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.groupName
            });
        });
    });
    $scope.$watch("groupSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.user.group = {};
            $scope.user.group.id = selection.value;
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Users/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        UserResource.save($scope.user, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Users");
    };
});