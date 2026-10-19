package com.vinz.ireconciliation.shared.error;

/**
 * Base type for expected, domain-level failures. Mapped to HTTP 422 by {@link ApiExceptionHandler}.
 */
public class ReconciliationException extends RuntimeException {

	public ReconciliationException(String message) {
		super(message);
	}
}
