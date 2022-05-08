package com.itheima;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.TimerTask;

public class MainFrame extends JFrame {
    private JPanel jpanel;
    private Snake snake;
    //棋盘
    private Timer timer;
    //在规定时间内调用蛇移动
    private Node food;
    //食物




    public MainFrame() throws HeadlessException {
        initFrame();//窗体
        //初始化游戏棋盘
        iniGamePanel();
        //初始化蛇
        initSnake();
        initFood();
        initTimer();//初始定时器
        //设置键盘监听
        setKeyListener();
    }

    private void initFood(){
        food=new Node();
        food.random();
    }

    private void setKeyListener() {
        addKeyListener(new KeyAdapter() {
            //键盘按下时会自动调用此方法

            @Override
            public void keyPressed(KeyEvent e) {

                //键盘每个键都有一个编号
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP://修改蛇的运动方向
                        if (snake.getDirection() != Direction.DOWN)
                        { snake.setDirection(Direction.UP);
                            break;}
                    case KeyEvent.VK_DOWN:
                        if (snake.getDirection() != Direction.UP)
                        {snake.setDirection(Direction.DOWN);
                            break;}
                    case KeyEvent.VK_LEFT:
                        if (snake.getDirection() != Direction.RIGHT)
                        { snake.setDirection(Direction.LEFT);
                            break;}
                    case KeyEvent.VK_RIGHT:
                        if (snake.getDirection() != Direction.LEFT)
                        { snake.setDirection(Direction.RIGHT);
                            break;}
                    case KeyEvent.VK_SPACE:if(snake.isLiving==false){
                        snake.isLiving=true;
                        break; }

                       if(snake.isStart==true){
                           snake.isStart=false;
                        break;
                     }
                      if(snake.isStart==false){
                           snake.isStart=true;
                           break;
                        }

                }
            }
        });
    }

    private void initTimer() {
        timer = new Timer();//创建定时器对象

        //初始化定时任务
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {

                //判断蛇头是否和食物重合
                Node head=snake.getBody().getFirst();
                if (head.getX()== food.getX()&&head.getY()==food.getY()){
                    snake.eat(food);
                    food.random();
                    snake.score++;

                }
                snake.move();
                //重绘游戏棋盘
                jpanel.repaint();
            }
        };
        //每100毫秒，执行一次定时任务
        timer.scheduleAtFixedRate(timerTask, 0, 100);

    }

    private void initSnake() {
        snake = new Snake();

    }

    //设置窗体参数
    private void initFrame() {
        setSize(620, 660);
        setLocation(200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //退出程序
        setResizable(false);
    }

    private void iniGamePanel() {
        jpanel = new JPanel() {
            @Override
            public void paint(Graphics g) {

                //游戏提示
                if (snake.isLiving==false){
                    g.setColor(Color.black);
                    g.setFont(new Font("微软雅黑",Font.BOLD,40));
                    g.drawString("按下空格开始游戏!",50,50);
                    jpanel.repaint();
                    snake = new Snake();
                    food=new Node();
                    food.random();
                }
        if(snake.isLiving==true){

                //清空区域
                g.clearRect(0, 0, 620, 640);
            //显示得分
            g.setColor(Color.black);
            g.setFont(new Font("微软雅黑",Font.BOLD,20));
            g.drawString("得分："+ snake.score ,100,620);
            jpanel.repaint();
                for (int i = 0; i <= 40; i++) {
                    g.drawLine(0, i * 15, 600, i * 15);
                }
                for (int i = 0; i <= 40; i++) {
                    g.drawLine(i * 15, 0, i * 15, 600);
                }

                //绘制蛇
                LinkedList<Node> body = snake.getBody();
                for (Node node : body) {
                    g.fillRect(node.getX() * 15, node.getY() * 15, 15, 15);
                }

                //绘制食物
                g.fillRect(food.getX()*15, food.getY()*15,15,15);
            }}
        };
        add(jpanel);
    }

    public static void main(String[] args) {
        new MainFrame().setVisible(true);

    }
}