package CastAway.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import CastAway.DefaultMod;
import CastAway.util.TextureLoader;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CastAway.DefaultMod.makeRelicOutlinePath;
import static CastAway.DefaultMod.makeRelicPath;

public class IronFistRelic extends CustomRelic {

    // ID, images, text.
    public static final String ID = DefaultMod.makeID("IronFistRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    public IronFistRelic() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.MAGICAL);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m)  {
        if (c.type != AbstractCard.CardType.ATTACK) return;
        flash();
        this.addToBot(new DrawCardAction(1));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
