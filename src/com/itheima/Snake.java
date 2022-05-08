package com.itheima;

import java.util.LinkedList;

public class Snake {
    //snake表示蛇，一条蛇由linkedList集合存储NOde节点，初始有1个节点

    private LinkedList<Node>body;//蛇身
    private  Direction direction = Direction.LEFT;//默认方向
    //蛇是否活着
    public boolean isLiving=false;
    public boolean isStart=true;//是否开始
    public Snake(){
        initSnake();
    }
    public int score;
    //蛇身
    private void initSnake(){
        //创建集合，合成蛇身
        body = new LinkedList<>();
        //创建1个节点当蛇身
        body.add(new Node(16,20));

    }



    //默认沿着蛇头方向移动,蛇头运动防线创建一个节点，蛇尾删除最后一个节点
    public void move() {

        //判断是否活着
        if (isLiving==true &&isStart==true) {
            //获取蛇头
            Node head = body.getFirst();
            switch (direction) {
                case UP:/*在蛇头上面添加节点*/
                    body.addFirst(new Node(head.getX(), head.getY() - 1));
                    break;
                case DOWN:
                    body.addFirst(new Node(head.getX(), head.getY() + 1));
                    break;
                case LEFT:
                    body.addFirst(new Node(head.getX() - 1, head.getY()));
                    break;
                case RIGHT:
                    body.addFirst(new Node(head.getX() + 1, head.getY()));
                    break;
            }
            body.removeLast();//删除最后节点

            //判断蛇是否撞墙
            head = body.getFirst();
            if (head.getX() < 0 || head.getY() < 0 || head.getX() >= 40 || head.getY() >= 40) {
                isLiving = false;
            }

            //判断蛇是否碰到自己身体
            for (int i = 1; i < body.size(); i++) {
                Node node = body.get(i);
                if (head.getX() == node.getX() && head.getY() == node.getY()) {
                    isLiving = false;
                }
            }


        }
    }


    //重新开始游戏


    public LinkedList<Node> getBody() {
        return body;
    }

    public void setBody(LinkedList<Node> body) {
        this.body = body;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void eat(Node food) {
        //沿着蛇头的移动方向添加节点
        Node head = body.getFirst();
        switch (direction) {
            case UP:/*在蛇头上面添加节点*/
                body.addFirst(new Node(head.getX(), head.getY() - 1));
                break;
            case DOWN:
                body.addFirst(new Node(head.getX(), head.getY() + 1));
                break;
            case LEFT:
                body.addFirst(new Node(head.getX() - 1, head.getY()));
                break;
            case RIGHT:
                body.addFirst(new Node(head.getX() + 1, head.getY()));
                break;
        }

    }
}