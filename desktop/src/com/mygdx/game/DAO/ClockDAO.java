package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Clock.Calendar;
import com.mygdx.game.Clock.Clock;
import com.mygdx.game.Clock.Day;
import com.mygdx.game.Clock.Season;

import java.util.HashMap;

import static com.mygdx.game.Constants.MINUTES_PER_HOUR;
import static java.lang.Integer.parseInt;

public class ClockDAO {

    public ClockDAO(){
    }

    public Clock readClock(){
        JsonReader reader = new JsonReader();
        JsonValue file = reader.parse(Gdx.files.internal("resources/clock.json"));

        JsonValue calendarJSON = file.get("calendar");
        Calendar calendar = readCalendar(calendarJSON);

        Season season = Season.valueOf(file.getString("season"));

        Day day = Day.valueOf(file.getString("day"));

        int time = timeStringToMinutes(file.getString("time"));

        int dayInMonth = file.getInt("dayInMonth");

        return Clock.builder()
                .calendar(calendar)
                .season(season)
                .day(day)
                .timeInMinutes(time)
                .dayOfTheSeason(dayInMonth)
                .build();
    }

    private Calendar readCalendar(JsonValue calendarJSON){
        HashMap<Season, Integer> seasons = new HashMap<>();
        for(JsonValue seasonJSON: calendarJSON){
            Season season = Season.valueOf(seasonJSON.getString("season"));
            int seasonLength = seasonJSON.getInt("length");
            seasons.put(season, seasonLength);
        }
        return Calendar.builder().seasons(seasons).build();
    }

    /**
     * Converts a time string HH:MM to an integer (the time in minutes, to be precise)
     */
    private int timeStringToMinutes(String time){
        String[] array = time.split(":");
        int hours = parseInt(array[0]);
        int minutes = parseInt(array[1]);
        return hours*MINUTES_PER_HOUR + minutes;
    }
}
