# Admin

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 13.0.1.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.

## Troubleshhoting

###

Error: node_modules/keycloak-angular/lib/core/services/keycloak.service.d.ts:3:8 - error TS1259: Module '"/home/skok/Dev/SCM/Git/Centilliard/holiday/admin/node_modules/keycloak-js/dist/keycloak"' can only be default-imported using the 'allowSyntheticDefaultImports' flag

3 import Keycloak from 'keycloak-js';
         ~~~~~~~~

  node_modules/keycloak-js/dist/keycloak.d.ts:23:1
    23 export = Keycloak;
       ~~~~~~~~~~~~~~~~~~
    This module is declared with using 'export =', and can only be used with a default import when using the 'allowSyntheticDefaultImports' fla

See:    
https://github.com/mauriciovigolo/keycloak-angular/issues/359#issuecomment-974861770

add "allowSyntheticDefaultImports": true to angularCompilerOptions in tsconfig.json