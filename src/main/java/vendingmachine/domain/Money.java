package vendingmachine.domain;

import vendingmachine.exception.CustomException;
import vendingmachine.exception.ErrorMessage;

public class Money {
    private final int money;

    public Money(final String money) {
        this.money = validate(money);
    }

    private int validate(String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException numberFormatException) {
            throw CustomException.from(ErrorMessage.NUMBER_INPUT_ERROR);
        }
    }

    public int getMoney() {
        return money;
    }
}
