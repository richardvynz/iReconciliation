# iReconciliation — Project Charter

**Product:** iReconciliation (Intelligent Reconciliation)
**Coordinates:** `com.vinz:ireconciliation:0.0.1-SNAPSHOT` · base package `com.vinz.ireconciliation`
**Status:** Batch 00 (research and architecture)

## Mission
Enable financial institutions and payment providers to ingest transaction records from multiple sources, reconcile ledger / processor / settlement data deterministically, reconstruct payment lifecycles, detect exceptions, investigate them with evidence-grounded AI, and manage them through human review with full audit trails.

## Objectives
1. Ingest records from several sources with provenance (REST, CSV upload).
2. Reconcile internal ledger, processor and settlement records; flag missing, duplicate, ambiguous, delayed and mismatched items.
3. Reconstruct payment lifecycles from recorded events (duplicate and out-of-order tolerant).
4. Detect stuck payments and conflicting statuses.
5. Investigate exceptions with AI that only sees verified evidence.
6. Recommend safe operational actions; humans decide.
7. Maintain traceable audit histories; report exposure and performance.

## Non-negotiables
- `BigDecimal` + ISO 4217 currency for money; never `double`/`float`.
- Deterministic, reproducible reconciliation and payment-state evaluation.
- AI is an investigation assistant, never a financial authority: it cannot move money, reverse, alter balances, approve settlements or assert unsupported outcomes.
- Preserve source identifiers and provenance; idempotent processing.
- Synthetic data only in development; no committed secrets; redact logs and AI inputs.
- Treat uploads, external events and LLM output as untrusted.
- Authentication/authorization before any operational API is exposed publicly.

## Technology baseline (approved)
Java 25 LTS · Spring Boot 4.1.1 · Maven · Spring MVC · Spring Data JPA · PostgreSQL · Flyway · Jakarta Validation · Lombok · modular monolith. Versions are not downgraded without a demonstrated compatibility blocker (see DECISIONS.md).

## Out of scope unless justified by an approved ADR
WebFlux, Python, MongoDB, Kubernetes, Redis, microservices. Kafka deferred to Batch 07.

## Delivery process
Research → Plan → **Approval** → Implement → Verify, one focused branch per batch. Work items are GitHub Issues tracked in GitHub Projects with statuses Backlog / Ready / In Progress / In Review / Done / Blocked. No batch is Done until its acceptance criteria are verified. No force-push, merge or branch deletion without explicit approval.
