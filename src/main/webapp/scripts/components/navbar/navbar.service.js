'use strict';

angular.module('watererpApp').factory(
		'Navbar',
		function Navbar($rootScope, $state, $q, $location) {
			return {

				getCurrentModule : function(module2MenuItems) { //Return module object for a given module.
					var path = $location.absUrl()
					var moduleName = "";
					// console.log("This is the path:" + path)

					// if
					// path="http://localhost:8080/billingAndCollectionMain#/"
					// below will give "billingAndCollectionMain"
					var main = path.split("#")[0];
					// console.log("This is the main url:" + main)
					var parts = main.split("/");
					if (parts[parts.length - 1].length == 0) {
						moduleName = parts[parts.length - 2];
					} else {
						moduleName = parts[parts.length - 1];
					}

					// console.log(moduleName); //
					// "billingAndCollectionMain"

					moduleName = moduleName.slice(0, -4);

					// console.log(moduleName); // "billingAndCollection

					var matches = module2MenuItems.filter(function(val, index,
							array) {
						return val.server_url === moduleName;
					});

					return matches[0];

				}

			};
		});
