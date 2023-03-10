package io.fatefc.redmage;

import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.RelicStrings;
import io.fatefc.redmage.base.CharacterInitializer;
import io.fatefc.redmage.card.*;
import io.fatefc.redmage.character.RedMage;
import io.fatefc.redmage.character.RedMageClass;
import io.fatefc.redmage.relic.RapierRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpireInitializer
public class RedMageInitializer implements CharacterInitializer, EditCardsSubscriber, EditRelicsSubscriber, EditStringsSubscriber {

    public static final Logger logger = LogManager.getLogger(RedMageInitializer.class);
    public final static String BUTTON = "img/menu/rdm_icon.png";
    public final static String PORTRAIT = "img/menu/rdm_portrait.jpg";

    public String makeId(String id) {
        return String.format("rdm:%s",id);
    }

    public RedMageInitializer() {
        BaseMod.subscribe(this);

    }

    public static void initialize() {
        @SuppressWarnings("unused")
        RedMageInitializer redMageInitializer = new RedMageInitializer();
    }


    public void receiveEditCharacters() {
        logger.info("Adding new character ...");
        BaseMod.addCharacter(new RedMage(), BUTTON, PORTRAIT, RedMageClass.RED_MAGE_MOD);
        logger.info("Added Red Mage!");
    }

    public void receiveEditCards() {
        BaseMod.addCard(new Jolt());
        BaseMod.addCard(new Verthunder());
        BaseMod.addCard(new Veraero());
        BaseMod.addCard(new Vercure());
        BaseMod.addCard(new Riposte());
        BaseMod.addCard(new Zwerchau());
        BaseMod.addCard(new Redoublement());
        BaseMod.addCard(new EnchantedRiposte());
    }

   public void receiveEditRelics() {
        RelicLibrary.add(new RapierRelic());
        BaseMod.addRelic(new RapierRelic(), RelicType.SHARED);
    }

    public void loadStrings(String langKey) {
        if(!Gdx.files.internal("localization/" + langKey + "/").exists()) return;
        String filePath = "localization/" + langKey + "/Relicstrings.json";
        BaseMod.loadCustomStringsFile(RelicStrings.class, filePath);
    }

    @Override
    public void receiveEditStrings() {
        this.loadStrings("eng");
    }
}
