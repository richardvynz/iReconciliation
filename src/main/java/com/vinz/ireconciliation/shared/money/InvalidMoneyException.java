package com.vinz.ireconciliation.shared.money;

import com.vinz.ireconciliation.shared.error.ReconciliationException;

public class InvalidMoneyException extends ReconciliationException {

	public InvalidMoneyException(String message) {
		super(message);
	}
}
