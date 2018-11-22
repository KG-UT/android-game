package fall2018.csc2017.gamecentre.database.asyncInterfaces;

import java.util.List;

import fall2018.csc2017.gamecentre.database.entity.GoBoardManager;


public interface AsyncGoGamesResult {
    void asyncFinished(List<GoBoardManager> goBoardManagers);
}
