# Online Payment Architecture

Online Payment has been implemented a separate standalone HTML + Bootstrap application (MINUS Angular JS) within JHipster. Back-end of course uses the API from JHipster.

  - Simple HTML located at src/main/webapp/onlinePaymentCust.html
  - This has no relation whatsoever with the Modules and other client architecture implemented under JHipster
  - It uses bootstrap3-typeahead.min.js located in the same directory
  - [TODO] This typeahead JS shall be integrated into bower
  - For validations, bootstrap validator is being used [https://github.com/1000hz/bootstrap-validator]
  - The api (`/api/onlinePaymentOrders`) needs to be called without authentication, hence changes to Security Configuration will be done. To be filled in later.
  - Necessary server side validations have been built into OnlinePaymentOrder.java
  - To enable a clean error report to client, `ExceptionTranslator` has been updated with following code:
```sh
    package com.callippus.water.erp.web.rest.errors;
    ...
    ...
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO processConstraintViolationException(ConstraintViolationException ex) {
    	Set<ConstraintViolation<?>> c = ex.getConstraintViolations();
        ErrorDTO dto = new ErrorDTO(ErrorConstants.ERR_VALIDATION);
    	for (ConstraintViolation<?> constraintViolation : c) {
    	    dto.add("",constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage() );
    	}
        return dto;    	
    }
```
  - A standalone API UnifiedPayment (`/api/unifiedPGResponse`) has also been written to enable back-end incoming request from the Payment Gateway (3rd Party app). To enable this, SecurityConfiguration.java has been modified as follows:


### Was earlier
```sh
  http.csrf()
				.and()
				.addFilterAfter(new CsrfCookieGeneratorFilter(),  
```

```sh
				.antMatchers("/api/account/reset_password/finish").permitAll()
				.antMatchers("/api/**").authenticated()
```
### Is Now
```sh
  http.csrf().ignoringAntMatchers("/api/unifiedPGResponse")
				.and()
				.addFilterAfter(new CsrfCookieGeneratorFilter(),  
```
```sh
				.antMatchers("/api/account/reset_password/finish").permitAll()
				.antMatchers("/api/unifiedPGResponse").permitAll()
				.antMatchers("/api/custDetailss/searchCAN/").permitAll()
				.antMatchers("/api/onlinePaymentOrders").permitAll()
				.antMatchers("/api/**").authenticated()
```

**Finally, the URL to access: ** [Online Cust Payment]

   [Online Cust Payment]: <https://localhost:8080/onlineCustPayment.html>
