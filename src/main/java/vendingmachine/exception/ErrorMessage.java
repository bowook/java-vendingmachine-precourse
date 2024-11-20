package vendingmachine.exception;

public enum ErrorMessage {
    NUMBER_INPUT_ERROR("금액은 숫자여야 합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}