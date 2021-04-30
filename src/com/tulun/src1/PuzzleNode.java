package com.tulun.src1;

public class PuzzleNode {
    private int value;
    private int i;
    private boolean left;
    private boolean right;

    public PuzzleNode(int value,int i){
        this.value=value;
        this.i=i;
    }
    public int getValue() {
        return value;
    }

    public int getI() {
        return i;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }
}
