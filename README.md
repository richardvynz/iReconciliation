# iReconciliation

Intelligent Reconciliation: an AI-assisted financial operations platform (modular monolith).
Java 25 · Spring Boot 4.1.1 · Maven · Spring MVC · Spring Data JPA · PostgreSQL · Flyway.
See `docs/` for the charter, PRD, architecture, decisions, backlog and risks.

## Prerequisites
- **JDK 25.** Maven uses `JAVA_HOME`, not the `java` on `PATH`. The build fails fast (Maven Enforcer) on an older JDK.
  - Windows (PowerShell, permanent): `setx JAVA_HOME "C:\Users\<you>\.jdks\temurin-25.0.3"` and open a new terminal.
  - Per session: `$env:JAVA_HOME = "C:\Users\<you>\.jdks\temurin-25.0.3"`
- **Docker** (Docker Desktop running). The integration tests start PostgreSQL through Testcontainers, so
  `./mvnw verify` **requires Docker**. Pure unit tests (e.g. `MoneyTest`) do not.

## Build and test
```bash
./mvnw clean verify
```

## Run locally
```bash
cp .env.example .env        # synthetic local values only; .env is git-ignored
# if 5432 is taken, set POSTGRES_PORT in .env and adjust DB_URL
docker compose up -d postgres
# export the DB_* variables from .env, then:
./mvnw spring-boot:run
```
Health: `GET http://localhost:8080/actuator/health`

## Configuration
All settings come from environment variables (`DB_URL`, `DB_USER`, `DB_PASSWORD`). Never commit credentials. Use synthetic data only.

## Git workflow
One branch per batch (`batch/NN-name`). No force-push or merge without approval.
