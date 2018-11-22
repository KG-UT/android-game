package fall2018.csc2017.gamecentre.database.asyncInterfaces;

import java.util.List;

import fall2018.csc2017.gamecentre.database.entity.SlidingTileBoardManager;

public interface AsyncSTGamesResult {
    void asyncFinished(List<SlidingTileBoardManager> stBoardManagers);
}
