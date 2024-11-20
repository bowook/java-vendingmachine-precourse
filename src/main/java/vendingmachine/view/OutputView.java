package vendingmachine.view;


import vendingmachine.domain.Machine;
import vendingmachine.domain.Money;

public class OutputView {
    private final static String HOLD_MACHINE_MONEY = "자판기가 보유한 동전";
    private final static String REPEAT_INPUT_MONEY = "투입 금액: %d원";
    private final static String REMAIN_MACHINE_MONEY = "잔돈";

    public void writeRemainMachineMoney(Machine machine) {
        System.out.println(REMAIN_MACHINE_MONEY);
        System.out.println(machine.toString());

    }

    public void writeRepeatInputMoney(Money money) {
        newLine();
        System.out.println(String.format(REPEAT_INPUT_MONEY, money.getMoney()));
    }

    public void writeHoldMachineMoney(Machine machine) {
        newLine();
        System.out.println(HOLD_MACHINE_MONEY);
        System.out.println(machine.initStatus());
    }

    public void writeErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    private void newLine() {
        System.out.print(System.lineSeparator());
    }

}
