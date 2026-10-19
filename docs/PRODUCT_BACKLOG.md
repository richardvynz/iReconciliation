# Product Backlog

Statuses: Backlog / Ready / In Progress / In Review / Done / Blocked. Each item becomes a GitHub Issue with detailed acceptance criteria when its batch is planned.

| ID | Batch | Item | Priority | Depends on | Status |
|---|---|---|---|---|---|
| IR-000 | 00 | Repository audit, architecture, decisions, docs | P0 | — | In Review |
| IR-001 | 01 | Foundation: Java 25 build/enforcer, datasource config, baseline Flyway migration, Actuator, error model, `Money`, Docker Compose PostgreSQL, Testcontainers integration tests, CI, `.gitignore` hardening, README | P0 | IR-000 | Ready |
| IR-002 | 02 | Security baseline: authentication, roles, audit hooks | P0 | IR-001 | Backlog |
| IR-003 | 03 | Canonical transaction model; CSV/REST ingestion, idempotent, provenance | P0 | IR-001 | Backlog |
| IR-004 | 04 | Deterministic reconciliation engine and reproducible runs | P0 | IR-003 | Backlog |
| IR-005 | 04 | Exception detection: missing, duplicate, conflict, stuck | P0 | IR-004 | Backlog |
| IR-006 | 04 | Payment journey reconstruction (out-of-order tolerant) | P1 | IR-003 | Backlog |
| IR-007 | 05 | Case management and audit trail | P1 | IR-002, IR-005 | Backlog |
| IR-008 | 05 | AI investigation: `InvestigationAssistant`, OpenAI adapter via Spring AI, redaction, structured output | P1 | IR-005, IR-006, IR-007 | Backlog |
| IR-009 | 06 | Reporting: exposure, results, KPIs | P2 | IR-004, IR-005 | Backlog |
| IR-010 | 06 | OpenAPI docs (springdoc, after compatibility check) and demo dataset | P2 | IR-003 | Backlog |
| IR-011 | 07 | Kafka ingestion adapter (ADR required for any outbox) | P3 | IR-003 | Backlog |

Batch numbering beyond 01 is indicative and re-planned at each batch.
