package com.vinz.ireconciliation.shared.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class MoneyTest {

	@Test
	void normalisesToCurrencyScale() {
		assertThat(Money.of("10", "USD").amount()).isEqualByComparingTo("10.00").extracting(BigDecimal::scale)
			.isEqualTo(2);
		assertThat(Money.of("5", "JPY").amount().scale()).isZero();
	}

	@Test
	void rejectsPrecisionLossInsteadOfRounding() {
		assertThatThrownBy(() -> Money.of("10.005", "USD")).isInstanceOf(InvalidMoneyException.class);
		assertThatThrownBy(() -> Money.of("1.5", "JPY")).isInstanceOf(InvalidMoneyException.class);
	}

	@Test
	void addsExactlyWithoutFloatingPointError() {
		assertThat(Money.of("0.10", "EUR").add(Money.of("0.20", "EUR"))).isEqualTo(Money.of("0.30", "EUR"));
	}

	@Test
	void subtractsAndNegates() {
		Money result = Money.of("5.00", "USD").subtract(Money.of("7.50", "USD"));
		assertThat(result).isEqualTo(Money.of("-2.50", "USD"));
		assertThat(result.negate()).isEqualTo(Money.of("2.50", "USD"));
		assertThat(Money.of("0", "USD").isZero()).isTrue();
	}

	@Test
	void rejectsCurrencyMismatch() {
		assertThatThrownBy(() -> Money.of("1", "USD").add(Money.of("1", "EUR")))
			.isInstanceOf(InvalidMoneyException.class)
			.hasMessageContaining("mismatch");
	}

	@Test
	void rejectsInvalidInput() {
		assertThatThrownBy(() -> Money.of("abc", "USD")).isInstanceOf(InvalidMoneyException.class);
		assertThatThrownBy(() -> Money.of("1", "ZZZ")).isInstanceOf(InvalidMoneyException.class);
		assertThatThrownBy(() -> Money.of(null, "USD")).isInstanceOf(InvalidMoneyException.class);
		assertThatThrownBy(() -> Money.of("1", "XXX")).isInstanceOf(InvalidMoneyException.class);
	}
}
