angular.module('scavengerhunt').factory('GroupResource', function($resource){
    var resource = $resource('rest/groups/:GroupId',{GroupId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});