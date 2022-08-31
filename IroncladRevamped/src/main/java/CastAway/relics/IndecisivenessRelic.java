package CastAway.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.LoseBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import CastAway.DefaultMod;
import CastAway.util.TextureLoader;
import com.megacrit.cardcrawl.relics.CursedKey;
import com.megacrit.cardcrawl.relics.Lantern;

import static CastAway.DefaultMod.makeRelicOutlinePath;
import static CastAway.DefaultMod.makeRelicPath;

public class IndecisivenessRelic extends CustomRelic {

    // ID, images, text.
    public static final String ID = DefaultMod.makeID("IndecisivenessRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    private static final int damage = 3;

    public IndecisivenessRelic() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);
        energyBased = true;
    }

    // Flash at the start of Battle.
    @Override
    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void onExhaust(AbstractCard card) {
        if (this.usedUp) return;

        flash();
        this.addToBot(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, damage));
        this.usedUp = true;
    }

    @Override
    public void onVictory() {
        this.usedUp = false;
    }


    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0].replace("!D!", String.valueOf(damage));
    }

}
