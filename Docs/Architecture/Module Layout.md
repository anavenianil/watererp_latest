# Introduction

This document provides a walk-through of tbe changes we did for converting JHipster from a SPA to an MPA. Some Key Concepts

  - Modules & Menu Items:  A module represents a heading in a Menu List, essentially the top level element under which several Menu Items exist. The current architecture considers just 2 level hierarchy for menu items - Module and Menu Item
  - Roles: This is the authority a user has in the system. One user can have only one role
  - Modules are defined in "module" table and Menu Items in "menu_item" table
  - Each Module is linked to a Menu Item using the table module2_menu_item
  - Each Menu Item consists of its server path (Ex: `#/custDetailss`), Menu Item is also linked to a URL ("url" table) using menu_item2_url. URL here denotes the API end point on the server (without the /api prefix)
  - Each url2role defines which URLs are accessible from which roles. One URL can be accessible from many roles
  - URL2ROLE mapping is used in ensuring server side authorization for each API request

Module has a significance not only as a main heading for a menu list, but also for ensuring SPA/MPA functionality
> Each module is self-contained in an SPA
> The initial page you see when you get to the application is necessarily the Logon screen
> Once you login into the application, based on the role, list of modules available to the user are arrived at. jhi_user_authority -> url2role -> menu_item2url -> module2_menu_item

## Module Config Checklist
### Database Configuration
* Insert new module in `module` table
* Create menu links under this module by inserting rows in `menu_item` table
* Link these two tables
* Create API end-points for these menu_items (`url` and `menu_item2url` tables)
* Link urls to roles

### Client Configuration
* Pay attention to the server_url column in the module table
* This plays key role in the configuration on client side
* As MPA revolves around module, when a module icon is selected in front-end, the call is sent to /<Module>Main#/. This is rendered by Thymeleaf using `ThymeleafResource` class
* The mark-up for module rendering is in dashboard.html and dashboard.controller.js
* The actual mark-up for module will be /<Module>Main#/module/<Module>
* Once the full page is rendered by Thymeleaf and loaded in browser, AngularJS will route it to /module/:moduleName configured in main.js
* This is a dynamic TemplateURL which points to src/main/webapp/scripts/app/module/<Module>.html and src/main/webapp/scripts/app/module/<module>.controller.js. These files should exist compulsorily
* The router configuration in src/main/webapp/scripts/app/main/main.js has a dynamic configuration for `moduleDashboard` state. Controller is not specified here. It is mentioned directly in the html file like so: `<div ng-Controller='BillCycleRunController'>`
* Do not forget to add the new .html and .js to the corresponding module html file in src/main/resources/templates/<module>.html
