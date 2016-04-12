'use strict';

angular.module('watererpApp')
    .factory('CustDetailsService', function ($resource, DateUtils) {
        return $resource('api/custDetailss/search/:can', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.connDate = DateUtils.convertLocaleDateFromServer(data.connDate);
                    data.prevBillMonth = DateUtils.convertLocaleDateFromServer(data.prevBillMonth);
                    data.metReadingDt = DateUtils.convertLocaleDateFromServer(data.metReadingDt);
                    data.metReadingMo = DateUtils.convertLocaleDateFromServer(data.metReadingMo);
                    data.lastPymtDt = DateUtils.convertLocaleDateFromServer(data.lastPymtDt);
                    data.meterFixDate = DateUtils.convertLocaleDateFromServer(data.meterFixDate);
                    return data;
                }
            }
        });
    });
