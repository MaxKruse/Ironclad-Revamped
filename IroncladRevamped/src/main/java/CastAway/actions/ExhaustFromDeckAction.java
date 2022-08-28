package CastAway.actions;

import CastAway.characters.TheCastAway;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ExhaustFromDeckAction extends AbstractGameAction {
    final private int magicNumber;
    final private AbstractPlayer p;

    public ExhaustFromDeckAction(final AbstractPlayer p,
                                 final int magicNumber) {
        this.p = p;
        this.magicNumber = magicNumber;
    }

    @Override
    public void update() {
        TheCastAway.logger.info("Trying to exhaust hand-selected cards from the deck");
        // get cards in drawpile
        CardGroup drawPile = AbstractDungeon.player.drawPile;

        if (drawPile.size() <= magicNumber) {
            for(AbstractCard card : drawPile.group) {
                exhaustCard(card, drawPile);
            }
            isDone = true;
            return;
        }

        // we have more options
        AbstractDungeon.actionManager.addToBottom(new SelectCardsAction(drawPile.group, magicNumber, "Choose cards to Exhaust", true, p -> true, cardList -> {
            for(AbstractCard c : cardList) {
                exhaustCard(c, drawPile);
            }
        }));
        isDone = true;
    }

    private void exhaustCard(AbstractCard card, CardGroup pile) {
        AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, pile));
    }
}
