package vendingmachine.controller;

import vendingmachine.domain.Machine;
import vendingmachine.exception.CustomException;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class MachineController {

    private final InputView inputView;
    private final OutputView outputView;

    public MachineController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Machine machine = holdMachineMoney();
        outputView.writeHoldMachineMoney(machine);
    }
    

    private Machine holdMachineMoney() {
        while (true) {
            try {
                return new Machine(inputView.readHoldMoney());
            } catch (CustomException customException) {
                outputView.writeErrorMessage(customException.getMessage());
            }
        }
    }
}
