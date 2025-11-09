
# Ecoembes TW1 — Central Server Prototype (Fixed DTOs)

Run:
```
./gradlew bootRun
```
Open Swagger:
```
http://localhost:8080/swagger-ui/index.html
```
Auth:
- POST `/auth/login` with `{"email":"test@eco.com","password":"x"}`
- Use returned token in header `X-Token` for other endpoints.
