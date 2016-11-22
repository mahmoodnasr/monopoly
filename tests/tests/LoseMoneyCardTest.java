package tests;

import tests.mocks.MockGUI;
import junit.framework.TestCase;
import monopoly.Card;
import monopoly.enums.CardType;
import tests.gameboardsTests.GameBoardCCLoseMoney;
import monopoly.MainController;
import monopoly.cards.MoneyCard;

public class LoseMoneyCardTest extends TestCase {
    private MainController mainCtl;
    private Card loseMoneyCard;

    @Override
    protected void setUp() {
        mainCtl = new MainController();
        mainCtl.setGameBoard(new GameBoardCCLoseMoney());
        mainCtl.setNumberOfPlayers(1);
        mainCtl.reset();
        mainCtl.setGUI(new MockGUI());
        loseMoneyCard = new MoneyCard("Pay 20 dollars", -20, CardType.CC);
        mainCtl.getGameBoard().addCard(loseMoneyCard);
    }
    
    public void testLoseMoneyCardAction() {
        int origMoney = mainCtl.getPlayer(0).getMoney();
        Card card = mainCtl.drawCCCard();
        assertEquals(loseMoneyCard, card);
        card.applyAction(mainCtl);
        assertEquals(origMoney - 20, mainCtl.getPlayer(0).getMoney());
    }
    
    public void testLoseMoneyCardUI() {
        mainCtl.movePlayer(mainCtl.getPlayer(0), 1);
        assertTrue(mainCtl.getGUI().isDrawCardButtonEnabled());
        assertFalse(mainCtl.getGUI().isEndTurnButtonEnabled());
        mainCtl.btnDrawCardClicked();
        assertFalse(mainCtl.getGUI().isDrawCardButtonEnabled());
        assertTrue(mainCtl.getGUI().isEndTurnButtonEnabled());
    }
}
