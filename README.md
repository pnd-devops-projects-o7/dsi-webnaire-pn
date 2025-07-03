# Webnaire 03/07/2025
This git repo contains 5 folders:

# cloud-services-configurations-files
- this folder contains all configuration files for all services of modushop-app. 
- the configuration service pull configs from (this folder and distribute them to each service that asks its configurations.
- Refer configuration service config file (application.yml)
   -   to see how it is linked to this folder
   -   how each microservice configuration (bootstrap.yml) is linked to the configuration service
# modushop-docker-imgs-deploy
this folder contains a stack of deployment of the following services name into docker
- _modushop-db_
- _modushop-keycloak-idp_
-  **technical servces**:
    - _modushop-configuration-service_
    - _modushop-registration-service_
    - _modushop-backend-gateway-oauth2-client_
- **business microservices**:  
(only hexagonal architecture microservices are déployed into docker images):
  -  _modushop-hexagonal-archi-bs-ms-user_
  -  _modushop-hexagonal-archi-bs-ms-product_
  -  _modushop-hexagonal-archi-bs-ms-order_
# modushop-hexagonal-archi-micros-app
this folder contains source code for 3 business microservices written into hexagonal architecture:
  - modu-shop-hexagonal-architecture-bs-ms-order
  - modu-shop-hexagonal-architecture-bs-ms-product
  - modu-shop-hexagonal-architecture-bs-ms-user
# modushop-microservices-base-app
this folder contains source code for 3 business microservices written into mvc pattern:
- modu-shop-bs-microservice-order
- modu-shop-bs-microservice-product
- modu-shop-bs-microservice-user
# modushop-technical-services
this folder contains sources of technical services
- modu-shop-backend-gateway-client
- modu-shop-configuration-service
- modu-shop-registration-service

# to run modushop-app
- Configure working environment:
   - configure: maven (maven-3.9.9)
   -  install docker: https://www.docker.com/get-started/
   -  intall dev environment: intellij or vscode
- pull the repo in your local: ```git clone```
- after docker is installed and run, go into folder: ```modushop-docker-imgs-deploy``` and run:
   - ``` docker compose -f modushop-db-keycloak-compose up -d modushop-db```: this pull and run ```mysql:8.4.1 server docker image```
   - ``` docker exec -it modushop-db \bin\bash```: this command line executes mysql container
   - ```>mysql -u root -p``` (enter)
   - enter password: ```root```
   - now you are inside the ```mysql docker container``` and you can run sql requests, create 4 databases:
       - ```create database if not exists keycloak_db```: database for keycloak to save users, realms, ...etc
       - ```create database if not exists users_db```: database of user microservice
       - ```create database if not exists products_db```: database of product microservice
         ```create database if not exists orders_db```: database of user microservice
     
- clean and install the project with maven:
  - ```mvn clean install```
- into folder: ```modushop-docker-imgs-deploy``` run the command line below to build docker images modushop-app
   - ```docker compose -f modushop-db-keycloak-compose build```
   - ``` docker system prune``` to remove untaged docker images
- deploy the application with command line below:
   - ```docker compose -f  modushop-db-keycloak-compose up -d```

 ## keycloak doc:
 - refer keycloak documentation to see how to create realms, clients, client role, realm role and user
 - for modushop-app, i create:
    - realm: ```modushop_app_realm```
    - client: ```backend-gateway-client-id```
    - realm roles: ```modushop_realm_admin_role``` and ```modushop_realm_user_role```
    - client role: ```modushop_admin_role``` et ```modushop_user_role```
  
