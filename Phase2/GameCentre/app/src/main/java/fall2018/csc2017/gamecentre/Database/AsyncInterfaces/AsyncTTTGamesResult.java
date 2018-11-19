package fall2018.csc2017.gamecentre.Database.AsyncInterfaces;

import java.util.List;

import fall2018.csc2017.gamecentre.Database.Entity.TicTacToeBoardManager;

public interface AsyncTTTGamesResult {
    void asyncFinished(List<TicTacToeBoardManager> tttBoardManagers);
}
