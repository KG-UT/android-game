package fall2018.csc2017.gamecentre.Database.AsyncInterfaces;

import java.util.List;

import fall2018.csc2017.gamecentre.Database.Entity.SlidingTileBoardManager;

public interface AsyncSTGamesResult {
    void asyncFinished(List<SlidingTileBoardManager> stBoardManagers);
}
