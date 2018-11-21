package fall2018.csc2017.gamecentre.database.asyncInterfaces;

import fall2018.csc2017.gamecentre.database.entity.User;

public interface AsyncUserResult {
    void asyncFinished(User user);
}
