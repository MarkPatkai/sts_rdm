package io.fatefc.redmage.character;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Claw;
import com.megacrit.cardcrawl.cards.blue.EchoForm;
import com.megacrit.cardcrawl.cards.blue.Leap;
import com.megacrit.cardcrawl.cards.colorless.Apotheosis;
import com.megacrit.cardcrawl.cards.green.CloakAndDagger;
import com.megacrit.cardcrawl.cards.red.Anger;
import com.megacrit.cardcrawl.cards.red.Clash;
import com.megacrit.cardcrawl.cards.red.Rage;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.Prefs;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.screens.stats.CharStat;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbBlue;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;
import io.fatefc.redmage.card.Jolt;

import java.util.ArrayList;


public class RedMage extends AbstractPlayer {

    private static final int HP = 60;
    private static final int MAX_HP = 60;
    private static final int CARD_DRAW = 5;
    private static final int GOLD = 90;
    private static final int MAX_ORBS = 0;
    private static final String NAME = "Red Mage";
    private static final String HEART_TEXT = "So shall the Red Mage meet the heart of the spire... first time, or the last?";

    private static final String VAMPIRE_TEXT = "The red mage have survived Eureka... what's the difference between a few vampires and Demi Ozma?";
    public static final int ENERGY_PER_TURN = 3;
    public static final String MY_CHARACTER_SHOULDER_2 = "img/rdm_char.jpg"; // campfire pose
    public static final String MY_CHARACTER_SHOULDER_1 = "img/rdm_char.jpg"; // another campfire pose
    public static final String MY_CHARACTER_CORPSE = "img/character/corpse.png"; // dead corpse
    public static final String MY_CHARACTER_SKELETON_ATLAS = "img/character/skeleton.atlas"; // spine animation atlas
    public static final String MY_CHARACTER_SKELETON_JSON = "img/character/skeleton.json"; // spine animation json

    private EnergyOrbInterface energyOrb = new EnergyOrbBlue();


    public RedMage() {
        super(NAME, RedMageClass.RED_MAGE_MOD);


        this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
        this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values

        initializeClass(null, MY_CHARACTER_SHOULDER_2, // required call to load textures and setup energy/loadout
                MY_CHARACTER_SHOULDER_1,
                MY_CHARACTER_CORPSE,
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

        loadAnimation(MY_CHARACTER_SKELETON_ATLAS, MY_CHARACTER_SKELETON_JSON, 1.0F); // if you're using modified versions of base game animations or made animations in spine make sure to include this bit and the following lines
        AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);

        e.setTime(e.getEndTime() * MathUtils.random());
    }

    @Override
    public String getPortraitImageName() {
        return "rdm_portrait.jpg";
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add("Jolt");
        cards.add("Jolt");
        cards.add("Jolt");
        cards.add("Jolt");
        cards.add("Vercure");
        cards.add("Verthunder");
        cards.add("Veraero");
        cards.add("Riposte");
        return cards;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> relics = new ArrayList<>();
        relics.add("PrismaticShard");         // TODO: Default relic these currently are for development as there are no Red Mage cards; Prismatic is mandatory
        relics.add("Rapier");
        return relics;
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(
                "Red Mage", "A visitor from Eorzea has come to share the importance of Black and White mana balance",
                HP, MAX_HP, MAX_ORBS, GOLD, CARD_DRAW, this, getStartingRelics(), getStartingDeck(), false

        );
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return playerClass.name();
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return RedMageCardColor.RED_MAGE_MOD;
    }

    @Override
    public Color getCardRenderColor() {
        return Color.RED;
    }

    @Override
    public String getAchievementKey() {
        return "MODDED";
    }

    @Override
    public ArrayList<AbstractCard> getCardPool(ArrayList<AbstractCard> arrayList) {
      ArrayList<AbstractCard> cardPool = new ArrayList<>();
      return cardPool;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Jolt();
    }

    @Override
    public Color getCardTrailColor() {
        return Color.RED;
    }

    @Override
    public String getLeaderboardCharacterName() {
        return "Red Mage";
    }

    @Override
    public Texture getEnergyImage() {
        return ImageMaster.PURPLE_ORB_FLASH_VFX;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 0;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontPurple;
    }

    @Override
    public void renderOrb(SpriteBatch spriteBatch, boolean b, float v, float v1) {
        this.energyOrb.renderOrb(spriteBatch, b, v, v1);
    }

    @Override
    public void updateOrb(int i) {

    }

    @Override
    public Prefs getPrefs() {
        return new Prefs();
    }

    @Override
    public void loadPrefs() {

    }

    @Override
    public CharStat getCharStat() {
        return new CharStat(this);
    }

    @Override
    public int getUnlockedCardCount() {
        return 0;
    }

    @Override
    public int getSeenCardCount() {
        return 0;
    }

    @Override
    public int getCardCount() {
        return 0;
    }

    @Override
    public boolean saveFileExists() {
        return false;
    }

    @Override
    public String getWinStreakKey() {
        return "RedMage";
    }

    @Override
    public String getLeaderboardWinStreakKey() {
        return "RedMage";
    }

    @Override
    public void renderStatScreen(SpriteBatch spriteBatch, float v, float v1) {

    }

    @Override
    public void doCharSelectScreenSelectEffect() {

    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return null;
    }

    @Override
    public Texture getCustomModeCharacterButtonImage() {
        return new Texture("img/menu/rdm_icon.png");
    }

    @Override
    public CharacterStrings getCharacterString() {
        return new CharacterStrings();
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAME;
    }

    @Override
    public void refreshCharStat() {

    }

    @Override
    public AbstractPlayer newInstance() {
        return new RedMage();
    }

    @Override
    public TextureAtlas.AtlasRegion getOrb() {
        return AbstractCard.orb_blue;
    }

    @Override
    public String getSpireHeartText() {
        return HEART_TEXT;
    }

    @Override
    public Color getSlashAttackColor() {
        return Color.RED;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[0];
    }

    @Override
    public String getVampireText() {
        return VAMPIRE_TEXT;
    }
}
