package vendingmachine.view;


import vendingmachine.domain.Machine;

public class OutputView {
    private final static String HOLD_MACHINE_MONEY = "자판기가 보유한 동전";

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
