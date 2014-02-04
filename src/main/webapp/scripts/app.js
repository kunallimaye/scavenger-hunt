'use strict';
angular.module('scavengerhunt',['ngMap','ngRoute','ngResource'])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/',{templateUrl:'views/landing.html',controller:'LandingPageController'})
      .when('/Tasks',{templateUrl:'views/Task/search.html',controller:'SearchTaskController'})
      .when('/Tasks/new',{templateUrl:'views/Task/detail.html',controller:'NewTaskController'})
      .when('/Tasks/edit/:TaskId',{templateUrl:'views/Task/detail.html',controller:'EditTaskController'})
      .when('/Users',{templateUrl:'views/User/search.html',controller:'SearchUserController'})
      .when('/Users/new',{templateUrl:'views/User/detail.html',controller:'NewUserController'})
      .when('/Users/edit/:UserId',{templateUrl:'views/User/detail.html',controller:'EditUserController'})
      .when('/UserGroups',{templateUrl:'views/UserGroup/search.html',controller:'SearchUserGroupController'})
      .when('/UserGroups/new',{templateUrl:'views/UserGroup/detail.html',controller:'NewUserGroupController'})
      .when('/UserGroups/edit/:UserGroupId',{templateUrl:'views/UserGroup/detail.html',controller:'EditUserGroupController'})
      .otherwise({
        redirectTo: '/'
      });
  }])
  .controller('LandingPageController', function LandingPageController() {
  })
  .controller('NavController', function NavController($scope, $location) {
    $scope.matchesRoute = function(route) {
        var path = $location.path();
        return (path === ("/" + route) || path.indexOf("/" + route + "/") == 0);
    };
  });
