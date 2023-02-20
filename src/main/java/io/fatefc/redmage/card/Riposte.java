package io.fatefc.redmage.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class Riposte extends CustomCard {

    public static final String ID = "Riposte";

    public static final String NAME = "Riposte";
    public static final String DESCRIPTION = "Deal !D! damage. Adds a Zwerchau to your hand. Upgrades to Enchanted Riposte when you have 10 Black and White Mana.";
    public static final String IMG_PATH = "img/card/riposte.png";
    private static final int COST = 3;
    private static final int ATTACK_DMG = 5;
    private static final int UPGRADE_PLUS_DMG = 7;

    public Riposte() {
        super(ID, NAME,
                IMG_PATH,
                COST, DESCRIPTION, CardType.ATTACK,
                CardColor.RED,
                CardRarity.UNCOMMON, CardTarget.ENEMY);
        setBackgroundTexture("img/512/attack.png", "img/1024/attack.png");
        setOrbTexture("img/512/orb.png", "img/1024/orb.png");
        baseDamage = ATTACK_DMG;

    }

    @Override
    public void upgrade() {
        if(!upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }

    private boolean hasGauge(AbstractPlayer player) {
        if (player.getPower("BlackMana") == null ||
                player.getPower("WhiteMana") == null) {
            return false;
        }
        int bm = AbstractDungeon.player.getPower("BlackMana").amount;
        int wm = AbstractDungeon.player.getPower("WhiteMana").amount;

        return bm == wm && bm >= 10;
    }

    @Override
    public boolean canUse(AbstractPlayer player, AbstractMonster monster) {
        return !hasGauge(player);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(abstractMonster,
                new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        this.addToBot(new MakeTempCardInHandAction(new Zwerchau()));

    }

    @Override
    public void triggerWhenDrawn() {
        if (hasGauge(AbstractDungeon.player))
            this.addToBot(new DiscardSpecificCardAction(this));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Riposte();
    }
}
