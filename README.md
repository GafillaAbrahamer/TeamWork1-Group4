
# Ecoembes TW1 — Central Server Prototype

Spring Boot + Gradle. No persistence; all state simulated in service layer. Swagger UI included.

## Run
```bash
./gradlew bootRun
```
Then open: http://localhost:8080/swagger-ui/index.html

## Auth flow
1) POST `/auth/login` with JSON `{"email":"alice@eco.com","password":"x"}` → copy `token` from response
2) For all other endpoints, add header: `X-Token: <token>`

## Example sequence
```bash
# Login
curl -s localhost:8080/auth/login -H 'Content-Type: application/json' -d '{"email":"alice@eco.com","password":"x"}'

# List plants
curl -s localhost:8080/plants -H "X-Token: <token>"

# Check capacity
curl -s 'localhost:8080/plants/1/capacity?date=2025-11-10' -H "X-Token: <token>"

# Create dumpster
curl -s localhost:8080/dumpsters -H 'Content-Type: application/json' -H "X-Token: <token>" -d '{"id":10,"address":"C/ Nueva 5","postalCode":48002,"capacity":150}'

# Update sensor data
curl -s localhost:8080/dumpsters/10/sensor-check -H 'Content-Type: application/json' -H "X-Token: <token>" -d '{"containersEstimate":90,"fillLevel":"ORANGE"}'

# Get status by postal code/date
curl -s 'localhost:8080/dumpsters/status?postalCode=48002&date=2025-11-10' -H "X-Token: <token)"

# Create assignment (needs dumpster estimates set)
curl -s localhost:8080/assignments -H 'Content-Type: application/json' -H "X-Token: <token>" -d '{"plantId":1,"dumpsterIds":[10],"date":"2025-11-10"}'
```

## Notes
- Fill levels: `GREEN | ORANGE | RED`
- Capacity rule: toy conversion of containers→tons: `1 ton per 10 containers` to validate capacity.
- Error handling: standardized JSON via `GlobalExceptionHandler`.
- This prototype matches the TW1 spec: REST endpoints, in-memory "state management", Swagger documentation.
