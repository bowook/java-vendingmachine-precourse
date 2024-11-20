package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import vendingmachine.exception.CustomException;
import vendingmachine.exception.ErrorMessage;

public class Machine {
    private final List<Item> items;
    private int fiveHundred = 0;
    private int hundred = 0;
    private int fifty = 0;
    private int ten = 0;
    private int returnFiveHundred = 0;
    private int returnHundred = 0;
    private int returnFifty = 0;
    private int returnTen = 0;


    public Machine(final Money initMoney) {
        classification(initMoney);
        items = new ArrayList<>();
    }

    public void addItems(List<String> itemStatus) {
        String itemName = itemStatus.get(0);
        Money itemPrice = new Money(itemStatus.get(1));
        String itemQuantity = itemStatus.get(2);
        items.add(new Item(itemName, itemPrice, itemQuantity));
    }

    public String initStatus() {
        return String.format("500원 - %d개\n", fiveHundred)
                + String.format("100원 - %d개\n", hundred)
                + String.format("50원 - %d개\n", fifty)
                + String.format("10원 - %d개\n", ten);
    }

    public boolean isPurchasePossible(Money userMoney) {
        for (Item item : items) {
            if (userMoney.getMoney() >= item.getMoney()) {
                return true;
            }
        }
        return false;
    }

    public boolean isPurchaseQuantity() {
        int sum = 0;
        for (Item item : items) {
            sum += item.getQuantity();
        }
        return sum != 0;
    }

    public void subtractRemainCoin(Money userMoney) {
        if (totalRemainCoins() <= userMoney.getMoney()) {
            allRemains();
            return;
        }
        checkRemains(userMoney);
    }

    private boolean checkPurchasePossible(Money userMoney) {
        for (Item item : items) {
            if (item.getMoney() <= userMoney.getMoney()) {
                return true;
            }
        }
        return false;
    }

    public void sellItem(Money userMoney, String itemName) {
        validateItemName(itemName);
        for (Item item : items) {
            if (item.getName().equals(itemName) && userMoney.getMoney() >= item.getMoney() && item.getQuantity() > 0) {
                item.subtract();
                userMoney.subtract(item.getMoney());
            }
        }
    }

    @Override
    public String toString() {
        return remainFiveHundred() + remainHundred() + remainFifty() + remainTen();
    }

    private void checkRemains(Money userMoney) {
        while (userMoney.getMoney() != 0) {
            checkRemainFiveHundred(userMoney);
            checkRemainHundred(userMoney);
            checkRemainFifty(userMoney);
            checkRemainTen(userMoney);
        }
    }

    private void checkRemainTen(Money userMoney) {
        if (userMoney.getMoney() / Coin.COIN_10.getAmount() != 0 && ten > 0) {
            ten -= 1;
            returnTen += 1;
            userMoney.subtract(Coin.COIN_10.getAmount());
        }
    }

    private void checkRemainFifty(Money userMoney) {
        if (userMoney.getMoney() / Coin.COIN_50.getAmount() != 0 && fifty > 0) {
            fifty -= 1;
            returnFifty += 1;
            userMoney.subtract(Coin.COIN_50.getAmount());
        }
    }

    private void checkRemainHundred(Money userMoney) {
        if (userMoney.getMoney() / Coin.COIN_100.getAmount() != 0 && hundred > 0) {
            hundred -= 1;
            returnHundred += 1;
            userMoney.subtract(Coin.COIN_100.getAmount());
        }
    }

    private void checkRemainFiveHundred(Money userMoney) {
        if (userMoney.getMoney() / Coin.COIN_500.getAmount() != 0 && fiveHundred > 0) {
            fiveHundred -= 1;
            returnFiveHundred += 1;
            userMoney.subtract(Coin.COIN_500.getAmount());
        }
    }

    private void allRemains() {
        if (fiveHundred != 0) {
            returnFiveHundred += fiveHundred;
        }
        if (hundred != 0) {
            returnHundred += hundred;
        }
        if (fifty != 0) {
            returnFifty += fifty;
        }
        if (ten != 0) {
            returnTen += ten;
        }
    }

    private int totalRemainCoins() {
        return (Coin.COIN_500.getAmount() * fiveHundred) + (Coin.COIN_100.getAmount() * hundred)
                + (Coin.COIN_50.getAmount() * fifty) + (Coin.COIN_10.getAmount() * ten);
    }

    private void validateItemName(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                return;
            }
        }
        throw CustomException.from(ErrorMessage.ITEM_NAME_NOT_IN_MACHINE);
    }

    private String remainTen() {
        if (returnTen != 0) {
            return String.format("10원 - %d개\n", returnTen);
        }
        return "";
    }

    private String remainFifty() {
        if (returnFifty != 0) {
            return String.format("50원 - %d개\n", returnFifty);
        }
        return "";
    }

    private String remainHundred() {
        if (returnHundred != 0) {
            return String.format("100원 - %d개\n", returnHundred);
        }
        return "";
    }

    private String remainFiveHundred() {
        if (returnFiveHundred != 0) {
            return String.format("500원 - %d개\n", returnFiveHundred);
        }
        return "";
    }

    private void classification(Money initMoney) {
        while (initMoney.getMoney() != 0) {
            List<Integer> coins = initCoins(initMoney);
            init(initMoney, coins);
        }
    }

    private void init(Money initMoney, List<Integer> coins) {
        int coin = Randoms.pickNumberInList(coins);
        if (coin == Coin.COIN_500.getAmount()) {
            initFiveHundred(initMoney);
        }
        if (coin == Coin.COIN_100.getAmount()) {
            initHundred(initMoney);
        }
        if (coin == Coin.COIN_50.getAmount()) {
            initFifty(initMoney);
        }
        if (coin == Coin.COIN_10.getAmount()) {
            initTen(initMoney);
        }
    }

    private void initTen(Money initMoney) {
        ten += 1;
        initMoney.subtract(Coin.COIN_10.getAmount());
    }

    private void initFifty(Money initMoney) {
        fifty += 1;
        initMoney.subtract(Coin.COIN_50.getAmount());
    }

    private void initHundred(Money initMoney) {
        hundred += 1;
        initMoney.subtract(Coin.COIN_100.getAmount());
    }

    private void initFiveHundred(Money initMoney) {
        fiveHundred += 1;
        initMoney.subtract(Coin.COIN_500.getAmount());
    }

    private List<Integer> initCoins(Money initMoney) {
        List<Integer> coins = new ArrayList<>();
        if (initMoney.getMoney() >= Coin.COIN_500.getAmount()) {
            coins.add(Coin.COIN_500.getAmount());
        }
        if (initMoney.getMoney() >= Coin.COIN_100.getAmount()) {
            coins.add(Coin.COIN_100.getAmount());
        }
        if (initMoney.getMoney() >= Coin.COIN_50.getAmount()) {
            coins.add(Coin.COIN_50.getAmount());
        }
        if (initMoney.getMoney() >= Coin.COIN_10.getAmount()) {
            coins.add(Coin.COIN_10.getAmount());
        }
        return coins;
    }

}
