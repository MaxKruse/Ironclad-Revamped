package CastAway.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import CastAway.DefaultMod;
import CastAway.util.TextureLoader;
import com.megacrit.cardcrawl.random.Random;

import static CastAway.DefaultMod.makeRelicOutlinePath;
import static CastAway.DefaultMod.makeRelicPath;

public class NothingIsLostRelic extends CustomRelic {

    // ID, images, text.
    public static final String ID = DefaultMod.makeID("NothingIsLostRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    public NothingIsLostRelic() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    // Flash at the start of Battle.
    @Override
    public void onExhaust(AbstractCard c) {

        Random rng = AbstractDungeon.cardRandomRng;

        boolean shouldAddCopy = rng.randomBoolean(0.5f);

        if (!shouldAddCopy) {
            return;
        }

        flash();
        AbstractCard copy = c.makeStatEquivalentCopy();
        copy.cost = 0;

        AbstractDungeon.player.discardPile.addToBottom(copy);
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
