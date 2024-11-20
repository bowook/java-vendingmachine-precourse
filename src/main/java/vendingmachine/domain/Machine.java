package vendingmachine.domain;

public class Machine {
    private int fiveHundred = 0;
    private int hundred = 0;
    private int fifty = 0;
    private int ten = 0;

    public Machine(final Money initMoney) {
        classification(initMoney);
    }

    public String initStatus() {
        return String.format("500원 - %d개\n", fiveHundred)
                + String.format("100원 - %d개\n", hundred)
                + String.format("50원 - %d개\n", fifty)
                + String.format("10원 - %d개\n", ten);
    }

    private void classification(Money initMoney) {
        int money = checkFiveHundred(initMoney.getMoney());
        money = checkHundred(money);
        money = checkFifty(money);
        checkTen(money);
    }

    private void checkTen(int money) {
        int checkTen = money / Coin.COIN_10.getAmount();
        if (money >= Coin.COIN_10.getAmount() && checkTen != 0) {
            this.ten = checkTen;
        }
    }

    private int checkFifty(int money) {
        int checkFifty = money / Coin.COIN_50.getAmount();
        if (money >= Coin.COIN_50.getAmount() && checkFifty != 0) {
            this.fifty = checkFifty;
            money -= hundred * Coin.COIN_50.getAmount();
        }
        return money;
    }

    private int checkHundred(int money) {
        int checkHundred = money / Coin.COIN_100.getAmount();
        if (money >= Coin.COIN_100.getAmount() && checkHundred != 0) {
            this.hundred = checkHundred;
            money -= hundred * Coin.COIN_100.getAmount();
        }
        return money;
    }

    private int checkFiveHundred(int money) {
        int checkFiveHundred = money / Coin.COIN_500.getAmount();
        if (money >= Coin.COIN_500.getAmount() && checkFiveHundred != 0) {
            this.fiveHundred = checkFiveHundred;
            money -= fiveHundred * Coin.COIN_500.getAmount();
        }
        return money;
    }
}
