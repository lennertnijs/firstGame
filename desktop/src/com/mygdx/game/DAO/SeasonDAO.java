package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Clock.Season;
import com.mygdx.game.Clock.SeasonName;
import com.mygdx.game.Clock.SeasonNode;
import com.mygdx.game.LinkedList.LinkedList;

import static com.mygdx.game.Constants.JSON_PATHNAME;

public class SeasonDAO {

    public SeasonDAO(){

    }

    public static LinkedList readSeasons(){
        LinkedList seasons = new LinkedList();
        JsonReader reader = new JsonReader();
        String path = JSON_PATHNAME + "seasons.json";
        JsonValue file = reader.parse(Gdx.files.internal(path));
        for(JsonValue seasonJSON: file){
            SeasonName seasonName = SeasonName.valueOf(seasonJSON.getString("season"));
            int seasonLength = seasonJSON.getInt("length");
            Season season = Season.builder().seasonName(seasonName).lengthInDays(seasonLength).build();
            SeasonNode seasonNode = SeasonNode.builder().setSeason(season).build();
            seasons.add(seasonNode);
        }
        return seasons;
    }
}
