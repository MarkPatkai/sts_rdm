package io.fatefc.redmage.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAndDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import io.fatefc.redmage.buff.BlackMana;
import io.fatefc.redmage.buff.WhiteMana;

public class EnchantedRiposte extends CustomCard {
    public static final String ID = "EnchantedRiposte";
    public static final String NAME = "Enchanted Riposte";
    public static final String DESCRIPTION = "Retain. NL Deal !D! damage. NYI Adds a Enchanted Zwerchau to your hand.";
    public static final String IMG_PATH = "img/card/enchantedriposte.png";
    private static final int COST = 0;
    private static final int ATTACK_DMG = 20;
    private static final int UPGRADE_PLUS_DMG = 0;

    public EnchantedRiposte() {
        super(ID, NAME,
                IMG_PATH,
                COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
                AbstractCard.CardColor.RED,
                AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
        setBackgroundTexture("img/512/attack.png", "img/1024/attack.png");
        setOrbTexture("img/512/orb.png", "img/1024/orb.png");
        baseDamage = ATTACK_DMG;
        exhaust = true;
        retain = true;

    }

    @Override
    public void upgrade() {
        if(!upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }

    }

    @Override
    public boolean canUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (abstractPlayer.getPower("BlackMana") == null ||
                abstractPlayer.getPower("WhiteMana") == null) {
            return false;
        }
        int bm = AbstractDungeon.player.getPower("BlackMana").amount;
        int wm = AbstractDungeon.player.getPower("WhiteMana").amount;
        return bm == wm && bm >= 10;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(abstractMonster,
                new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        abstractPlayer.getPower("BlackMana").reducePower(10);
        abstractPlayer.getPower("WhiteMana").reducePower(10);
    }

    @Override
    public AbstractCard makeCopy() {
        return new EnchantedRiposte();
    }
}
