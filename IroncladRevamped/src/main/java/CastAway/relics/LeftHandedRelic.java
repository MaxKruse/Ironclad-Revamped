package CastAway.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import CastAway.DefaultMod;
import CastAway.util.TextureLoader;

import static CastAway.DefaultMod.makeRelicOutlinePath;
import static CastAway.DefaultMod.makeRelicPath;

public class LeftHandedRelic extends CustomRelic {

    // ID, images, text.
    public static final String ID = DefaultMod.makeID("LeftHandedRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    public LeftHandedRelic() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    // Flash at the start of Battle.
    @Override
    public void onManualDiscard() {
        flash();
        this.addToBot(new DrawCardAction(1));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
