# iReconciliation — Architecture

## Style
Modular monolith, package-by-feature, Spring MVC, Spring Data JPA, PostgreSQL, Flyway. Constructor injection, transactional service methods, simple REST APIs, no premature abstraction.

## Modules (`com.vinz.ireconciliation.*`)
| Module | Responsibility |
|---|---|
| `ingestion` | Transport-independent ingestion services; REST and CSV adapters on top. Raw records + provenance, idempotent via natural key / content hash. |
| `ledger` | Canonical transactions and money value types. |
| `reconciliation` | Deterministic matching engine, runs, ambiguity flags. |
| `journey` | Payment lifecycle reconstruction. |
| `detection` | Exception detectors. |
| `investigation` | Provider-neutral `InvestigationAssistant` port; OpenAI/Spring AI adapter in an infrastructure sub-package (Batch 05). Read-only evidence tools. |
| `cases` | Case management and review workflow. |
| `audit` | Append-only audit log. |
| `reporting` | Exposure and KPI read models. |
| `shared` | `Money`, error model, clock, redaction utilities. |

Rules: modules talk through service interfaces, not each other's repositories; no cyclic dependencies (enforced by ArchUnit once added — compatibility to be verified before adoption); controllers are thin; domain logic has no web or AI dependencies.

## Money
`BigDecimal` with explicit scale; DB `NUMERIC(19,4)`; currency `CHAR(3)` validated against ISO 4217. No floating point.

## Determinism and reproducibility
Reconciliation runs persist ruleset version, parameters and input snapshot ids. Matching uses stable ordering and explicit tie handling; ties become *ambiguous* results.

## AI boundary
Port `InvestigationAssistant` (domain side) → adapter (infrastructure). Inputs: minimal, redacted, synthetic-safe evidence. Outputs: untrusted, parsed against a schema; recommendations are proposals requiring human approval. No tool can write to ledger or trigger payment movement. Credentials are never included in prompts.

## Security
Spring Security introduced before any operational API is exposed (Batch 02). Privileged actions audited. Uploads, events and LLM output are untrusted. Secrets via environment variables only.

## Local environment
- Java 25 required. `JAVA_HOME` must point to a JDK 25 (see DECISIONS.md ADR-005).
- PostgreSQL via Docker Compose (Batch 01). Integration tests use Testcontainers PostgreSQL, so **Docker is required to run the integration suite**.

## Evolution
Kafka consumers/producers may be added in Batch 07 as another adapter over the same ingestion services; no outbox before a concrete reliable-publication requirement exists.
