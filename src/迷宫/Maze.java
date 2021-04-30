package 迷宫;

import 迷宫.Constant;
import 迷宫.MazeNode;

import java.util.Scanner;
import java.util.Stack;


public class Maze {
    private MazeNode[][] mazeNodes;
    private Stack<MazeNode> stack;
    private int row;
    private int colum;
    private Scanner scanner = new Scanner(System.in);


    public Maze() {
        stack=new Stack<>();
        System.out.println("请输入行列数");
        row = scanner.nextInt();
        colum = scanner.nextInt();
        mazeNodes = new MazeNode[row][colum];
    }

    public void initValue() {
        System.out.println("输入迷宫路径：");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colum; j++) {
                mazeNodes[i][j] = new MazeNode(scanner.nextInt(),i,j);
            }
        }
    }

    public void initWayState(){
        for(int i=0;i<row;i++){
            for(int j=0;j<colum;j++){
                if(mazeNodes[i][j].getValue()==0){
                    if(j+1<colum && mazeNodes[i][j+1].getValue() == 0){
                        mazeNodes[i][j].setWayState(Constant.WAY_EAST,Constant.WAY_ABLE);
                    }
                    if(i+1<row&&mazeNodes[i+1][j].getValue()==0){
                        mazeNodes[i][j].setWayState(Constant.WAY_SOUTH,Constant.WAY_ABLE);
                    }
                    if(j-1>=0&&mazeNodes[i][j-1].getValue()==0){
                        mazeNodes[i][j].setWayState(Constant.WAY_WEST,Constant.WAY_ABLE);
                    }
                    if(i-1>=0&&mazeNodes[i-1][j].getValue()==0){
                        mazeNodes[i][j].setWayState(Constant.WAY_NORTH,Constant.WAY_ABLE);
                    }
                }
            }
        }
    }
    public void GoMaze() {
        initValue();
        initWayState();
        if (mazeNodes[0][0].getValue() != 0) {
            System.out.println("没有迷宫路劲");
            return;
        }
        stack.push(mazeNodes[0][0]);
        while (!stack.isEmpty()) {
                MazeNode top = stack.peek();
                int i = top.getI();
                int j = top.getJ();
                if(i==colum-1&&j==row-1){
                return;
                }
                if(mazeNodes[i][j].getWayState(Constant.WAY_EAST)==false&&mazeNodes[i][j].getWayState(Constant.WAY_WEST)==false&&mazeNodes[i][j].getWayState(Constant.WAY_SOUTH)==false&&mazeNodes[i][j].getWayState(Constant.WAY_NORTH)==false){
                    stack.pop();
                }
                if (mazeNodes[i][j].getWayState(Constant.WAY_EAST) == true) {
                    stack.push(mazeNodes[i][j + 1]);
                    mazeNodes[i][j + 1].setWayState(Constant.WAY_WEST, Constant.WAY_DISABLE);
                    mazeNodes[i][j].setWayState(Constant.WAY_EAST, Constant.WAY_DISABLE);
                }else if (mazeNodes[i][j].getWayState(Constant.WAY_WEST) == true) {
                    stack.push(mazeNodes[i][j - 1]);
                    mazeNodes[i][j - 1].setWayState(Constant.WAY_EAST, Constant.WAY_DISABLE);
                    mazeNodes[i][j].setWayState(Constant.WAY_WEST, Constant.WAY_DISABLE);
                }else if (mazeNodes[i][j].getWayState(Constant.WAY_SOUTH) == true) {
                    stack.push(mazeNodes[i + 1][j]);
                    mazeNodes[i + 1][j].setWayState(Constant.WAY_NORTH, Constant.WAY_DISABLE);
                    mazeNodes[i][j].setWayState(Constant.WAY_SOUTH, Constant.WAY_DISABLE);
                }else if (mazeNodes[i][j].getWayState(Constant.WAY_NORTH) == true) {
                    stack.push(mazeNodes[i - 1][j]);
                    mazeNodes[i - 1][j].setWayState(Constant.WAY_SOUTH, Constant.WAY_DISABLE);
                    mazeNodes[i][j].setWayState(Constant.WAY_NORTH, Constant.WAY_DISABLE);
                }

        }
    }

//    public void GoMaze(){
//        initValue();
//        initWayState();
//        if(mazeNodes[0][0].getValue()!=0){
//            System.out.println("没有迷宫路径");
//            return;
//        }
//        stack.push(mazeNodes[0][0]);
//        while (!stack.isEmpty()) {
//            MazeNode top = stack.peek();
//            //获取当前栈顶元素在二维数组中的行列坐标
//            int i = top.getI();
//            int j = top.getJ();
//            if(i == row-1&& j==colum-1){
//                System.out.println("找到迷宫路径");
//                return;
//            }
//            if(mazeNodes[i][j].getWayState(Constant.WAY_SOUTH)==false
//                    && mazeNodes[i][j].getWayState(Constant.WAY_EAST)==false
//                    && mazeNodes[i][j].getWayState(Constant.WAY_NORTH)==false
//                    && mazeNodes[i][j].getWayState(Constant.WAY_WEST)==false){
//                stack.pop();
//            }
//            //东
//            else if (mazeNodes[i][j].getWayState(Constant.WAY_EAST)) {
//                stack.push(mazeNodes[i][j + 1]);
//                mazeNodes[i][j+1].setWayState(Constant.WAY_WEST,Constant.WAY_DISABLE);
//                mazeNodes[i][j].setWayState(Constant.WAY_EAST,Constant.WAY_DISABLE);
//            }//南
//            else if (mazeNodes[i][j].getWayState(Constant.WAY_SOUTH)) {
//                //如果南边方向可走，将南边节点进行入栈操作
//                stack.push(mazeNodes[i + 1][j]);
//                //封路1：将南边节点的回路（北边）方向封掉
//                mazeNodes[i+1][j].setWayState(Constant.WAY_NORTH,false);
//                //封路2：将当前节点i,j  走过的路封掉
//                mazeNodes[i][j].setWayState(Constant.WAY_SOUTH,false);
//            }
//            //西
//            else if (mazeNodes[i][j].getWayState(Constant.WAY_WEST)) {
//                stack.push(mazeNodes[i][j - 1]);
//                mazeNodes[i][j-1].setWayState(Constant.WAY_EAST,false);
//                mazeNodes[i][j].setWayState(Constant.WAY_WEST,Constant.WAY_DISABLE);
//            }
//            //北
//            else if (mazeNodes[i][j].getWayState(Constant.WAY_NORTH)) {
//                stack.push(mazeNodes[i - 1][j]);
//                mazeNodes[i-1][j].setWayState(Constant.WAY_SOUTH,false);
//                mazeNodes[i][j].setWayState(Constant.WAY_NORTH,Constant.WAY_DISABLE);
//            }
//        }
//    }


    public void show(){
        while (!stack.isEmpty()){
            MazeNode top =stack.peek();
            top.setValue(6);
            stack.pop();
        }
        for(int i=0;i<row;i++){
            for(int j=0;j<colum;j++){
                System.out.print(mazeNodes[i][j].getValue()+"  ");
            }
            System.out.println();
        }
    }
}
