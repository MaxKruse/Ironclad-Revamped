package CastAway.cards.Attacks.uncommon;

import CastAway.cards.AbstractDynamicCard;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CastAway.DefaultMod;
import CastAway.characters.TheCastAway;

import java.util.List;

import static CastAway.DefaultMod.makeCardPath;

// public class Lost_Souls extends AbstractDynamicCard
// @AutoAdd.Ignore // Remove this line when you make a template. Refer to https://github.com/daviscook477/BaseMod/wiki/AutoAdd if you want to know what it does.
public class Lost_Souls extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Lost_Souls.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    //public static final String ID = DefaultMod.makeID("DefaultCommonAttack"); // DELETE THIS ONE.
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("Lost_Souls.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = TheCastAway.Enums.COLOR_GRAY;

    private static final int COST = 2;  // COST = 2
    private static final int UPGRADED_COST = 2; // UPGRADED_COST = 1

    private static final int DAMAGE = 5;    // DAMAGE = 5
    private static final int UPGRADE_PLUS_DMG = 1;  // UPGRADE_PLUS_DMG = 1

    // /STAT DECLARATION/


    public Lost_Souls() { // public Lost_Souls() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = damage = DAMAGE;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        CardGroup discardPileCards = AbstractDungeon.player.discardPile;

        for (AbstractCard card : discardPileCards.group) {
            boolean containsExhaust = card.rawDescription.toLowerCase().contains("exhaust");
            boolean containsDiscard = card.rawDescription.toLowerCase().contains("discard");

            if (containsExhaust || containsDiscard) {
                AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, discardPileCards));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn)));
            }
        }
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
