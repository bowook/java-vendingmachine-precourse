package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.domain.Money;

public class InputView {
    private final static String HOLD_MONEY = "자판기가 보유하고 있는 금액을 입력해 주세요.";

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
