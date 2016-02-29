'use strict';

angular.module('watererpApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


