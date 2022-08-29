package CastAway.patches.relics;

import CastAway.characters.TheCastAway;
import CastAway.relics.OptimismRelic;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.GoldenIdol;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.shop.StorePotion;
import com.megacrit.cardcrawl.shop.StoreRelic;

import java.util.ArrayList;

@SpirePatch(clz = ShopScreen.class, method = SpirePatch.CLASS)
public class ShopPricePatch {

    public static SpireField<Integer> retval = new SpireField<>(() -> 1);

    @SpirePatch(clz = ShopScreen.class, method = "init")
    public static class init {
        public static void Postfix(ShopScreen __instance) {

            TheCastAway.logger.info("Hit postfix for ShopScreen.init");

            if (AbstractDungeon.player.hasRelic(OptimismRelic.ID)) {
                __instance.applyDiscount(1.0f - OptimismRelic.MULTIPLIER, true);
            }
        }
    }
}
