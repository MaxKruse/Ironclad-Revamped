package CastAway.cards.Skills.uncommon;

import CastAway.actions.ExhaustFromDeckAction;
import CastAway.actions.RandomExhaustFromDeckAction;
import CastAway.cards.AbstractDynamicCard;
import CastAway.powers.LostPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CastAway.DefaultMod;
import CastAway.characters.TheCastAway;

import static CastAway.DefaultMod.makeCardPath;

public class LosingYourself extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(LosingYourself.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;


    // STAT DECLARATION 	

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheCastAway.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int MAGIC_NUMBER = 3;
    private static final int UPGRADE_PLUS_MAGIC = 2;

    private static final int DECK_EXHAUST = 5;
    private static final int UPGRADE_PLUS_DECK_EXHAUST = -2;


    // /STAT DECLARATION/


    public LosingYourself() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC_NUMBER;
        deckExhaust = baseDeckExhaust = DECK_EXHAUST;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!upgraded) {
            AbstractDungeon.actionManager.addToBottom(new RandomExhaustFromDeckAction(p, deckExhaust));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ExhaustFromDeckAction(p, deckExhaust));
        }

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LostPower(p, p, MAGIC_NUMBER)));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
            upgradeDeckExhaust(UPGRADE_PLUS_DECK_EXHAUST);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
