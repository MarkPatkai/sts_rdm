package io.fatefc.redmage.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import io.fatefc.redmage.buff.BlackMana;
import io.fatefc.redmage.buff.WhiteMana;

public class Redoublement extends CustomCard {
    public static final String ID = "Redoublement";
    public static final String NAME = "Redoublement";
    public static final String DESCRIPTION = "Deal !D! damage. NL Exhaust.";
    public static final String IMG_PATH = "img/card/redoublement.png";
    private static final int COST = 0;
    private static final int ATTACK_DMG = 5;
    private static final int UPGRADE_PLUS_DMG = 7;

    public Redoublement() {
        super(ID, NAME,
                IMG_PATH,
                COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
                AbstractCard.CardColor.RED,
                AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
        setBackgroundTexture("img/512/attack.png", "img/1024/attack.png");
        setOrbTexture("img/512/orb.png", "img/1024/orb.png");
        baseDamage = ATTACK_DMG;
        exhaust = true;

    }

    @Override
    public void upgrade() {
        if(!upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(abstractMonster,
                new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));

    }

    @Override
    public AbstractCard makeCopy() {
        return new Redoublement();
    }
}
