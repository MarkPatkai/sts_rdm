package io.fatefc.redmage.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import io.fatefc.redmage.character.RedMageCardColor;

public class Vercure extends CustomCard {

    public static final String ID = "Vercure";
    public static final String NAME = "Vercure";
    public static final String DESCRIPTION = "Heal !M!. NL Exhaust.";
    public static final String IMG_PATH = "img/card/vercure.png";
    public static final String IMG_PATH_SMALL = "img/card/vercure.png";
    private static final int COST = 2;
    private static final int BASE_HEAL = 5;
    private static final int UPGRADE_HEAL = 2; // this adds up, not sets it

    public Vercure() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, CardColor.RED, CardRarity.UNCOMMON, CardTarget.SELF);
        setBackgroundTexture("img/512/skill.png", "img/1024/skill.png");
        setOrbTexture("img/512/orb.png", "img/1024/orb.png");
        exhaust = true;
        this.magicNumber = this.baseMagicNumber = BASE_HEAL;
        tags.add(CardTags.HEALING);
    }

    @Override
    public void upgrade() {
        if(!upgraded) {
            this.upgradeName();
            upgradeMagicNumber(UPGRADE_HEAL);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new HealAction(abstractPlayer, abstractPlayer, magicNumber));

    }

    @Override
    public AbstractCard makeCopy() {
        return new Vercure();
    }
}
