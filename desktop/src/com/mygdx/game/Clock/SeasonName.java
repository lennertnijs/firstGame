package com.mygdx.game.Clock;

// TESTED
public enum SeasonName {

    LIGHT,
    DARK;

    /**
     * @return The next season's name. (Works circularly)
     */
    @SuppressWarnings("Duplicates")
    public SeasonName next(){
        SeasonName[] seasonNames = SeasonName.values();
        int amountOfSeasons = seasonNames.length;
        for(int i = 0; i < amountOfSeasons; i++){
            boolean foundSeason = this.equals(seasonNames[i]);
            if(foundSeason){
                int nextSeasonIndex = (i+1)%amountOfSeasons;
                return seasonNames[nextSeasonIndex];
            }
        }
        throw new IllegalArgumentException("Cannot find the next season.");
    }
}
