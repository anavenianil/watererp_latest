#Code block to identify Form Errors.
```
		<ul>
			<li ng-repeat="(key, errors) in editForm.$error track by $index">
				<strong>{{ key }}</strong>
				errors
				<ul>
					<li ng-repeat="e in errors">
						{{ e.$name }} has an error:
						<strong>{{ key }}</strong>
						.
					</li>
				</ul>
			</li>
		</ul>
```				

#Angular JS http call
$http.get('/someUrl').
  success(function(data, status, headers, config) {
    // this callback will be called asynchronously
    // when the response is available
  })
  .error(function(data, status, headers, config) {
    // called asynchronously if an error occurs
    // or server returns response with an error status.
  });