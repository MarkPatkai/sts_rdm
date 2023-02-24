package io.fatefc.redmage.base;


import static io.fatefc.redmage.RedMageInitializer.makeId;

public abstract class RedMageCard extends CustomCard {
    
    public RedMageCard(final String id, final String name, final String img, final int cost, CardType cardType, CardRarity cardRarity, CardTarget cardTarget) {
        super(makeId(id), name, img, cost, "NYI", cardType,  CardColor.RED, cardRarity, cardTarget);
    }
}
