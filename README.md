# Claude SpringBoot Architect

A minimal Spring Boot demo service used as a practice environment for the **Claude Code Architect Foundations** certification.

The goal is not to ship features — it is to practice architectural reasoning: knowing when to act, when to question, and how to evaluate trade-offs in a layered Java service.

---

## Purpose

This project supports the following learning objectives:

- **Architectural discernment** — recognizing layer responsibility violations, missing error contracts, and over-engineering
- **Deliberate use of Claude Code skills** — planning mode, thinking mode, architecture review, and security review
- **Trade-off reasoning** — evaluating options rather than jumping to implementation
- **Code review practice** — reading code critically as a Staff-level architect would

---

## Architecture

Spring Boot 4.0.6 · Java 17 · Maven

```
controller/
  CalculatorController.java     — HTTP layer, delegates to service
  GlobalExceptionHandler.java   — maps domain exceptions to HTTP error responses

service/
  CalculatorService.java        — arithmetic logic, owns divide-by-zero guard

model/
  OperationRequest.java         — inbound request (validated)
  OperationResult.java          — outbound response (immutable)
  OperationType.java            — ADD | SUBTRACT | MULTIPLY | DIVIDE
```

The three-layer structure enforces clear separation of concerns:

| Layer | Responsibility |
|---|---|
| Controller | Deserialize input, invoke service, serialize output |
| Service | Business rules and domain validation |
| Model | Data shape only — no logic |

---

## API

### POST `/api/calculate`

**Request**
```json
{
  "a": 10,
  "b": 4,
  "operation": "DIVIDE"
}
```

**Response `200 OK`**
```json
{
  "a": 10,
  "b": 4,
  "operation": "DIVIDE",
  "result": 2.5
}
```

**Response `400 Bad Request`** — invalid input (null operation, divide by zero)
```json
{
  "error": "Division by zero is not allowed"
}
```

---

## Build & Run

```bash
# Windows
mvnw.cmd clean install
mvnw.cmd spring-boot:run
mvnw.cmd test

# Unix
./mvnw clean install
./mvnw spring-boot:run
./mvnw test
```

---

## Architectural Decisions Log

### Error contract via `@RestControllerAdvice`
`GlobalExceptionHandler` intercepts `IllegalArgumentException` (divide-by-zero from the service) and `MethodArgumentNotValidException` (bean validation failures on the request model) and maps both to `400 Bad Request` with a consistent `{"error": "..."}` body. Without this, Spring returns a generic 500 that leaks internal details and gives callers no actionable signal.

### Bean validation on `OperationRequest`
`@NotNull` on the `operation` field, activated by `@Valid` in the controller, catches a missing or null operation before it reaches the service's `switch` expression — which would otherwise throw a `NullPointerException` with no useful HTTP contract.

### What was intentionally left out
- No catch-all `Exception` handler — hiding unknown errors during practice would defeat the purpose
- No Lombok — plain getters/setters keep the model transparent for review exercises
- No persistence layer — the service is stateless by design; adding a database would shift focus away from HTTP contract and layer responsibility
- No service-level `@Validated` — the entry point is HTTP only, so controller-level validation is sufficient here

---

## Practice Exercises

Suggested exercises for the certification track. Each targets a specific architectural skill.

| Exercise | Skill practiced |
|---|---|
| Add a `GET /api/calculate/history` endpoint | Layer responsibility — where does history storage belong? |
| Support `double` operands instead of `int` | API contract evolution, backward compatibility |
| Add a second operation domain (e.g., string utilities) | Package structure, cohesion |
| Write `@WebMvcTest` for the controller | Testing the HTTP contract independently of business logic |
| Extract an `ErrorResponse` record | Response shape ownership, consistency |
