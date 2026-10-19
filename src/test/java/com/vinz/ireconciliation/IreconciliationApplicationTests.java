package com.vinz.ireconciliation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
class IreconciliationApplicationTests {

	@Autowired
	JdbcTemplate jdbc;

	@Test
	void contextLoadsAndFlywayBaselineApplied() {
		String value = jdbc.queryForObject(
				"select meta_value from app_metadata where meta_key = 'schema_baseline'", String.class);
		assertThat(value).isEqualTo("batch-01");
	}
}
