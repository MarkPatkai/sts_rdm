package io.fatefc.redmage.relic;

import basemod.abstracts.CustomRelic;
import basemod.patches.com.megacrit.cardcrawl.helpers.RelicLibrary.EditRelicsPatch;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.CloakAndDagger;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.FrozenEgg2;
import com.megacrit.cardcrawl.relics.RingOfTheSerpent;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import io.fatefc.redmage.RedMageInitializer;
import io.fatefc.redmage.card.EnchantedRiposte;
import io.fatefc.redmage.card.Riposte;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class RapierRelic extends CustomRelic {

    public static final String ID = "Rapier";

    public static final Logger logger = LogManager.getLogger(RapierRelic.class);

    public RapierRelic() {
        super(ID, new Texture("img/relic/TodoItem.png"), new Texture("img/relic/TodoItemOutline.png"),
                RelicTier.BOSS, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DESCRIPTIONS[1];
    }


    @Override
    public AbstractRelic makeCopy() {
        return new RapierRelic();
    }

    public boolean hasGauge() {
        if (AbstractDungeon.player.getPower("BlackMana") == null ||
            AbstractDungeon.player.getPower("WhiteMana") == null) {
            return false;
        }
        int bm = AbstractDungeon.player.getPower("BlackMana").amount;
        int wm = AbstractDungeon.player.getPower("WhiteMana").amount;
        logger.info("Player has: {} Black Mana and {} White Mana", bm, wm);
        logger.info("Has gauge: {}", bm == wm && bm >= 10);
        return bm == wm && bm >= 10;
    }

    public void removeRiposte() {

    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if ( card.cardID.equals("Riposte")) {
            if (hasGauge()) {
                card.triggerOnExhaust();
                this.addToBot(new MakeTempCardInHandAction(new EnchantedRiposte()));
            }
        }
    }

    @Override
    public void atTurnStartPostDraw() {
        if(hasGauge()) {
            this.addToBot(new MakeTempCardInHandAction(new EnchantedRiposte()));
        }

    }




}
