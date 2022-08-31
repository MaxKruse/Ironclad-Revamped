package CastAway.cards;
import CastAway.characters.TheCastAway;
import CastAway.powers.PreventExhaustPower;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractDefaultCard extends CustomCard {

    // Custom Abstract Cards can be a bit confusing. While this is a simple base for simply adding a second magic number,
    // if you're new to modding I suggest you skip this file until you know what unique things that aren't provided
    // by default, that you need in your own cards.

    // In this example, we use a custom Abstract Card in order to define a new magic number. From here on out, we can
    // simply use that in our cards, so long as we put "extends AbstractDynamicCard" instead of "extends CustomCard" at the start.
    // In simple terms, it's for things that we don't want to define again and again in every single card we make.

    // Exhaust stuff

    public int deckExhaust;
    public int baseDeckExhaust;
    public boolean upgradedDeckExhaust;
    public boolean isDeckExhaustModified;



    public int secondDamage;
    public int baseSecondDamage;
    public boolean upgradedSecondDamage;
    public boolean isSecondDamageModified;

    public AbstractDefaultCard(final String id,
                               final String name,
                               final String img,
                               final int cost,
                               final String rawDescription,
                               final CardType type,
                               final CardColor color,
                               final CardRarity rarity,
                               final CardTarget target) {

        super(id, name, img, cost, rawDescription, type, color, rarity, target);

        // Set all the things to their default values.
        isCostModified = false;
        isCostModifiedForTurn = false;
        isDamageModified = false;
        isBlockModified = false;
        isMagicNumberModified = false;
        isDeckExhaustModified = false;
        isSecondDamageModified = false;
    }

    public void displayUpgrades() { // Display the upgrade - when you click a card to upgrade it
        super.displayUpgrades();

        if (upgradedDeckExhaust) {
            deckExhaust = baseDeckExhaust;
            isDeckExhaustModified = true;
        }

        if (upgradedSecondDamage) {
            secondDamage = baseSecondDamage;
            isSecondDamageModified = true;
        }
    }

    public void upgradeDeckExhaust(int amount) {
        baseDeckExhaust += amount;
        deckExhaust = baseDeckExhaust;
        upgradedDeckExhaust = true;
    }

    public void upgradeSecondDamage(int amount) {
        baseSecondDamage += amount;
        secondDamage = baseSecondDamage;
        upgradedSecondDamage = true;
    }
}