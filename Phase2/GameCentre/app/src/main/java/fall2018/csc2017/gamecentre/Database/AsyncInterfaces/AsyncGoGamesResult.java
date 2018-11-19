package fall2018.csc2017.gamecentre.Database.AsyncInterfaces;

import java.util.List;

import fall2018.csc2017.gamecentre.Database.Entity.GoBoardManager;


public interface AsyncGoGamesResult {
    void asyncFinished(List<GoBoardManager> goBoardManagers);
}
