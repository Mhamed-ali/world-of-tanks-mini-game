package demo1;

import java.io.File;
import javafx.scene.shape.*;
import javafx.animation.Timeline;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import static javafx.application.Application.launch;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

enum direction {
    UP, DOWN, RIGHT, LEFT;

}

class Tank extends Rectangle {

    private String s = "AAA ";

    private direction dir = direction.UP;
    private int possitionX;
    private int miscounter = 10;
    private int possitionY;
    private int health;
    private int live;
    private boolean Tup = false;
    private boolean Tdouwn = false;
    private boolean Tleft = false;
    private boolean Tright = false;
    private int roundlost = 0;
    private int respawnx = 0;
    private int respawny = 0;
    private boolean misdelay = false;

    public void setS(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }

    public Tank() throws IOException {
        //FileInputStream input = new FileInputStream("file:src/images.png");
        Image image = new Image("file:src/images.png");

        ImagePattern imagePattern = new ImagePattern(image);
        this.setFill(imagePattern);

        this.setX(510);
        this.setY(500);
        this.setHeight(100);
        this.setWidth(100);

    }

    public boolean getMisdelay() {
        return this.misdelay;
    }

    public void setMisdelay(boolean misdelay) {
        this.misdelay = misdelay;
    }

    public void setRoundlost(int roundlost) {
        this.roundlost = roundlost;
    }

    public int getRoundlost() {
        return roundlost;
    }

    public void setTright(boolean Tright) {
        this.Tright = Tright;
    }

    public void setTleft(boolean Tleft) {
        this.Tleft = Tleft;
    }

    public void setTdouwn(boolean Tdouwn) {
        this.Tdouwn = Tdouwn;
    }

    public void setTup(boolean Tup) {
        this.Tup = Tup;
    }

    public void setMiscounter(int miscounter) {
        this.miscounter = miscounter;
    }

    public boolean getTup() {
        return this.Tup;
    }

    public boolean getTdowun() {
        return this.Tdouwn;
    }

    public boolean getTright() {
        return this.Tright;
    }

    public boolean getTleft() {
        return this.Tleft;
    }

    public int getMiscounter() {
        return miscounter;
    }

    public void setRespawny(int respawny) {
        this.respawny = respawny;
    }

    public void setRespawnx(int respawnx) {
        this.respawnx = respawnx;
    }

    public int getRespawny() {
        return respawny;
    }

    public int getRespawnx() {
        return respawnx;
    }

    public void setDir(direction dir) {
        this.dir = dir;
    }

    public direction getDir() {
        return dir;
    }

}

class Brick extends Rectangle {

    public Brick(int x, int y, int width, int hight) throws IOException {
        //   FileInputStream input = new FileInputStream("file:src/download.png");
        Image image = new Image("file:src/download.png");

        ImagePattern imagePattern = new ImagePattern(image);
        this.setFill(imagePattern);
        this.setX(x);
        this.setY(y);
        this.setHeight(hight);
        this.setWidth(width);

    }

}

class missile extends Rectangle {

    private final direction dir;
//    private double speed;

    public missile(Tank T) throws FileNotFoundException {
        //  FileInputStream input = new FileInputStream("file:src/missle.png");
        Image image = new Image("file:src/missle.png");

        ImagePattern imagePattern = new ImagePattern(image);
        this.setFill(imagePattern);
        this.setHeight(40);
        this.setWidth(25);
        this.setX(T.getX() + T.getWidth() / 2 - this.getWidth() / 2);
        if (null != T.getDir()) {
            switch (T.getDir()) {
                case UP:
                    this.setY(T.getY() - this.getHeight());
                    break;
                case DOWN:
                    this.setY(T.getY() + T.getHeight());
                    break;
                case RIGHT:
                    this.setY(T.getY() + this.getHeight() / 2 + this.getWidth() / 2);
                    this.setX(this.getX() + T.getWidth() / 2 + this.getWidth());
                    break;
                case LEFT:
                    this.setY(T.getY() + this.getHeight() / 2 + this.getWidth() / 2);
                    this.setX(this.getX() - T.getWidth() / 2 - this.getWidth());
                    break;
                default:
                    break;
            }
        }

        this.dir = T.getDir();
        if (null != dir)//        this.setFill(Color.ORANGE);
        {
            switch (dir) {
                case UP:
                    this.setRotate(0);
                    break;
                case DOWN:
                    this.setRotate(180);
                    break;
                case RIGHT:
                    this.setRotate(90);
                    break;
                case LEFT:
                    this.setRotate(270);
                    break;
                default:
                    break;
            }
        }

    }

    public direction getDir() {
        return dir;
    }

}

class Rectangl4 extends Rectangle {

    public Rectangl4(int width, int height) throws FileNotFoundException {
        //  FileInputStream input = new FileInputStream("file:src/mmt.png");
//        Image image = new Image("file:src/mmt.png");

//        ImagePattern imagePattern = new ImagePattern(image);
        this.setFill(Color.BLACK);
        this.setOpacity(0.4);

        this.setWidth(width + 10);
        this.setHeight(height + 10);
        this.setY(0);
    }
}

class Rectangl3 extends Rectangle {

    public Rectangl3(int width, int height) throws FileNotFoundException {
        //  FileInputStream input = new FileInputStream("file:src/mmt.png");
        Image image = new Image("file:src/mmt.png");

        ImagePattern imagePattern = new ImagePattern(image);
        this.setFill(imagePattern);

        this.setWidth(width + 10);
        this.setHeight(height + 10);
        this.setY(0);
    }
}

class Rectangl extends Rectangle {

    public Rectangl(int width, int height) {
        this.setWidth(width + 50);
        this.setHeight(height + 50);
        this.setY(0);
        this.setFill(Color.GREY);
    }
}

class rectangl2 extends Rectangle {

    public rectangl2() {
        this.setWidth(1210);
        this.setHeight(910);
        this.setY(0);
        this.setFill(Color.BLUE);
    }
}

class map2 extends Pane {

    final static int scw = 1200;
    final static int sch = 900;

    Button b = new Button("New Game Map1");
    Button b1 = new Button("New Game Map2");
    Button bp = new Button("coming soon");

    Button b2 = new Button("Exit");

    map2() {
        Rectangl3 rectang = null;
        try {
            rectang = new Rectangl3(scw, sch);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(map2.class.getName()).log(Level.SEVERE, null, ex);
        }

        getChildren().add(rectang);

        getChildren().addAll(b, b2, b1, bp);
        b.setOnAction(e -> setScene());
        b.setPrefSize(400, 100);
        b.setLayoutX(400);
        b.setLayoutY(140);
        b.setOpacity(0.7);
        b.setStyle("-fx-text-fill: white");
        b.setFont(Font.font("Helvetica"));
        b.setBackground(new Background(new BackgroundFill(
                Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        Font font = new Font(40);
        b.setFont(font);

        b.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                b.setStyle("-fx-background-color:#dae7f3;");
            }
        });

        b.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                b.setOpacity(0.7);
                b.setStyle("-fx-text-fill: white");
            }
        });

        b1.setOnAction(e -> setScene1());
        b1.setPrefSize(400, 100);
        b1.setLayoutX(400);
        b1.setLayoutY(280);
        b1.setOpacity(0.7);
        b1.setStyle("-fx-text-fill: white");
        b1.setFont(Font.font("Helvetica"));
        b1.setBackground(new Background(new BackgroundFill(
                Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        b1.setFont(font);

        b1.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                b1.setStyle("-fx-background-color:#dae7f3;");
            }
        });

        b1.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                b1.setOpacity(0.7);
                b1.setStyle("-fx-text-fill: white");
            }
        });

        bp.setFont(font);
       // bp.setOnAction(e -> setScenep());
        bp.setPrefSize(400, 100);
        bp.setLayoutX(400);
        bp.setLayoutY(420);
        bp.setOpacity(0.7);
        bp.setStyle("-fx-text-fill: white");
        bp.setFont(Font.font("Helvetica"));
        bp.setBackground(new Background(new BackgroundFill(
                Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        bp.setFont(font);
//        b.setStyle("-fx-background-color: transparent;");
        bp.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                bp.setStyle("-fx-background-color:#dae7f3;");
            }
        });

        bp.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                bp.setOpacity(0.7);
                bp.setStyle("-fx-text-fill: white");
            }
        });

        b2.setPrefSize(400, 100);
        b2.setLayoutX(400);
        b2.setLayoutY(560);
        b2.setFont(Font.font("Calibri"));
        b2.setBackground(new Background(new BackgroundFill(
                Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        b2.setOpacity(0.7);
        b2.setStyle("-fx-text-fill: white");
        b2.setFont(font);

        b2.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                b2.setStyle("-fx-background-color:#dae7f3;");
            }
        });

        b2.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                b2.setOpacity(0.7);
                b2.setStyle("-fx-text-fill: white");
            }
        });

        b2.setOnAction(e -> {
            System.exit(0);
        });
        Button b3 = new Button();
        b3.setOnAction(e -> {
        }
        );

    }

    public void add2() {

    }

    public int getscw() {
        return map2.scw;
    }

    public int getsch() {
        return map2.sch;
    }

    private void setScene1() {
        Demo1.setScene1();
    }


    private void setScene() {
        Demo1.setScene();
    }

    public static void setScene2() {
        Demo1.setScene2();
    }
}

class map extends Pane {

    boolean pause = false;
    final static int scw = 1200;
    final static int sch = 900;
    Tank T = new Tank();
    Tank T2 = new Tank();
    Button b = new Button("new game");
    Rectangl4 rec = new Rectangl4(scw, sch);
    Button b2 = new Button("exit");
    Label l2 = new Label();
    Label l = new Label();
    Label ll = new Label(T.getS());
    Button r = new Button("resume");
    Text round = new Text();

    private void play_audio() {
        Media hit = new Media(new File("src/9.wav").toURI().toString());
        MediaPlayer note = new MediaPlayer(hit);
        note.play();
    }

    public int getscw() {
        return map.scw;
    }

    public int getsch() {
        return map.sch;
    }

    map(int i) throws IOException {
//        this.requestFocus();

        Media hit = new Media(new File("src/Two Steps From Hell - Victory.wav").toURI().toString());
        MediaPlayer player = new MediaPlayer(hit);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();
        if (i == 1) {
            add();

        } else {
            add2();
        }

        b.setPrefSize(300, 100);
        b2.setPrefSize(300, 100);

        b.setLayoutX(450);
        b.setLayoutY(350);
        b.setOpacity(0.7);
        b.setStyle("-fx-text-fill: white");
        b.setFont(Font.font("Helvetica"));
        b.setBackground(new Background(new BackgroundFill(
                Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        Font font = new Font(40);
        b.setFont(font);

        b.setOnAction(e -> {
            newgame();
            map2.setScene2();
            newgame();

        });

        b2.setLayoutX(450);
        b2.setLayoutY(500);

        b2.setOpacity(0.7);
        b2.setStyle("-fx-text-fill: white");
        b2.setFont(Font.font("Helvetica"));
        b2.setBackground(new Background(new BackgroundFill(
                Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        Font font2 = new Font(40);
        b2.setFont(font2);

        b2.setOnAction(e -> {
            System.exit(0);
        });
        r.setPrefSize(300, 100);
        r.setLayoutX(450);
        r.setLayoutY(200);
        r.setOpacity(0.7);
        r.setStyle("-fx-text-fill: white");
        r.setFont(Font.font("Helvetica"));
        r.setBackground(new Background(new BackgroundFill(
                Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        Font fontr = new Font(40);
        r.setFont(fontr);

        r.setOnAction(e -> {
            resume();
        });

        round.setFill(Color.WHITE);
        round.setText("player 1: " + (T.getRoundlost() + "     player 2 :" + T2.getRoundlost()));
        round.setX(scw / 2 - 17);
        round.setY(20);
        getChildren().add(round);
        l.setTextFill(Color.web("#ffffff"));
        l.setText("Player1 remaining missles :" + Integer.toString(T.getMiscounter()));
        l.setAlignment(Pos.BASELINE_RIGHT);
        l.setLayoutX(-30);
        l.setTranslateX(scw - 160);
        getChildren().add(l);
        l2.setTextFill(Color.web("#ffffff"));
        l2.setText("Player2 remaining missles :" + Integer.toString(T2.getMiscounter()));
        l2.setAlignment(Pos.CENTER);
        l2.setTranslateX(8);

        getChildren().add(l2);

        setOnKeyPressed(e -> {
            if (this.pause == false) {
                if (e.getCode() == KeyCode.ESCAPE) {
                    gamepause();
                }
                if (e.getCode() == KeyCode.SPACE) {
                    if (T.getMiscounter() > 0 && T.getMisdelay() == false) {
                        T.setMiscounter(T.getMiscounter() - 1);
                        l.setText("Player1 remaining missles :" + Integer.toString(T.getMiscounter()));
                        try {
                            getChildren().add(new missile(T));
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(map.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    // play_audio();

                }
                if (e.getCode() == KeyCode.CONTROL) {
                    if (T2.getMiscounter() > 0) {
                        T2.setMiscounter(T2.getMiscounter() - 1);
                        l2.setText("Player2 remaining missles :" + Integer.toString(T2.getMiscounter()));
                        try {
                            getChildren().add(new missile(T2));
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(map.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    //                      play_audio();

                }
                if (e.getCode() == KeyCode.UP && this.pause == false) {
                    T.setTup(true);
                } else if (e.getCode() == KeyCode.DOWN) {
                    T.setTdouwn(true);
                } else if (e.getCode() == KeyCode.LEFT) {
                    T.setTleft(true);
                } else if (e.getCode() == KeyCode.RIGHT) {
                    T.setTright(true);

                }
                if (e.getCode() == KeyCode.W) {
                    T2.setTup(true);

                } else if (e.getCode() == KeyCode.S) {
                    T2.setTdouwn(true);

                } else if (e.getCode() == KeyCode.A) {
                    T2.setTleft(true);

                } else if (e.getCode() == KeyCode.D) {
                    T2.setTright(true);

                }
            }
        }
        );

        setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.UP) {
                T.setTup(false);
            } else if (e.getCode() == KeyCode.DOWN) {
                T.setTdouwn(false);
            } else if (e.getCode() == KeyCode.LEFT) {
                T.setTleft(false);
            } else if (e.getCode() == KeyCode.RIGHT) {
                T.setTright(false);

            }
            if (e.getCode() == KeyCode.W) {
                T2.setTup(false);

            } else if (e.getCode() == KeyCode.S) {
                T2.setTdouwn(false);

            } else if (e.getCode() == KeyCode.A) {
                T2.setTleft(false);

            } else if (e.getCode() == KeyCode.D) {
                T2.setTright(false);

            }
        });
        EventHandler<ActionEvent> anim;
        anim = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (Node n : getChildren()) {

                    //optional check if randomcircle 
                    if (n instanceof missile) {
                        missile mis = (missile) n;
                        if (null != mis.getDir()) {
                            switch (mis.getDir()) {
                                case UP:
                                    for (Node m : getChildren()) {
                                        if (m instanceof Brick) {
                                            if (mis.getX() < (((Brick) m).getX() + ((Brick) m).getWidth() - mis.getWidth() / 2 + 1)
                                                    && mis.getX() > ((Brick) m).getX() - mis.getWidth() / 2 + 1
                                                    && mis.getY() - 5 < ((Brick) m).getY() + ((Brick) m).getHeight()
                                                    && mis.getY() > ((Brick) m).getY()
                                                    || mis.getY() < 0 - mis.getHeight()) {
                                                misremove(mis);
                                            }
                                        }

                                        if (m instanceof Tank) {
                                            if (mis.getX() < (((Tank) m).getX() + ((Tank) m).getWidth() - mis.getWidth() / 2 + 1)
                                                    && mis.getX() > ((Tank) m).getX() - mis.getWidth() / 2 + 1
                                                    && mis.getY() - 5 < ((Tank) m).getY() + ((Tank) m).getHeight()
                                                    && mis.getY() > ((Tank) m).getY()
                                                    || mis.getY() < 0 - mis.getHeight()) {
                                                misremove(mis);
                                                ((Tank) m).setRoundlost(((Tank) m).getRoundlost() + 1);
                                                round.setText("player 1: " + (T.getRoundlost() + "     player 2 :" + T2.getRoundlost()));
                                                respawn();
                                                if (((Tank) m).getRoundlost() > 2) {
                                                    gameover(((Tank) m));
                                                }
                                            }

                                        }
                                    }

                                    mis.setY(mis.getY() - 5);

                                    break;

                                case DOWN:
                                    for (Node m : getChildren()) {
                                        if (m instanceof Brick) {
                                            if (mis.getX() < (((Brick) m).getX() + ((Brick) m).getWidth() - mis.getWidth() / 2 + 1)
                                                    && mis.getX() > ((Brick) m).getX() - mis.getWidth() / 2 + 1
                                                    && mis.getY() - 5 < ((Brick) m).getY() + ((Brick) m).getHeight()
                                                    && mis.getY() > ((Brick) m).getY() - mis.getHeight()
                                                    || mis.getY() > getsch()) {
                                                misremove(mis);
                                            }
                                        }

                                        if (m instanceof Tank) {
                                            if (mis.getX() < (((Tank) m).getX() + ((Tank) m).getWidth() - mis.getWidth() / 2 + 1)
                                                    && mis.getX() > ((Tank) m).getX() - mis.getWidth() / 2 + 1
                                                    && mis.getY() + 5 < ((Tank) m).getY() + ((Tank) m).getHeight()
                                                    && mis.getY() > ((Tank) m).getY() - mis.getHeight()) {
                                                misremove(mis);
                                                ((Tank) m).setRoundlost(((Tank) m).getRoundlost() + 1);
                                                round.setText("player 1: " + (T.getRoundlost() + "     player 2 :" + T2.getRoundlost()));
                                                respawn();

                                                if (((Tank) m).getRoundlost() > 2) {
                                                    gameover(((Tank) m));
                                                }
                                            }
                                        }
                                    }
                                    mis.setY(mis.getY() + 5);
                                    break;

                                case RIGHT:

                                    for (Node m : getChildren()) {
                                        if (m instanceof Brick) {

                                            if ((mis.getX() > ((Brick) m).getX() - mis.getHeight())
                                                    && mis.getX() < ((Brick) m).getX() + ((Brick) m).getWidth()
                                                    && mis.getY() < (((Brick) m).getY() + ((Brick) m).getHeight() - mis.getHeight() / 2)
                                                    && mis.getY() > ((Brick) m).getY() - mis.getWidth() / 2 - 10) {
                                                misremove(mis);
                                            }
                                        }
                                        if (m instanceof Tank) {
                                            if ((mis.getX() > ((Tank) m).getX() - mis.getHeight())
                                                    && mis.getX() < ((Tank) m).getX() + ((Tank) m).getWidth()
                                                    && mis.getY() < (((Tank) m).getY() + ((Tank) m).getHeight() - mis.getHeight() / 2)
                                                    && mis.getY() > ((Tank) m).getY() - mis.getWidth() / 2 - 10) {
                                                misremove(mis);
                                                ((Tank) m).setRoundlost(((Tank) m).getRoundlost() + 1);

                                                round.setText("player 1: " + (T.getRoundlost() + "     player 2 :" + T2.getRoundlost()));
                                                respawn();

                                                if (((Tank) m).getRoundlost() > 2) {
                                                    gameover(((Tank) m));
                                                }
                                            }
                                        }
                                    }
                                    mis.setX(mis.getX() + 5);
                                    break;

                                case LEFT:

                                    for (Node m : getChildren()) {
                                        if (m instanceof Brick) {

                                            if ((mis.getX() > ((Brick) m).getX() - mis.getHeight())
                                                    && mis.getX() < ((Brick) m).getX() + ((Brick) m).getWidth() + mis.getHeight() / 2
                                                    && mis.getY() < (((Brick) m).getY() + ((Brick) m).getHeight() - mis.getHeight() / 2)
                                                    && mis.getY() > ((Brick) m).getY() - mis.getWidth() / 2 - 10) {
                                                misremove(mis);
                                            }
                                        }
                                        if (m instanceof Tank) {
                                            if (mis.getX() > ((Tank) m).getX()
                                                    && mis.getX() - 15 < ((Tank) m).getX() + ((Tank) m).getWidth()
                                                    && mis.getY() < (((Tank) m).getY() + ((Tank) m).getHeight() - mis.getHeight() / 4)
                                                    && mis.getY() > ((Tank) m).getY() - mis.getHeight() / 2 - mis.getWidth() / 2) {
                                                misremove(mis);
                                                ((Tank) m).setRoundlost(((Tank) m).getRoundlost() + 1);
                                                round.setText("player 1: " + (T.getRoundlost() + "     player 2 :" + T2.getRoundlost()));
                                                respawn();

                                                if (((Tank) m).getRoundlost() > 2) {
                                                    gameover(((Tank) m));
                                                }

                                            }
                                        }
                                    }
                                    mis.setX(mis.getX() - 5);
                                    break;

                                default:
                                    break;
                            }
                        }

                    } else if (n instanceof Tank) {

                        Tank T = (Tank) n;

                        if (T.getTup() == true) {
                            for (Node b : getChildren()) {

                                if (b instanceof Brick) {
                                    Brick B = (Brick) b;
                                    if (T.getX() < (B.getX() + B.getWidth())
                                            && T.getX() > B.getX() - B.getWidth()
                                            && T.getY() - 2 < B.getY() + B.getHeight()
                                            && T.getY() > B.getY() && T.getTup() == true || T.getY() < 0) {
                                        T.setDir(direction.UP);
                                        T.setRotate(0);
                                        T.setTup(false);
                                        break;

                                    }

                                }
                                if (b instanceof Tank) {

                                    Tank B = (Tank) b;
                                    if (T.getX() < (B.getX() + B.getWidth())
                                            && T.getX() > B.getX() - B.getWidth()
                                            && T.getY() - 2 < B.getY() + B.getHeight()
                                            && T.getY() > B.getY() && T.getTup() == true) {
                                        T.setDir(direction.UP);
                                        T.setRotate(0);
                                        T.setTup(false);
                                        break;
                                    }

                                }

                            }
                            if (T.getTup() == true) {

                                T.setY(T.getY() - 2);
                                T.setDir(direction.UP);
                                T.setRotate(0);

                            }
                        } else if (T.getTdowun() == true) {
                            for (Node b : getChildren()) {
                                if (b instanceof Brick) {
                                    Brick B = (Brick) b;
                                    if (T.getX() < (B.getX() + B.getWidth())
                                            && T.getX() > B.getX() - B.getWidth()
                                            && T.getY() < B.getY()
                                            && T.getY() + 2 > B.getY() - T.getHeight() && T.getTdowun() == true || T.getY() > sch - T.getHeight()) {
                                        T.setDir(direction.DOWN);
                                        T.setRotate(180);
                                        T.setTdouwn(false);
                                        break;

                                    }

                                }
                                if (b instanceof Tank) {

                                    Tank B = (Tank) b;
                                    if (T.getX() < (B.getX() + B.getWidth())
                                            && T.getX() > B.getX() - B.getWidth()
                                            && T.getY() < B.getY()
                                            && T.getY() + 2 > B.getY() - T.getHeight()
                                            && T.getTdowun() == true) {
                                        T.setDir(direction.DOWN);
                                        T.setRotate(180);
                                        T.setTdouwn(false);
                                        break;
                                    }

                                }

                            }
                            if (T.getTdowun() == true) {
                                T.setDir(direction.DOWN);
                                T.setRotate(180);
                                T.setY(T.getY() + 2);
                            }

                        } else if (T.getTleft() == true) {
                            for (Node b : getChildren()) {
                                if (b instanceof Brick) {
                                    Brick B = (Brick) b;
                                    if (T.getX() - 4 < B.getX() + B.getHeight()
                                            && T.getX() > B.getX()
                                            && T.getY() < (B.getY() + B.getHeight())
                                            && T.getY() > B.getY() - T.getHeight() && T.getTleft() == true
                                            || T.getX() < 0) {
                                        T.setDir(direction.LEFT);
                                        T.setRotate(270);
                                        T.setTleft(false);
                                        break;

                                    }

                                }
                                if (b instanceof Tank) {
                                    Tank B = (Tank) b;
                                    if (T.getX() - 4 < B.getX() + B.getHeight()
                                            && T.getX() > B.getX()
                                            && T.getY() < (B.getY() + B.getHeight())
                                            && T.getY() > B.getY() - T.getHeight() && T.getTleft() == true) {
                                        T.setDir(direction.LEFT);
                                        T.setRotate(270);
                                        T.setTleft(false);
                                        break;

                                    }

                                }
                            }
                            if (T.getTleft() == true) {

                                T.setX(T.getX() - 2);
                                T.setDir(direction.LEFT);
                                T.setRotate(270);
                            }

                        } else if (T.getTright() == true) {
                            for (Node b : getChildren()) {
                                if (b instanceof Brick) {
                                    Brick B = (Brick) b;
                                    if (T.getX() + 2 > B.getX() - B.getWidth()
                                            && T.getX() < B.getX() + B.getWidth()
                                            && T.getY() < (B.getY() + B.getHeight())
                                            && T.getY() > B.getY() - T.getHeight() || T.getX() > scw - T.getWidth()) {
                                        T.setDir(direction.RIGHT);
                                        T.setRotate(90);
                                        T.setTright(false);
                                        break;

                                    }

                                }
                                if (b instanceof Tank) {
                                    Tank B = (Tank) b;
                                    if (T.getX() + 5 > B.getX() - B.getWidth()
                                            && T.getX() + T.getWidth() < B.getX() + B.getWidth()
                                            && T.getY() < (B.getY() + B.getHeight())
                                            && T.getY() > B.getY() - T.getHeight()
                                            && T.getTright() == true) {
                                        T.setDir(direction.RIGHT);
                                        T.setRotate(90);
                                        T.setTright(false);
                                        break;

                                    }

                                }

                            }
                            if (T.getTright() == true) {

                                T.setX(T.getX() + 2);
                                T.setDir(direction.RIGHT);
                                T.setRotate(90);

                            }
                        }
                    }
                }

            }

        };

        Timeline t = new Timeline(new KeyFrame(Duration.millis(30), anim));

        t.setCycleCount(Timeline.INDEFINITE);

        t.play();

    }

    public void misremove(missile m) {
        this.getChildren().remove(m);

    }

    public void respawn() {

        T.setX(T.getRespawnx());
        T.setY(T.getRespawny());
        T.setMiscounter(10);
        T.setRotate(0);
        T.setDir(direction.UP);

        T.setTup(false);
        T.setTright(false);
        T.setTleft(false);
        T.setTdouwn(false);
        T2.setTup(false);
        T2.setTright(false);
        T2.setTleft(false);
        T2.setTdouwn(false);
        T2.setX(T2.getRespawnx());
        T2.setY(T2.getRespawny());
        T2.setMiscounter(10);
        T2.setRotate(180);
        T2.setDir(direction.DOWN);
        l.setText("Player1 remaining missles :" + Integer.toString(T.getMiscounter()));
        l2.setText("Player2 remaining missles :" + Integer.toString(T2.getMiscounter()));
        for (Node b : getChildren()) {
            if (b instanceof missile) {
                missile B = (missile) b;
                misremove(B);
            }
        }

    }

    public void gameover(Tank T) {

        map2.setScene2();
        newgame();

    }

    private void add() throws IOException {
        Rectangl rectang = new Rectangl(scw, sch);
        this.getChildren().add(rectang);

        //*********************************************************************//
        this.getChildren()
                .add(new Brick(0, 0, 100, 100));
        this.getChildren()
                .add(new Brick(0, 100, 100, 100));

        this.getChildren()
                .add(new Brick(0, 200, 100, 100));
        this.getChildren()
                .add(new Brick(0, 300, 100, 100));
        this.getChildren()
                .add(new Brick(0, 400, 100, 100));
        this.getChildren()
                .add(new Brick(0, 500, 100, 100));
        this.getChildren()
                .add(new Brick(0, 600, 100, 100));
        this.getChildren()
                .add(new Brick(0, 700, 100, 100));
        this.getChildren()
                .add(new Brick(0, 800, 100, 100));
        this.getChildren()
                .add(new Brick(0, 900, 100, 100));

        //////////////////////////////////////////////////
        this.getChildren()
                .add(new Brick(scw - 100, 0, 100, 100));
        this.getChildren()
                .add(new Brick(scw - 100, 100, 100, 100));

        this.getChildren()
                .add(new Brick(scw - 100, 200, 100, 100));
        this.getChildren()
                .add(new Brick(scw - 100, 300, 100, 100));
        this.getChildren()
                .add(new Brick(scw - 100, 400, 100, 100));
        this.getChildren()
                .add(new Brick(scw - 100, 500, 100, 100));
        this.getChildren()
                .add(new Brick(scw - 100, 600, 100, 100));
        this.getChildren()
                .add(new Brick(scw - 100, 700, 100, 100));
        this.getChildren()
                .add(new Brick(scw - 100, 800, 100, 100));
        this.getChildren()
                .add(new Brick(scw - 100, 900, 100, 100));
//////////////////////////////////////////////////////////////
        this.getChildren()
                .add(new Brick(300, 250, 100, 100));
        this.getChildren()
                .add(new Brick(400, 250, 100, 100));
        this.getChildren()
                .add(new Brick(500, 250, 100, 100));
        this.getChildren()
                .add(new Brick(600, 250, 100, 100));
        this.getChildren()
                .add(new Brick(700, 250, 100, 100));
        this.getChildren()
                .add(new Brick(800, 250, 100, 100));

//////////////////////////////////////////////////////////////
        this.getChildren()
                .add(new Brick(300, 550, 100, 100));
        this.getChildren()
                .add(new Brick(400, 550, 100, 100));
        this.getChildren()
                .add(new Brick(500, 550, 100, 100));
        this.getChildren()
                .add(new Brick(600, 550, 100, 100));
        this.getChildren()
                .add(new Brick(700, 550, 100, 100));
        this.getChildren()
                .add(new Brick(800, 550, 100, 100));

        //******************************************************************************//
        this.getChildren()
                .add(new Brick(scw / 2 - 50, 810, 100, 100));

        this.getChildren()
                .add(new Brick(scw / 2 - 50, 0, 100, 100));
        this.getChildren()
                .add(T);

        this.getChildren()
                .add(T2);

        T.setRespawnx((int) (scw / 2 - T.getWidth() / 2));
        T.setRespawny(680);
        T.setX(T.getRespawnx());
        T.setY(T.getRespawny());
        T.setRotate(0);
        T.setDir(direction.UP);
        T.setS("player 2 wins");
        T2.setRespawnx((int) (scw / 2 - T.getWidth() / 2));
        T2.setRespawny(120);
        T2.setX(T2.getRespawnx());
        T2.setY(T2.getRespawny());
        T2.setRotate(180);
        T2.setDir(direction.DOWN);
        T2.setS("player 1 wins");
    }

    public void add2() throws IOException {
        Rectangl rectang = new Rectangl(scw, sch);
        this.getChildren().add(rectang);

        //*********************************************************************//
        this.getChildren()
                .add(new Brick(0, 0, 100, 100));
        this.getChildren()
                .add(new Brick(0, 100, 100, 100));

        this.getChildren()
                .add(new Brick(0, 200, 100, 100));
        this.getChildren()
                .add(new Brick(0, 300, 100, 100));
        this.getChildren()
                .add(new Brick(0, 400, 100, 100));
        this.getChildren()
                .add(new Brick(0, 500, 100, 100));
        this.getChildren()
                .add(new Brick(0, 600, 100, 100));
        this.getChildren()
                .add(new Brick(0, 700, 100, 100));
        this.getChildren()
                .add(new Brick(0, 800, 100, 100));
        this.getChildren()
                .add(new Brick(0, 900, 100, 100));

        //////////////////////////////////////////////////
        this.getChildren()
                .add(new Brick(scw - 90, 0, 100, 100));
        this.getChildren()
                .add(new Brick(scw - 90, 100, 100, 100));

        this.getChildren()
                .add(new Brick(scw - 90, 200, 100, 100));
        this.getChildren()
                .add(new Brick(scw - 90, 300, 100, 100));
        this.getChildren()
                .add(new Brick(scw - 90, 400, 100, 100));
        this.getChildren()
                .add(new Brick(scw - 90, 500, 100, 100));
        this.getChildren()
                .add(new Brick(scw - 90, 600, 100, 100));
        this.getChildren()
                .add(new Brick(scw - 90, 700, 100, 100));
        this.getChildren()
                .add(new Brick(scw - 90, 800, 100, 100));
        this.getChildren()
                .add(new Brick(scw - 90, 900, 100, 100));
//////////////////////////////////////////////////////////////
        this.getChildren()
                .add(new Brick(200, 100, 100, 100));
        this.getChildren()
                .add(new Brick(200, 200, 100, 100));
        this.getChildren()
                .add(new Brick(200, 300, 100, 100));
        this.getChildren()
                .add(new Brick(200, 400, 100, 100));
        this.getChildren()
                .add(new Brick(200, 500, 100, 100));
        this.getChildren()
                .add(new Brick(200, 600, 100, 100));

//////////////////////////////////////////////////////////////
        this.getChildren()
                .add(new Brick(500, 350, 100, 100));
        this.getChildren()
                .add(new Brick(600, 350, 100, 100));
        this.getChildren()
                .add(new Brick(700, 350, 100, 100));
        this.getChildren()
                .add(new Brick(400, 350, 100, 100));

        //////////////////////////////////////////////////////////////
        this.getChildren()
                .add(new Brick(900, 100, 100, 100));
        this.getChildren()
                .add(new Brick(900, 200, 100, 100));
        this.getChildren()
                .add(new Brick(900, 300, 100, 100));
        this.getChildren()
                .add(new Brick(900, 400, 100, 100));
        this.getChildren()
                .add(new Brick(900, 500, 100, 100));
        this.getChildren()
                .add(new Brick(900, 600, 100, 100));

        //******************************************************************************//
        this.getChildren()
                .add(new Brick(scw / 2 - 50, 810, 100, 100));

        this.getChildren()
                .add(new Brick(scw / 2 - 50, 0, 100, 100));
        this.getChildren()
                .add(T);

        this.getChildren()
                .add(T2);

        T.setRespawnx((int) (scw / 2 - T.getWidth() / 2));
        T.setRespawny(680);
        T.setX(T.getRespawnx());
        T.setY(T.getRespawny());
        T.setRotate(0);
        T.setDir(direction.UP);
        T.setS("player 2 wins");
        T2.setRespawnx((int) (scw / 2 - T.getWidth() / 2));
        T2.setRespawny(120);
        T2.setX(T2.getRespawnx());
        T2.setY(T2.getRespawny());
        T2.setRotate(180);
        T2.setDir(direction.DOWN);
        T2.setS("player 1 wins");
    }

    public void newgame() {
        respawn();

        this.getChildren().removeAll(rec, b, b2, ll);
        if (this.pause == true) {
            this.getChildren().remove(r);
        }
        T.setRoundlost(0);
        T2.setRoundlost(0);
        round.setText("player 1: " + (T.getRoundlost() + "     player 2 :" + T2.getRoundlost()));

        this.pause = false;
        this.requestFocus();
    }

    public void gamepause() {

        this.getChildren().addAll(rec, b, b2, r);
        this.pause = true;

    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public void resume() {
        this.setPause(false);
        this.getChildren().removeAll(rec, b, b2, r);
        this.requestFocus();

    }

}

public class Demo1 extends Application {

    int scw = map.scw;
    int sch = map.sch;
    static Stage window;
    static Scene sc, sc1, sc2;

    public static void setScene() {
        window.setScene(sc);

    }

    public static void setScene1() {
        window.setScene(sc2);
    }

    public static void setScene2() {
        window.setScene(sc1);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;

        map2 m2 = new map2();

        sc1 = new Scene(m2, scw, sch);



        map m3 = new map(2);

        sc2 = new Scene(m3, scw, sch);
        m3.requestFocus();

        m2.requestFocus();
        map m = new map(1);

        sc = new Scene(m, scw, sch);
        m.requestFocus();

        window.setScene(sc1);
        window.setTitle("World Of Tanks");
        window.setResizable(false);

        primaryStage.show();
    }

}
