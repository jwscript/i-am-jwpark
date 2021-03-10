package net.jwpark.domain;

public class Result {
	private boolean valid;

	private String errorMessage;

	public boolean isValid() {
		return this.valid;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	private Result() {
		this(true, null);
	}

	private Result(boolean valid, String errorMessage) {
		this.valid = valid;
		this.errorMessage = errorMessage;
	}

	public static Result ok() {
		return new Result();
	}

	public static Result fail(String errorMessage) {
		return new Result(false, errorMessage);
	}
}
