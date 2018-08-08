$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/anagrams/st1"
    }).then(function(data) {
       $('.anagram-id').append(data.id);
       $('.anagram-content').append(data.content);
    });
});

angular.module('anagram', [])
.controller('Hello', function($scope, $http) {
    $http.get('http://localhost:8080/anagrams/st1').
        then(function(response) {
            $scope.greeting = response.data;
        });
});