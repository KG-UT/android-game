package fall2018.csc2017.gamecentre.firebase;

import fall2018.csc2017.gamecentre.abstractClasses.BoardManager;

public interface DatabaseTools {

    public String serializeBoardManager();

    public BoardManager deSerializeBoardManager(String serializedBoardManager);

    public BoardManager retrieveDeSerializedBoardManager(String gameKeyValue);

    public void saveToDatabase(BoardManager boardManager);


}
