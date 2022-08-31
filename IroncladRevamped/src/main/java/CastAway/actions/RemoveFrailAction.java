package CastAway.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import CastAway.powers.CommonPower;

public class RemoveFrailAction extends AbstractGameAction {
    final private AbstractPlayer p;

    public RemoveFrailAction(final AbstractPlayer p) {
        this.p = p;
    }

    @Override
    public void update() {
        this.addToBot(new RemoveSpecificPowerAction(p, p, FrailPower.POWER_ID));
        isDone = true;
    }
}
