# business microservices
each business microservice: **user**, **product** and **order** of this sub forder is implemented into hexagonal architecture
## modu-shop-hexagonal-architecture-bs-ms-user
this mciroservice manager customers
## modu-shop-hexagonal-architecture-bs-ms-product
this mciroservice manager products
## modu-shop-hexagonal-architecture-bs-ms-order
- this mciroservice manager customers orders
- this order service a cloud openfeign to get remote user and product

- Those microservices uses **oauth2** protocol to validate jwt sent in the costumer request.
- That is, they delegate jwt validation to identity provider, here keycloak



