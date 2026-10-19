package com.vinz.ireconciliation.shared.error;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

class ApiExceptionHandlerTest {

	private final ApiExceptionHandler handler = new ApiExceptionHandler();

	@Test
	void domainExceptionMapsTo422() {
		ProblemDetail p = handler.handleDomain(new ReconciliationException("bad rule"));
		assertThat(p.getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());
		assertThat(p.getDetail()).isEqualTo("bad rule");
	}

	@Test
	void unexpectedExceptionDoesNotLeakDetails() {
		ProblemDetail p = handler.handleUnexpected(new IllegalStateException("db password=secret"));
		assertThat(p.getStatus()).isEqualTo(500);
		assertThat(p.getDetail()).doesNotContain("secret");
	}
}
