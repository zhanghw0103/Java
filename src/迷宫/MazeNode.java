package 迷宫;


import 迷宫.Constant;

public class MazeNode {
    private int value;
    private int i;
    private int j;
    private boolean[]wayState;


    public MazeNode(int value,int i,int j){
        wayState = new boolean[Constant.WAYNUM];
        this.value = value;
        this.i=i;
        this.j=j;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value){
        this.value=value;
    }

    public void setWayState(int direction,boolean isAble) {
        wayState[direction] = isAble;
    }

    public boolean getWayState(int direction) {
        return wayState[direction];
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}

