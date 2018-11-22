package fall2018.csc2017.gamecentre.database.asyncInterfaces;

import java.util.List;

import fall2018.csc2017.gamecentre.database.entity.TicTacToeBoardManager;

public interface AsyncTTTGamesResult {
    void asyncFinished(List<TicTacToeBoardManager> tttBoardManagers);
}
