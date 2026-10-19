# Sprint Status

_Last updated: 2026-10-19_

## Batch 00 — Repository audit, research, architecture
Docs complete on `batch/00-docs` (unmerged). Awaiting merge decision.

## Batch 01 — Application foundation (branch `batch/01-foundation`)
Status: **In Review** (not Done until the owner accepts).
- `./mvnw clean verify` on JDK 25: BUILD SUCCESS, 11 tests, 0 failures (2026-10-19).
- Testcontainers PostgreSQL integration tests exercise Flyway V1 (Docker required).
- Compose Postgres verified healthy on a spare port (5432 is held by an unrelated existing container).
- CI workflow written but **not yet run** (nothing pushed).

## Environment
- `JAVA_HOME` must point at JDK 25 (README). Shell sessions used `C:\Users\USER\.jdks\temurin-25.0.3`; the permanent machine setting is the owner's to apply.
