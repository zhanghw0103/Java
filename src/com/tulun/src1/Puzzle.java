package com.tulun.src1;

import java.util.Scanner;
import java.util.Stack;

public class Puzzle {


    private Stack<PuzzleNode> stack;
    private PuzzleNode[] PuzzleNodes;
    private Scanner scanner = new Scanner(System.in);

    public Puzzle(){
        stack = new Stack<>();

    }
    public void GoPuzzle() {
        PuzzleNodes = new PuzzleNode[10];
        for (int i = 0; i < PuzzleNodes.length; i++) {
            PuzzleNodes[i] = new PuzzleNode(scanner.nextInt(), i);
        }
        for (int i = 0; i < PuzzleNodes.length; i++) {
            if (i - PuzzleNodes[i].getValue() >= 0) {
                PuzzleNodes[i].setLeft(true);
            }
            if (i + PuzzleNodes[i].getValue() < PuzzleNodes.length) {
                PuzzleNodes[i].setRight(true);
            }
        }
        stack.push(PuzzleNodes[0]);

        while (true) {
            PuzzleNode top = stack.peek();
            if (top.getI() == 10 - 1) {
                return;
            }
            if (top.isRight()) {
                stack.push(PuzzleNodes[top.getI() + top.getValue()]);
                top.setRight(false);
            } else if (top.isLeft()) {
                stack.push(PuzzleNodes[top.getI() - top.getValue()]);
                top.setLeft(false);
            }
        }
    }
        public void show(){
            while (!stack.empty()){
                PuzzleNode top = stack.peek();
                System.out.println(top.getValue()+" <- ");
                stack.pop();
            }
        }








}
