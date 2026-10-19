# iReconciliation — Product Requirements (draft)

## Users
- **Reconciliation analyst** — reviews exceptions, works cases.
- **Operations lead** — approves recovery actions, monitors exposure.
- **Auditor** — reads audit trails read-only.
- **Integrator** — submits data via API/CSV.

## Capabilities
| Area | Requirement |
|---|---|
| Ingestion | Accept CSV uploads and REST submissions for ledger, processor and settlement records. Keep raw payload, source, file/row reference and content hash. Re-submission is idempotent. Reject/quarantine malformed rows with reasons. |
| Canonical model | Normalise to transactions with amount (`NUMERIC`), ISO currency, source identifiers, status, timestamps. |
| Reconciliation | Deterministic matching rules (reference, amount, currency, date window). Runs record ruleset version, parameters and input snapshot; rerun gives identical output. Ambiguous candidates are flagged, never silently resolved. |
| Journeys | Rebuild lifecycle per payment from events; tolerate duplicates and out-of-order arrival. |
| Detection | Missing settlement, duplicate, status conflict, stuck transaction. "Missing evidence" is distinct from "confirmed failure". |
| Investigation | AI summarises verified evidence, suggests causes and next steps, produces structured case summaries. Read-only tools, redacted minimal input, schema-validated output. |
| Cases | Create/assign/review/close cases; consequential actions require human approval. |
| Audit | Append-only record of privileged actions with actor and timestamp. |
| Reporting | Settlement exposure, reconciliation results, operational KPIs. |

## Non-functional
Structured logs; configuration via environment variables; explicit error responses; Flyway-managed schema; automated unit and integration tests; synthetic data only.

## Scope by batch
See PRODUCT_BACKLOG.md.
