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
import io.fatefc.redmage.character.RedMageCardColor;

public class Jolt extends CustomCard {

    public static final String ID = "Jolt";
    public static final String NAME = "Jolt";
    public static final String DESCRIPTION = "Deal !D! damage. Increase your Black Mana and White Mana by 1.";
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
        setBackgroundTexture("img/512/attack.png", "img/1024/attack.png");
        setOrbTexture("img/512/orb.png", "img/1024/orb.png");
        baseDamage = ATTACK_DMG;
        tags.add(CardTags.STRIKE);
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
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractPlayer, abstractPlayer, new BlackMana(abstractPlayer, 1)));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractPlayer, abstractPlayer, new WhiteMana(abstractPlayer, 1)));

    }

    @Override
    public AbstractCard makeCopy() {
        return new Jolt();
    }
}
