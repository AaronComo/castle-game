package src.castle;

import java.util.Set;

public class HandlerHelp extends Handler {
    private Set<String> cmdList;

    HandlerHelp(Game game, Set<String> cmdList) {
        super(game);
        this.cmdList = cmdList;
    }

    @Override
    public void doCmd(String cmd) {
        System.out.println("迷路了吗？你可以做的命令有：" + cmdList.toString());
        System.out.println("如: go east\n");
    }
}
