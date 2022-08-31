package CastAway.cards.Attacks.common;

import CastAway.cards.AbstractDynamicCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.defect.DiscardPileToHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CastAway.DefaultMod;
import CastAway.characters.TheCastAway;

import static CastAway.DefaultMod.makeCardPath;

// public class Throw extends AbstractDynamicCard
// @AutoAdd.Ignore // Remove this line when you make a template. Refer to https://github.com/daviscook477/BaseMod/wiki/AutoAdd if you want to know what it does.
public class Throw extends AbstractDynamicCard {

    /*
     * "Hey, I wanna make a bunch of cards now." - You, probably.
     * ok cool my dude no problem here's the most convenient way to do it:
     *
     * Copy all of the code here (Ctrl+A > Ctrl+C)
     * Ctrl+Shift+A and search up "file and code template"
     * Press the + button at the top and name your template whatever it is for - "AttackCard" or "PowerCard" or something up to you.
     * Read up on the instructions at the bottom. Basically replace anywhere you'd put your cards name with Throw
     * And then you can do custom ones like 4 and  if you want.
     * I'll leave some comments on things you might consider replacing with what.
     *
     * Of course, delete all the comments and add anything you want (For example, if you're making a skill card template you'll
     * likely want to replace that new DamageAction with a gain Block one, and add baseBlock instead, or maybe you want a
     * universal template where you delete everything unnecessary - up to you)
     *
     * You can create templates for anything you ever want to. Cards, relics, powers, orbs, etc. etc. etc.
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Throw.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    //public static final String ID = DefaultMod.makeID("DefaultCommonAttack"); // DELETE THIS ONE.
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("Throw.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = TheCastAway.Enums.COLOR_GRAY;

    private static final int COST = 2;  // COST = 1
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = 1

    private static final int DAMAGE = 8;    // DAMAGE = 4
    private static final int UPGRADE_PLUS_DMG = 3;  // UPGRADE_PLUS_DMG = 2

    private static final int DISCARD_AMOUNT = 2;
    private static final int UPGRADE_PLUS_DISCARD = -1;

    private static final int DISCARD_DAMAGE = 2;
    private static final int UPGRADE_PLUS_DISCARD_DAMAGE = 1;

    // /STAT DECLARATION/

    public Throw() { // public Throw() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = discard = DISCARD_AMOUNT;
        secondDamage = baseSecondDamage = DISCARD_DAMAGE;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));


        AbstractDungeon.actionManager.addToBottom(
                new DiscardAction(p, p, discard, false)
        );

        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, secondDamage, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));

        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, secondDamage, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(UPGRADED_COST);
            upgradeMagicNumber(UPGRADE_PLUS_DISCARD);
            upgradeSecondDamage(UPGRADE_PLUS_DISCARD_DAMAGE);
            initializeDescription();
        }
    }
}
