'use strict';

angular.module('watererpApp')
    .factory('Principal', function Principal($q, Account, $http) {
        var _identity,
            _authenticated = false;
        var _module2MenuItems;
        var _orgRole;

        return {
            isIdentityResolved: function () {
                return angular.isDefined(_identity);
            },
            isAuthenticated: function () {
                return _authenticated;
            },
            hasAuthority: function (authority) {
                if (!_authenticated) {
                    return $q.when(false);
                }

                return this.identity().then(function(_id) {
                    return _id.authorities && _id.authorities.indexOf(authority) !== -1;
                }, function(err){
                    return false;
                });
            },
            hasAnyAuthority: function (authorities) {
                if (!_authenticated || !_identity || !_identity.authorities) {
                    return false;
                }

                for (var i = 0; i < authorities.length; i++) {
                    if (_identity.authorities.indexOf(authorities[i]) !== -1) {
                        return true;
                    }
                }

                return false;
            },
            authenticate: function (identity) {
                _identity = identity;
                _authenticated = identity !== null;
            },
            identity: function (force) {
                var deferred = $q.defer();

                if (force === true) {
                    _identity = undefined;
                }

                // check and see if we have retrieved the identity data from the
				// server.
                // if we have, reuse it by immediately resolving
                if (angular.isDefined(_identity)) {
                    deferred.resolve(_identity);

                    return deferred.promise;
                }
                
                // retrieve the identity data from the server, update the
				// identity object, and then resolve.
                Account.get().$promise
                    .then(function (account) {
                        _identity = account.data;
                        _authenticated = true;

                        deferred.resolve(_identity);
                    })
                    .catch(function() {
                        _identity = null;
                        _authenticated = false;
                        deferred.resolve(_identity);
                    });
                
                return deferred.promise;
            },
            getLogonUser: function () {
            	// console.log("principal.service.js: Returning User:" +
				// JSON.stringify(_identity));
            	return _identity;
            },
            getModuleMenus: function(){ 
            	var deferred = $q.defer();

                // check and see if we have retrieved the _module2MenuItems data from the
				// server.
                // if we have, reuse it by immediately resolving
                if (angular.isDefined(_module2MenuItems)) {
                    deferred.resolve(_module2MenuItems);

                    return deferred.promise;
                }
                
                // retrieve the identity data from the server, update the
				// identity object, and then resolve.

            	$http.get("/api/module2MenuItems/role").then(
            			function (response) {
            				_module2MenuItems = response.data;
            				 deferred.resolve(_module2MenuItems);
				}).catch(function() {
					_module2MenuItems = null;
					deferred.resolve(_module2MenuItems);
                });
            	
                return deferred.promise;
    		},                         
            getOrgRole: function(){
               	var deferred = $q.defer();

                // check and see if we have retrieved the _module2MenuItems data from the
				// server.
                // if we have, reuse it by immediately resolving
                if (angular.isDefined(_orgRole)) {
                    deferred.resolve(_orgRole);

                    return deferred.promise;
                }
                
                // retrieve the _orgRole data from the server, update the
				// identity object, and then resolve.

            	$http.get("/api/module2MenuItems/role").$promise.then(
            			function (response) {
            				_orgRole = response.data;
            				 deferred.resolve(_module2MenuItems);
				}).catch(function() {
					_orgRole = null;
					deferred.resolve(_orgRole);
                });
            	
                return deferred.promise;
            }
        };
    });
