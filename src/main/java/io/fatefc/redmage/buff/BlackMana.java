package io.fatefc.redmage.buff;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.DemonForm;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import io.fatefc.redmage.RedMageInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BlackMana extends AbstractPower {

    public static final String POWER_ID = "BlackMana";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private boolean justEvoked = true;

    public static final Logger logger = LogManager.getLogger(RedMageInitializer.class);



    public BlackMana(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("strength");
        this.isTurnBased = false;
        this.type = PowerType.BUFF;
    }

    static {
        PowerStrings dc = new PowerStrings();
        dc.NAME = "BlackMana";
        dc.DESCRIPTIONS = new String[] {"Black Mana Gauge: #b"};
        powerStrings = dc;
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount;
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        logger.info("You have {} black mana!", this.amount);
    }


}
