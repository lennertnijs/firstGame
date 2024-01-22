package com.mygdx.game.Clock;

// TESTED

public enum DayName {

    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    /**
     * @return The next day's name. (This works circularly)
     */
    @SuppressWarnings("Duplicates")
    public DayName next(){
        DayName[] dayNames = DayName.values();
        int amountOfDays = dayNames.length;
        for(int i = 0; i < amountOfDays; i++){
            boolean foundDay = this.equals(dayNames[i]);
            if(foundDay){
                int nextIndex = (i+1)%amountOfDays;
                return dayNames[nextIndex];
            }
        }
        throw new IllegalArgumentException("Cannot find the correct day");
    }
}
