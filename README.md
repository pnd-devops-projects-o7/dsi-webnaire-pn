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
