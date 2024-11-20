package vendingmachine;

import vendingmachine.controller.MachineController;
import vendingmachine.service.ItemService;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class Application {
    public static void main(String[] args) {
        MachineController machineController = new MachineController(new InputView(), new OutputView(),
                new ItemService());
        machineController.start();
    }
}
