angular.module('scavengerhunt').factory('UserGroupResource', function($resource){
    var resource = $resource('rest/user-groups/:UserGroupId',{UserGroupId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});