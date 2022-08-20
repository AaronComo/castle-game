package src.castle;

public class HandlerBye extends Handler {
    
    HandlerBye(Game game) {
        super(game);
    }

    @Override
    public boolean isBye() {
        return true;
    }
}
