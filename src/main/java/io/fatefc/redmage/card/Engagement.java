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

public class Engagement extends CustomCard {
    public static final String ID = "Jolt";
    public static final String NAME = "Jolt";
    public static final String DESCRIPTION = "Deal !D! damage. Increase your Black Mana and White Mana by 1.";
    public static final String IMG_PATH = "img/card/jolt.png";
    private static final int COST = 1;
    private static final int BLOCK_BASE = 5;
    private static final int BLOCK_UPGRADE = 2;

    public Engagement() {
        super(ID, NAME,
                IMG_PATH,
                COST, DESCRIPTION, CardType.SKILL,
                CardColor.RED,
                CardRarity.UNCOMMON, CardTarget.SELF);
        setBackgroundTexture("img/512/attack.png", "img/1024/attack.png");
        setOrbTexture("img/512/orb.png", "img/1024/orb.png");
        this.baseBlock = BLOCK_BASE;
        tags.add(CardTags.STARTER_DEFEND);
    }

    @Override
    public void upgrade() {
        if(!upgraded) {
            this.upgradeName();
            this.upgradeBlock(BLOCK_UPGRADE);
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
