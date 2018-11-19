package fall2018.csc2017.gamecentre.Database.AsyncInterfaces;

import fall2018.csc2017.gamecentre.Database.Entity.User;

public interface AsyncUserResult {
    void asyncFinished(User user);
}
