package CastAway.powers;

import CastAway.characters.TheCastAway;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import CastAway.DefaultMod;
import CastAway.util.TextureLoader;

import java.util.List;
import java.util.stream.Collectors;

import static CastAway.DefaultMod.makePowerPath;

public class CollectBlockPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = DefaultMod.makeID("CollectBlockPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    private int ignoreNext = TheCastAway.CARD_DRAW;

    public CollectBlockPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those txtures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        TheCastAway.logger.info("CollectBlockPower: atStartOfTurnPostDraw");
        ignoreNext = TheCastAway.CARD_DRAW;
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        TheCastAway.logger.info("CollectBlockPower: onCardDraw " + ignoreNext);

        if (ignoreNext > 0) {
            ignoreNext--;
            return;
        }

        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.amount));
    }

    @Override
    public void atEndOfRound() {
        TheCastAway.logger.info("CollectBlockPower: atEndOfRound");
        ignoreNext = TheCastAway.CARD_DRAW;
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new CollectBlockPower(owner, source, amount);
    }
}
