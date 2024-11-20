package vendingmachine.domain;

import vendingmachine.exception.CustomException;
import vendingmachine.exception.ErrorMessage;

public class Item {
    private final String name;
    private final Money money;
    private int quantity;

    public Item(final String name, final Money money, final String quantity) {
        validateMoney(money);
        this.quantity = validateQuantity(quantity);
        this.name = name;
        this.money = money;
    }

    private int validateQuantity(String quantity) {
        try {
            return Integer.parseInt(quantity);
        } catch (NumberFormatException numberFormatException) {
            throw CustomException.from(ErrorMessage.ITEM_QUANTITY_IS_NOT_NUMBER);
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public void subtract() {
        quantity -= 1;
    }

    public int getMoney() {
        return money.getMoney();
    }

    private void validateMoney(Money money) {
        if (money.getMoney() < 100) {
            throw CustomException.from(ErrorMessage.ITEM_PRICE_MIN_PRICE);
        }
        if (money.getMoney() % 10 != 0) {
            throw CustomException.from(ErrorMessage.ITEM_PRICE_DIVIDE_CONDITION);
        }
    }


}
