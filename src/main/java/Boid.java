import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

public class Boid
{
    /*
    This class contains a constructor for a basic boid.
    It also contains variables related to the boid as well as methods
    which control the boids movement and behaviour.
     */

    private Color boidColor;
    int boidX1 ,boidX2,boidX3;
    int boidY1, boidY2,boidY3;
    private int wide;
    private int tall;
    private Shape boidShape;
    private int movement =2;


    public Boid(int wide, int tall, Color color)
    {
        this.boidColor = color;
        this.wide = wide;
        this.tall = tall;
        GenerateBoid();
        boidShape = new Polygon(new int[]{boidX1, boidX2,boidX3}, new int[]{boidY1,boidY2,boidY3},3);
    }


    public Shape getBoidShape() {
        return (Shape) boidShape;
    }
    public Color getBoidColor()
    {
        return boidColor;
    }
    public double centerX;
    public double centerY;

    public void GenerateBoid()
    {
        //Generates 2 random numbers within the size of the screen
        int posX = (int) (Math.random() * wide);
        int posY = (int) (Math.random() * tall);

        //Sets one point of the triangle as the base
        boidX2 = posX;
        //It then generates the triangle based on this
        boidX1 = posX - 10;
        boidX3 = posX + 10;

        //It then does the same for the Y Coords
        boidY2 = posY;

        boidY1 = posY + 25;
        boidY3= posY + 25;
    }


    Direction dirVert = Direction.UP;
    Direction dirHorz = Direction.LEFT;
    public void MoveBoidVertical()
    {
        if(boidY2 < 0) {
            dirVert = Direction.DOWN;
            int temp = boidY2;
            boidY2 = boidY1;
            boidY1 = temp;
            boidY3 = temp;
        }
        else if(boidY2 > tall)
        {
            dirVert = Direction.UP;
            int temp = boidY2;
            boidY2 = boidY1;
            boidY1 = temp;
            boidY3 = temp;
        }


        if(dirVert == Direction.UP)
        {
            boidY1 = boidY1 - movement;
            boidY2 = boidY2 - movement;
            boidY3 = boidY3 - movement;
            //TODO Implement Fixed Time Step Update
        }
        else
        {
            boidY1 = boidY1 + movement;
            boidY2 = boidY2 + movement;
            boidY3 = boidY3 + movement;
            //TODO Implement Fixed Time Step Update
        }
        boidShape = new Polygon(new int[]{boidX1, boidX2, boidX3}, new int[]{boidY1, boidY2, boidY3}, 3);
    }

    public void MoveBoidHorizontal()
    {
        if (boidX1 < 0)
        {
            dirHorz = Direction.RIGHT;

        }
        else if (boidX3 > wide)
        {
            dirHorz = Direction.LEFT;
        }

        if(dirHorz == Direction.LEFT)
        {
            boidX1 = boidX1 - movement;
            boidX2 = boidX2 - movement;
            boidX3 = boidX3 - movement;
            //TODO Implement Fixed Time Step Update
        }
        else
        {
            boidX1 = boidX1 + movement;
            boidX2 = boidX2 + movement;
            boidX3 = boidX3 + movement;
            //TODO Implement Fixed Time Step Update
        }
        boidShape = new Polygon(new int[]{boidX1, boidX2, boidX3}, new int[]{boidY1, boidY2, boidY3}, 3);
    }

    void centers()
    {
        centerX = (boidX1 + boidX2 + boidX3) /3;
        centerY = (boidY1 + boidY2 + boidY3) /3;
    }
    public double getCenterX() {
        return centerX;
    }
    public double getCenterY() {
        return centerY;
    }

//    Xnew = Xold x cosθ – Yold x sinθ
//    Ynew = Xold x sinθ + Yold x cosθ
    double rotation;

    public void addRotation() {
        this.rotation = rotation + 0.01;
    }

    void RotateLeft()
    {
        boidX1 = (int) ((boidX1 * Math.cos(rotation)) - (boidY1 * Math.sin(rotation)));
        boidY1 = (int) ((boidX1 * Math.sin(rotation)) + (boidY1 * Math.cos(rotation)));

        boidX2 = (int) ((boidX2 * Math.cos(rotation)) - (boidY2 * Math.sin(rotation)));
        boidY2 = (int) ((boidX2 * Math.sin(rotation)) + (boidY2 * Math.cos(rotation)));

        boidX3 = (int) ((boidX3 * Math.cos(rotation)) - (boidY3 * Math.sin(rotation)));
        boidY3 = (int) ((boidX3 * Math.sin(rotation)) + (boidY3 * Math.cos(rotation)));

        boidShape = new Polygon(new int[]{boidX1, boidX2, boidX3}, new int[]{boidY1, boidY2, boidY3}, 3);
    }

//    public void turnLeft()
//    {
//            boidX2 = boidX2 - 1;
//            boidY2 = boidY2 - 1;
//
//            boidX1 = boidX1 + 1;
//            boidY1 = boidY1 + 1;
//
//            boidX3 = boidX3 + 1;
//            boidY3 = boidY3 + 1;
//
//        boidShape = new Polygon(new int[]{boidX1, boidX2, boidX3}, new int[]{boidY1, boidY2, boidY3}, 3);
//    }
//
//    public void turnRight()
//    {
//        if(boidY2 < boidY1)
//        {
//            boidX2 = boidX2 + 1;
//            boidY2 = boidY2 + 1;
//
//            boidX1 = boidX1 - 1;
//            boidY1 = boidY1 - 1;
//
//            boidX3 = boidX3 - 1;
//            boidY3 = boidY3 - 1;
//            AffineTransform affineTransform = new AffineTransform();
//            affineTransform.translate(1,1);
//        }
//        else
//        {
//            boidX2 = boidX2 - 1;
//            boidY2 = boidY2 - 1;
//
//            boidX1 = boidX1 - 1;
//            boidY1 = boidY1 - 1;
//
//            boidX3 = boidX3 - 1;
//            boidY3 = boidY3 - 1;
//        }
//        boidShape = new Polygon(new int[]{boidX1, boidX2, boidX3}, new int[]{boidY1, boidY2, boidY3}, 3);
//    }

    void printcoords()
    {
        System.out.println("X1:" + boidX1 + " Y1:" + boidY1);
        System.out.println("X2:" + boidX2 + " Y2:" + boidY2);
        System.out.println("X3:" + boidX3 + " Y3:" + boidY3);
    }
}
