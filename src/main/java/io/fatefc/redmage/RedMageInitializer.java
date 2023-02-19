package io.fatefc.redmage;

import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import io.fatefc.redmage.base.CharacterInitializer;
import io.fatefc.redmage.card.Jolt;
import io.fatefc.redmage.card.Veraero;
import io.fatefc.redmage.card.Verthunder;
import io.fatefc.redmage.character.RedMage;
import io.fatefc.redmage.character.RedMageClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpireInitializer
public class RedMageInitializer implements CharacterInitializer, EditCardsSubscriber, EditRelicsSubscriber {

    public static final Logger logger = LogManager.getLogger(RedMageInitializer.class);
    public final static String BUTTON = "img/menu/rdm_icon.png";
    public final static String PORTRAIT = "img/menu/rdm_portrait.jpg";

    public RedMageInitializer() {
        BaseMod.subscribe(this);

    }

    public static void initialize() {
        @SuppressWarnings("unused")
        RedMageInitializer redMageInitializer = new RedMageInitializer();
    }


    @Override
    public void receiveEditCharacters() {
        logger.info("Adding new character ...");
        BaseMod.addCharacter(new RedMage(), BUTTON, PORTRAIT, RedMageClass.RED_MAGE_MOD);
        logger.info("Added Red Mage!");
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addCard(new Jolt());
        BaseMod.addCard(new Verthunder());
        BaseMod.addCard(new Veraero());
    }

    @Override
    public void receiveEditRelics() {
    }
}
