package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.domain.Money;

public class InputView {
    private final static String HOLD_MONEY = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private final static String HOLD_ITEMS = "상품명과 가격, 수량을 입력해 주세요.";
    private final static String USER_INPUT_MONEY = "투입 금액을 입력해 주세요.";
    private final static String USER_INPUT_ITEM = "구매할 상품명을 입력해 주세요.";

    public String readInputItem() {
        System.out.println(USER_INPUT_ITEM);
        return consoleRead();
    }

    public Money readInputMoney() {
        System.out.println();
        System.out.println(USER_INPUT_MONEY);
        return new Money(consoleRead());
    }

    public String readHoldItems() {
        System.out.println(HOLD_ITEMS);
        return consoleRead();
    }

    public Money readHoldMoney() {
        System.out.println(HOLD_MONEY);
        return new Money(consoleRead());
    }

    private String consoleRead() {
        return Console.readLine();
    }

    public void consoleClose() {
        Console.close();
    }

}
