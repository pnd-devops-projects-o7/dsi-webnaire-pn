# technical services
- ## modu-shop-configuration-service
  - this service is cloud config server that centralise all services configuration
  - it start first and when other services start, they come to connect to this config service to get their config
- ## modu-shop-registration-service:
  - it is a cloud service that serve as registry of other services
  - When other microservices start, they come to register with their name on this registry server
 
## modu-shop-backend-gateway-client
- it is a cloud gateway working as a token relay
- it receive customer request and delegate them to keycloak to be authenticated
- when keycloak authenticate the user request it generate a jwt that it send to to this backend-gateway-client
- this backend-gateway-client the received jwt to user request and the user uses received jwt to access to resource server
