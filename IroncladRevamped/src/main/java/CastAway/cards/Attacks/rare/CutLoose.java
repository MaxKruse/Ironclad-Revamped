package CastAway.cards.Attacks.rare;

import CastAway.cards.AbstractDynamicCard;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CastAway.DefaultMod;
import CastAway.characters.TheCastAway;
import com.megacrit.cardcrawl.relics.Brimstone;

import static CastAway.DefaultMod.makeCardPath;

// public class CutLoose extends AbstractDynamicCard
// @AutoAdd.Ignore // Remove this line when you make a template. Refer to https://github.com/daviscook477/BaseMod/wiki/AutoAdd if you want to know what it does.
public class CutLoose extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(CutLoose.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    //public static final String ID = DefaultMod.makeID("DefaultCommonAttack"); // DELETE THIS ONE.
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("CutLoose.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = TheCastAway.Enums.COLOR_GRAY;

    private static final int COST = 2;  // COST = 2
    private static final int UPGRADED_COST = 2; // UPGRADED_COST = 2

    private static final int DAMAGE = 3;    // DAMAGE = 4
    private static final int UPGRADE_PLUS_DMG = 2;  // UPGRADE_PLUS_DMG = 2

    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_PLUS_MAGIC = 1;

    // /STAT DECLARATION/


    public CutLoose() { // public CutLoose() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC_NUMBER;
        isMultiDamage = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int handSize = AbstractDungeon.player.hand.size();

        for (int i = 0; i < handSize; i++) {
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, damage, DamageInfo.DamageType.HP_LOSS, AbstractGameAction.AttackEffect.FIRE));
        }

        AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, magicNumber, false));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(UPGRADED_COST);
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
            initializeDescription();
        }
    }
}
