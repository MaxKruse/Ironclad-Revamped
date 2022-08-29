package CastAway.patches.relics;

import CastAway.characters.TheCastAway;
import CastAway.relics.OptimismRelic;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.GoldenIdol;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.shop.StorePotion;
import com.megacrit.cardcrawl.shop.StoreRelic;

import java.util.ArrayList;

@SpirePatch(clz = RewardItem.class, method = SpirePatch.CLASS)
public class RewardItemPatch {

    @SpirePatch(clz = RewardItem.class, method = "applyGoldBonus")
    public static class applyGoldBonus {
        public static void Postfix(RewardItem __instance) {
            TheCastAway.logger.info("Hit prefix for RewardItem.applyGoldBonus");
            TheCastAway.logger.info("Bonus Gold before: " + __instance.bonusGold);

            if (AbstractDungeon.player.hasRelic(OptimismRelic.ID)) {
                int bonusGold = __instance.bonusGold;

                if (AbstractDungeon.player.hasRelic(OptimismRelic.ID)) {
                    bonusGold += MathUtils.round((float)__instance.goldAmt * OptimismRelic.MULTIPLIER);
                }

                TheCastAway.logger.info("Adding" + bonusGold + " Bonus Gold");
                __instance.bonusGold = bonusGold;
                TheCastAway.logger.info("Bonus Gold after: " + __instance.bonusGold);

                if (__instance.bonusGold == 0) {
                    __instance.text = __instance.goldAmt + __instance.TEXT[1];
                } else {
                    __instance.text = __instance.goldAmt + __instance.TEXT[1] + " (" + __instance.bonusGold + ")";
                }
            }
        }
    }
}
