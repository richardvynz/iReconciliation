package com.vinz.ireconciliation.shared.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

/**
 * Immutable monetary amount: exact {@link BigDecimal} plus ISO 4217 currency. The amount is normalised to
 * the currency's minor-unit scale; an amount that would lose precision is rejected, never silently rounded.
 * There is deliberately no floating-point factory.
 */
public record Money(BigDecimal amount, Currency currency) {

	public Money {
		if (amount == null || currency == null) {
			throw new InvalidMoneyException("Amount and currency are required.");
		}
		int digits = currency.getDefaultFractionDigits();
		if (digits < 0) {
			throw new InvalidMoneyException("Currency has no minor-unit scale: " + currency.getCurrencyCode());
		}
		try {
			amount = amount.setScale(digits, RoundingMode.UNNECESSARY);
		}
		catch (ArithmeticException ex) {
			throw new InvalidMoneyException("Amount has more than %d decimal places for %s."
				.formatted(digits, currency.getCurrencyCode()));
		}
	}

	public static Money of(String amount, String currencyCode) {
		if (amount == null || currencyCode == null) {
			throw new InvalidMoneyException("Amount and currency are required.");
		}
		try {
			return new Money(new BigDecimal(amount), Currency.getInstance(currencyCode));
		}
		catch (IllegalArgumentException ex) { // NumberFormatException and unknown currency codes
			throw new InvalidMoneyException("Invalid amount or ISO 4217 currency code.");
		}
	}

	public Money add(Money other) {
		requireSameCurrency(other);
		return new Money(amount.add(other.amount), currency);
	}

	public Money subtract(Money other) {
		requireSameCurrency(other);
		return new Money(amount.subtract(other.amount), currency);
	}

	public Money negate() {
		return new Money(amount.negate(), currency);
	}

	public boolean isZero() {
		return amount.signum() == 0;
	}

	private void requireSameCurrency(Money other) {
		if (!currency.equals(other.currency)) {
			throw new InvalidMoneyException("Currency mismatch: %s vs %s."
				.formatted(currency.getCurrencyCode(), other.currency.getCurrencyCode()));
		}
	}
}
