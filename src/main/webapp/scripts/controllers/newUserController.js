
angular.module('scavengerhunt').controller('NewUserController', function ($scope, $location, locationParser, UserResource , UserGroupResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.user = $scope.user || {};
    
    $scope.userGroupList = UserGroupResource.queryAll(function(items){
        $scope.userGroupSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.groupDisplayName
            });
        });
    });
    $scope.$watch("userGroupSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.user.userGroup = {};
            $scope.user.userGroup.id = selection.value;
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