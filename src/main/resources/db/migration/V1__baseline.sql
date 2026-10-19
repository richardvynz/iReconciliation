-- Baseline migration: schema bookkeeping only. Domain tables arrive in later batches.
CREATE TABLE app_metadata (
    meta_key   VARCHAR(100) PRIMARY KEY,
    meta_value VARCHAR(500) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
);

INSERT INTO app_metadata (meta_key, meta_value) VALUES ('schema_baseline', 'batch-01');
