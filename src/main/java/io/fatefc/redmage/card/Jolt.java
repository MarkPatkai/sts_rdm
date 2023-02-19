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
import io.fatefc.redmage.character.RedMageCardColor;

public class Jolt extends CustomCard {

    public static final String ID = "Jolt";
    public static final String NAME = "Jolt";
    public static final String DESCRIPTION = "Deal !D! damage. Increase your Black and White mana by 1.";
    public static final String IMG_PATH = "img/card/jolt.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 5;
    private static final int UPGRADE_PLUS_DMG = 7;

    public Jolt() {
        super(ID, NAME,
                IMG_PATH,
                COST, DESCRIPTION, CardType.ATTACK,
                CardColor.RED,
                CardRarity.UNCOMMON, CardTarget.ENEMY);

        baseDamage = ATTACK_DMG;

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
        return new Jolt();
    }
}
