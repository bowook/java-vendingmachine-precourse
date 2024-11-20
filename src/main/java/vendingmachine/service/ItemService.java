package vendingmachine.service;

import java.util.ArrayList;
import java.util.List;
import vendingmachine.domain.Machine;
import vendingmachine.exception.CustomException;
import vendingmachine.exception.ErrorMessage;

public class ItemService {
    public void itemProcess(String initItems, Machine machine) {
        List<String> dividedColon = splitSemiColon(initItems);
        List<String> deletedBrackets = deleteSquareBrackets(dividedColon);
        splitComma(deletedBrackets, machine);
    }

    private void splitComma(List<String> itemStatus, Machine machine) {
        for (String status : itemStatus) {
            List<String> dividedItemStatus = List.of(status.split(","));
            if (dividedItemStatus.size() != 3) {
                throw CustomException.from(ErrorMessage.ITEM_STATUS_NOT_COMMA);
            }
            machine.addItems(dividedItemStatus);
        }
    }

    private List<String> deleteSquareBrackets(List<String> itemStatus) {
        List<String> deletedItemStatus = new ArrayList<>();
        for (String status : itemStatus) {
            if (!status.startsWith("[")) {
                throw CustomException.from(ErrorMessage.ITEM_STATUS_BRACKET_ERROR);
            }
            String deleteItemStatus = status.substring(1);
            if (deleteItemStatus.charAt(deleteItemStatus.length() - 1) != ']') {
                throw CustomException.from(ErrorMessage.ITEM_STATUS_BRACKET_ERROR);
            }
            deleteItemStatus = deleteItemStatus.substring(0, deleteItemStatus.length() - 1);
            deletedItemStatus.add(deleteItemStatus);
        }
        return deletedItemStatus;
    }

    private List<String> splitSemiColon(String itemStatus) {
        return List.of(itemStatus.split(";"));
    }
}
