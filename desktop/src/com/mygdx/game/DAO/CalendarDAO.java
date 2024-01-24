package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Clock.Calendar;
import com.mygdx.game.Clock.Season;

import static com.mygdx.game.Constants.JSON_PATHNAME;

public class CalendarDAO {

    public CalendarDAO(){
    }

    public static Calendar readCalendar(){
        JsonReader reader = new JsonReader();
        String path = JSON_PATHNAME + "seasons.json";
        JsonValue file = reader.parse(Gdx.files.internal(path));
        int seasonIndex = 0;
        Calendar.Builder calenderBuilder = new Calendar.Builder();
        for(JsonValue seasonJSON: file){
            Season season = Season.getSeason(seasonIndex);
            int length = seasonJSON.getInt("length");
            calenderBuilder.put(season, length);
            seasonIndex += 1;
        }
        return calenderBuilder.build();
    }
}
