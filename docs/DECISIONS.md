# Architecture Decision Records

| ID | Decision | Status |
|---|---|---|
| ADR-001 | Technology baseline | Accepted |
| ADR-002 | Defer Kafka to Batch 07 | Accepted |
| ADR-003 | OpenAI via Spring AI behind a provider-neutral port | Accepted |
| ADR-004 | Dependency admission policy | Accepted |
| ADR-005 | Run Maven on JDK 25 | Accepted |
| ADR-006 | Integration tests use Testcontainers PostgreSQL | Accepted |
| ADR-007 | Git history and ignore policy | Accepted |

## ADR-001 Technology baseline
Java 25 LTS, Spring Boot 4.1.1, Maven, Spring MVC, Spring Data JPA, PostgreSQL, Flyway, Lombok, modular monolith. The existing `pom.xml` is the source of truth. No downgrade without a demonstrated compatibility blocker.
**Verified (2026-10-19):** the 4.1.1 parent and BOM resolve on Maven Central; BOM manages Spring Framework 7.0.9, Hibernate 7.4.5, Flyway 12.4.0, PostgreSQL driver 42.7.13, Spring Security 7.1.1, JUnit 6.0.3, Mockito 5.23.0, Testcontainers 2.0.5.
Lombok guidance: boilerplate only; prefer records for DTOs/value objects; avoid `@Data` on JPA entities.

## ADR-002 Kafka deferred
Ingestion starts with REST and CSV. Ingestion logic stays transport-independent so Kafka can be added in Batch 07. No transactional outbox in Batch 01 unless a specific reliable event-publication requirement justifies it.

## ADR-003 AI provider
OpenAI as initial provider through a compatible Spring AI integration. Provider-neutral `InvestigationAssistant` interface; provider-specific code only in an infrastructure adapter. No AI dependencies before Batch 05. Synthetic data, minimal input, redaction, no credentials to the model.

## ADR-004 Dependency admission
Before adding any dependency: verify Boot 4.1.1 and Java 25 compatibility, prefer official stable releases, document limitations. springdoc and Spring AI are not added prematurely.

## ADR-005 Java environment
Finding: `JAVA_HOME` pointed at JDK 21.0.8 while `java` on PATH was Temurin 25.0.3, so Maven (which uses `JAVA_HOME`) could not compile for Java 25.
Decision: set `JAVA_HOME` to a JDK 25 (e.g. `C:\Users\USER\.jdks\temurin-25.0.3`) for builds; Batch 01 adds a Maven enforcer rule requiring Java 25+ and documents setup in the README. Local machine configuration change is made by the user or approved in Batch 01.

## ADR-006 Integration tests
No failing tests are disabled to get green. Batch 01 adds Testcontainers (BOM-managed 2.x; artifacts `testcontainers-postgresql` and `testcontainers-junit-jupiter` exist at 2.0.5; Spring Boot's `spring-boot-testcontainers` 4.1.1 exists) so Flyway migrations run against real PostgreSQL. Docker is required for the integration suite; unit tests do not need it.

## ADR-007 Git
Repository initialised; `main` pushed with initial README only (no history rewrite). Work proceeds on `batch/NN-*` branches. No force-push, merge or branch deletion without approval. `.gitignore` to be extended in Batch 01 for secrets and local env files (`.env*`, `*.pem`, `application-local*.yaml` etc.) in addition to existing build and IDE entries. The code baseline is committed only after it builds and tests.
