package CastAway.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import CastAway.DefaultMod;
import CastAway.util.TextureLoader;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.GoldenIdol;
import com.megacrit.cardcrawl.relics.MawBank;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.ShopRoom;

import java.util.List;

import static CastAway.DefaultMod.makeRelicOutlinePath;
import static CastAway.DefaultMod.makeRelicPath;

public class OptimismRelic extends CustomRelic {

    // ID, images, text.
    public static final String ID = DefaultMod.makeID("OptimismRelic");
    public static final float MULTIPLIER = 0.15f;

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    public OptimismRelic() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public void onGainGold() {

    }

    @Override
    public void onEnterRoom(AbstractRoom room) {
        if (room instanceof ShopRoom) {
            this.flash();
            this.pulse = true;
        } else {
            this.pulse = false;
        }
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0].replaceAll("!G!", String.format("%d", (long)(MULTIPLIER * 100.0f)));
    }

    @Override
    public AbstractRelic makeCopy() {
        return new OptimismRelic();
    }

}
