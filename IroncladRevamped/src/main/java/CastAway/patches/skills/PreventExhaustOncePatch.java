package CastAway.patches.skills;

import CastAway.characters.TheCastAway;
import CastAway.powers.PreventExhaustPower;
import CastAway.relics.OptimismRelic;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.GoldenIdol;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.shop.StorePotion;
import com.megacrit.cardcrawl.shop.StoreRelic;

import java.util.ArrayList;

@SpirePatch(clz = UseCardAction.class, method = SpirePatch.CLASS)
public class PreventExhaustOncePatch {

    @SpirePatch(clz = UseCardAction.class, method = "update")
    public static class update {

        public static void Prefix(UseCardAction __instance) {
            TheCastAway.logger.info("Hit prefix for UseCardAction.update");

            if (!AbstractDungeon.player.hasPower(PreventExhaustPower.POWER_ID)) return;

            if (__instance.exhaustCard) {
                __instance.exhaustCard = false;
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(AbstractDungeon.player,AbstractDungeon.player, PreventExhaustPower.POWER_ID, 1));
            }

            __instance.isDone = true;
        }
    }
}
