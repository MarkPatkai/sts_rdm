package io.fatefc.redmage.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Jolt extends CustomCard {

    public static final String ID = "Jolt";
    public static final String NAME = "Jolt";
    public static final String DESCRIPTION = "Deal !D! damage. Increase your Black and White mana by 1.";
    public static final String IMG_PATH = "img/card/jolt.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 5;
    private static final int UPGRADE_PLUS_DMG = 7;

    public Jolt() {
        super(ID, NAME, IMG_PATH, COST,
                DESCRIPTION, CardType.ATTACK,
                CardColor.RED, CardRarity.COMMON, CardTarget.ENEMY);

        this.damage = this.baseDamage = ATTACK_DMG;
        this.setBackgroundTexture(IMG_PATH, IMG_PATH);

        this.setBannerTexture(IMG_PATH, IMG_PATH);

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
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(abstractMonster,
                new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }
}
