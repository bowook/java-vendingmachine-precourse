package vendingmachine.domain;

import vendingmachine.exception.CustomException;
import vendingmachine.exception.ErrorMessage;

public class Money {
    private int money;

    public Money(final String money) {
        int tempMoney = validate(money);
        validateMoneyRange(tempMoney);
        this.money = tempMoney;
    }

    private void validateMoneyRange(int tempMoney) {
        if (tempMoney < 0) {
            throw CustomException.from(ErrorMessage.NUMBER_INPUT_RANGE_ERROR);
        }
    }

    private int validate(String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException numberFormatException) {
            throw CustomException.from(ErrorMessage.NUMBER_INPUT_ERROR);
        }
    }

    public void subtract(int money) {
        this.money -= money;
    }

    public int getMoney() {
        return money;
    }
}
