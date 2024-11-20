package vendingmachine.controller;

import vendingmachine.domain.Machine;
import vendingmachine.domain.Money;
import vendingmachine.exception.CustomException;
import vendingmachine.service.ItemService;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class MachineController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ItemService itemService;

    public MachineController(final InputView inputView, final OutputView outputView, final ItemService itemService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.itemService = itemService;
    }

    public void start() {
        Machine machine = holdMachineMoney();
        outputView.writeHoldMachineMoney(machine);
        holdMachineItems(machine);
        Money userMoney = userInputMoney();
        while (true) {
            if (!retry(userMoney, machine)) {
                break;
            }
        }
        outputView.writeRemainMachineMoney(machine);
        inputView.consoleClose();
    }

    private boolean retry(Money userMoney, Machine machine) {
        outputView.writeRepeatInputMoney(userMoney);
        if (!machine.isPurchasePossible(userMoney) || !machine.isPurchaseQuantity()) {
            machine.subtractRemainCoin(userMoney);
            return false;
        }
        userInputItem(machine, userMoney);
        return true;
    }

    private void userInputItem(Machine machine, Money userMoney) {
        while (true) {
            try {
                String itemName = inputView.readInputItem();
                machine.sellItem(userMoney, itemName);
                break;
            } catch (CustomException customException) {
                outputView.writeErrorMessage(customException.getMessage());
            }
        }
    }

    private Money userInputMoney() {
        while (true) {
            try {
                return inputView.readInputMoney();
            } catch (CustomException customException) {
                outputView.writeErrorMessage(customException.getMessage());
            }
        }
    }

    private void holdMachineItems(Machine machine) {
        while (true) {
            try {
                itemService.itemProcess(inputView.readHoldItems(), machine);
                break;
            } catch (CustomException customException) {
                outputView.writeErrorMessage(customException.getMessage());
            }
        }
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
