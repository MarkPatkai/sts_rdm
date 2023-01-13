package io.fatefc.redmage.relic;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class RapierRelic extends CustomRelic {

    public static final String ID = "Rapier";



    public RapierRelic() {
        super(ID, new Texture("img/relic/rapier.png"), RelicTier.COMMON, LandingSound.MAGICAL);
    }

    @Override
    public AbstractRelic makeCopy() {
        return super.makeCopy();
    }

    @Override
    public String getUpdatedDescription() {
        return "Red mage's handy tool!"; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public void onEquip() {
        System.out.println("Nothing happens as of now!");
    }

}
