package io.fatefc.redmage.buff;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ReboundPower;

public class Dualcast extends AbstractPower {

    public static final String POWER_ID = "Dualcast";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private boolean justEvoked = true;



    public Dualcast(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "Dualcast";
        this.owner = owner;
        this.amount = 1;
        this.updateDescription();
        this.loadRegion("rebound");
        this.isTurnBased = false;
        this.type = PowerType.BUFF;
    }

    static {
        PowerStrings dc = new PowerStrings();
        dc.NAME = "Dualcast";
        dc.DESCRIPTIONS = new String[] {"You have Dualcast!"};
        powerStrings = dc;
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount;
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (this.justEvoked) {
            this.justEvoked = false;
        } else {
            if (card.type != AbstractCard.CardType.POWER) {
                this.flash();
            }

            this.addToBot(new ReducePowerAction(this.owner, this.owner, "Dualcast", 1));
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Rebound"));
        }

    }

}
