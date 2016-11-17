package monopoly.cells;

import monopoly.Cell;
import monopoly.Dice;
import monopoly.MainController;
import monopoly.Player;

public class UtilityCell extends Cell {

    public static final String COLOR_GROUP = "UTILITY";
    private static int PRICE;

    public static void setPrice(int price) {
        UtilityCell.PRICE = price;
    }

    @Override
    public int getPrice() {
        return UtilityCell.PRICE;
    }

    public int getRent(int diceRoll) {
        if (player.numberOfUtil() == 1) {
                return diceRoll * 4;
        } else if (player.numberOfUtil() >= 2) {
                return diceRoll * 10;
        }
        return 0;
    }

    @Override
    public void playAction(MainController mainCtl) {
        Player currentPlayer;
        if (!isAvailable()) {
            currentPlayer = mainCtl.getCurrentPlayer();
            if (player != currentPlayer) {
                mainCtl.doUtilRoll();
                Dice dice = mainCtl.getUtilDice();
                mainCtl.payRentTo(player, getRent(dice.getTotal()));
            }
        }
    }
}
