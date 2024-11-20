package vendingmachine.exception;

public enum ErrorMessage {
    NUMBER_INPUT_ERROR("금액은 숫자여야 합니다."),
    NUMBER_INPUT_RANGE_ERROR("금액은 0보다 큰 숫자를 입력하셔야 합니다."),
    ITEM_STATUS_BRACKET_ERROR("상품명과 가격, 수량은 대괄호로 감싸야 합니다."),
    ITEM_STATUS_NOT_COMMA("상품명과 가격, 수량은 쉼표(,)로 구분지어야 합니다."),
    ITEM_PRICE_MIN_PRICE("상품 가격은 최소 100원부터 시작해야 합니다."),
    ITEM_PRICE_DIVIDE_CONDITION("상품 가격은 10원으로 나누어떨어져야 합니다."),
    ITEM_QUANTITY_IS_NOT_NUMBER("상품 수량은 숫자를 입력하셔야 합니다."),
    ITEM_NAME_NOT_IN_MACHINE("입력한 상품은 자판기에 존재하지 않습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}