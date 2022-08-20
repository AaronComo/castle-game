package src.castle;

public class HandlerGo extends Handler {
    
    HandlerGo(Game game) {
        super(game);
    }

    @Override
    public void doCmd(String cmd) {
        game.goRoom(cmd);
    }
}
