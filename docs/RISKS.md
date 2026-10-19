# Risk Register

| ID | Risk | Likelihood | Impact | Mitigation | Status |
|---|---|---|---|---|---|
| R1 | Library incompatibility with Boot 4.1.1 / Java 25 (springdoc, Spring AI, ArchUnit, Kafka) | Medium | High | ADR-004 admission check per dependency; spike before adoption; document limits | Open |
| R2 | Maven runs on JDK 21 (`JAVA_HOME`) and fails to compile for Java 25 | Certain until fixed | High | ADR-005; enforcer rule + README in Batch 01 | Open |
| R3 | `contextLoads` fails without a database; integration tests need Docker | Certain | Medium | Testcontainers PostgreSQL (ADR-006); never disable tests; Docker Desktop daemon currently stopped | Open |
| R4 | Repo history | — | — | Initialised, `main` pushed with README only | Mitigated |
| R5 | LLM hallucination affecting financial decisions | Medium | High | AI read-only, schema-validated output, human approval, no write tools | Open (Batch 05) |
| R6 | Sensitive data sent to the LLM | Low | High | Redaction, minimal input, synthetic data, no credentials | Open (Batch 05) |
| R7 | Duplicate/out-of-order events create wrong financial state | Medium | High | Idempotent ingestion, deterministic journey rules, tests | Open |
| R8 | Lombok misuse on JPA entities | Medium | Low | Guidance in ADR-001; code review | Open |
| R9 | Operational API exposed before authentication | Low | High | Security batch before exposure | Open |
