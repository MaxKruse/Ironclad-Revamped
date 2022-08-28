package CastAway.actions;

import CastAway.characters.TheCastAway;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.random.Random;

public class RandomExhaustFromDeckAction extends AbstractGameAction {
    final private int magicNumber;
    final private AbstractPlayer p;

    public RandomExhaustFromDeckAction(final AbstractPlayer p,
                                       final int magicNumber) {
        this.p = p;
        this.magicNumber = magicNumber;
    }

    @Override
    public void update() {
        TheCastAway.logger.info("Trying to randomly exhaust cards from the deck");

        // get cards in drawpile
        CardGroup drawPile = AbstractDungeon.player.drawPile;

        if (drawPile.size() <= magicNumber) {
            for(AbstractCard card : drawPile.group) {
                exhaustCard(card, drawPile);
            }
            isDone = true;
            return;
        }

        for(int i = 0; i < magicNumber; i++) {
            AbstractCard card = drawPile.getRandomCard(AbstractDungeon.cardRandomRng);
            exhaustCard(card, drawPile);
        }
        isDone = true;
    }

    private void exhaustCard(AbstractCard card, CardGroup pile) {
        AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, pile));
    }
}
