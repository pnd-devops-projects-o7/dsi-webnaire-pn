# business microservices
this sub folder contains business microservices, each one implemented into **hexagonal architecture**
## modu-shop-hexagonal-architecture-bs-ms-user
business user microservice is responsible for managing customers that will order products
## modu-shop-hexagonal-architecture-bs-ms-product
business product microservice is responsible for managing products that will be ordered bu customers
## modu-shop-hexagonal-architecture-bs-ms-order
- this order mciroservice is responsible for managing customers orders.
- customer order is validated according customer and product availability
- this order microservice uses **cloud openfeign** to join remote user and product services apis

# security
- All those microservices uses **oauth2** protocol to validate **jwt** sent in the costumer request.
- That is, each microservice delegate **jwt validation** to identity provider, here keycloak




