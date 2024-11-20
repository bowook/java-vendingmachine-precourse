package vendingmachine.service;

import java.util.ArrayList;
import java.util.List;
import vendingmachine.domain.Machine;
import vendingmachine.exception.CustomException;
import vendingmachine.exception.ErrorMessage;

public class ItemService {
    private final static String COMMA = ",";
    private final static String LEFT_SQUARE_BRACKET = "[";
    private final static String SEMI_COLON = ";";
    private final static char RIGHT_SQUARE_BRACKET = ']';

    public void itemProcess(String initItems, Machine machine) {
        List<String> dividedColon = splitSemiColon(initItems);
        List<String> deletedBrackets = deleteSquareBrackets(dividedColon);
        splitComma(deletedBrackets, machine);
    }

    private void splitComma(List<String> itemStatus, Machine machine) {
        for (String status : itemStatus) {
            List<String> dividedItemStatus = List.of(status.split(COMMA));
            if (dividedItemStatus.size() != 3) {
                throw CustomException.from(ErrorMessage.ITEM_STATUS_NOT_COMMA);
            }
            machine.addItems(dividedItemStatus);
        }
    }

    private List<String> deleteSquareBrackets(List<String> itemStatus) {
        List<String> deletedItemStatus = new ArrayList<>();
        for (String status : itemStatus) {
            String deleteItemStatus = isStartBracket(status);
            deleteItemStatus = isLastBracket(deleteItemStatus);
            deletedItemStatus.add(deleteItemStatus);
        }
        return deletedItemStatus;
    }

    private String isLastBracket(String deleteItemStatus) {
        if (deleteItemStatus.charAt(deleteItemStatus.length() - 1) != RIGHT_SQUARE_BRACKET) {
            throw CustomException.from(ErrorMessage.ITEM_STATUS_BRACKET_ERROR);
        }
        return deleteItemStatus.substring(0, deleteItemStatus.length() - 1);
    }

    private String isStartBracket(String status) {
        if (!status.startsWith(LEFT_SQUARE_BRACKET)) {
            throw CustomException.from(ErrorMessage.ITEM_STATUS_BRACKET_ERROR);
        }
        return status.substring(1);
    }

    private List<String> splitSemiColon(String itemStatus) {
        return List.of(itemStatus.split(SEMI_COLON));
    }

}
