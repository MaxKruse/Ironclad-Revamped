package CastAway.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import CastAway.DefaultMod;
import CastAway.util.TextureLoader;
import com.megacrit.cardcrawl.relics.MawBank;

import static CastAway.DefaultMod.makeRelicOutlinePath;
import static CastAway.DefaultMod.makeRelicPath;

public class HallucinationRelic extends CustomRelic {

    // ID, images, text.
    public static final String ID = DefaultMod.makeID("HallucinationRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    private boolean done = false;

    public HallucinationRelic() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public void onVictory() {
        this.usedUp = false;
    }

    // Flash at the start of Battle.
    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if (drawnCard.rarity != AbstractCard.CardRarity.RARE) return;

        if (this.usedUp) return;
        flash();

        AbstractCard copy = drawnCard.makeStatEquivalentCopy();
        copy.cost = 0;

        AbstractDungeon.player.hand.addToHand(copy);

        this.usedUp = true;
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
