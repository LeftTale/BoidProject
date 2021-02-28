import java.awt.*;
import java.awt.geom.Path2D;

public class Boid
{
    /*
    This class contains a constructor for a basic boid.
    It also contains variables related to the boid as well as methods
    which control the boids movement and behaviour.
     */
    private Color boidColor;
    private Path2D boidShape;
    double[] coord1 = new double[2];
    double[] coord2 = new double[2];
    double[] coord3 = new double[2];


    private int wide;
    private int tall;
    double speed = 1;

    private double centerX;
    private double centerY;

    Direction dir = Direction.STRAIGHT;


    public Boid(int wide, int tall, Color color)
    {
        this.boidColor = color;
        this.wide = wide;
        this.tall = tall;
        GenerateBoid();
        //boidShape = new Polygon(new int[]{boidX1, boidX2,boidX3}, new int[]{boidY1,boidY2,boidY3},3);
    }

    public Path2D getBoidShape() {
        return boidShape;
    }

    public Color getBoidColor()
    {
        return boidColor;
    }
    public void setBoidColor(Color boidColor){this.boidColor = boidColor;}

    public void GenerateBoid()
    {
        //Generates 2 random numbers within the size of the screen
        int posX = (int) ((Math.random() * (wide - 100)) + 100);
        int posY = (int) ((Math.random() * (tall - 100)) + 100);

        //Sets one point of the triangle as the base
        coord2[0] = posX;
        //It then generates the triangle based on this
        coord1[0] = posX - 10;
        coord3[0] = posX + 10;

        //It then does the same for the Y Coords
        coord2[1] = posY;

        coord1[1] = posY + 25;
        coord3[1] = posY + 25;

        double randomRot = 10 * Math.random();
        RotateBoid(randomRot);

        RedefineBoid();
    }

    void RedefineBoid()
    {
        boidShape = new Path2D.Double();
        boidShape.moveTo(coord2[0],coord2[1]);
        boidShape.lineTo(coord1[0],coord1[1]);
        boidShape.lineTo(coord3[0],coord3[1]);
        boidShape.closePath();
    }

    void centers()
    {
        centerX = (coord1[0] + coord2[0] + coord3[0]) /3;
        centerY = (coord1[1] + coord2[1] + coord3[1]) /3;

        System.out.println(centerX + " " + centerY);
    }

    void BoundaryCheck()
    {
        double boundaryLeft = 100;
        double boundaryRight = wide - 100;
        double boundaryTop = 100;
        double boundaryBottom = tall - 100;
        if(coord2[0] <= boundaryLeft || coord2[0] >= boundaryRight)
        {
            RotateBoid(0.1);
            for (int i = 0; i < 3; i++)
            {
                FindForward(speed);
            }
        }
        else if(coord2[1] <= boundaryTop || coord2[1] >= boundaryBottom)
        {
            RotateBoid(0.1);
            for (int i = 0; i < 3; i++)
            {
                FindForward(speed);
            }
        }
        else
        {
            for (int i = 0; i < 3; i++)
            {
                FindForward(speed);
            }
        }
    }

    public void FindForward(double speed)
    {
        centers();
        double[] forwardV = new double[2];
        forwardV[0] = coord2[0] - centerX;
        forwardV[1] = coord2[1] - centerY;

        double mag = Math.sqrt((forwardV[0] * forwardV[0]) + (forwardV[1] * forwardV[1]));

        double forwardX = forwardV[0]/mag;
        double forwardY = forwardV[1]/mag;

        MoveBoidForward(forwardX,forwardY,speed);

    }

    void MoveBoidForward(double forwardX, double forwardY,double speed)
    {
        coord1[0] = coord1[0] + (forwardX * speed);
        coord2[0] = coord2[0] + (forwardX * speed);
        coord3[0] = coord3[0] + (forwardX * speed);

        coord1[1] = coord1[1] + (forwardY * speed);
        coord2[1] = coord2[1] + (forwardY * speed);
        coord3[1] = coord3[1] + (forwardY * speed);

        RedefineBoid();
    }

    void RotateBoid(double angle)
    {
        centers();

        double tempX;
        double tempY;

        Rotate(angle, coord1);
        Rotate(angle, coord2);
        Rotate(angle, coord3);

        RedefineBoid();
    }

    private void Rotate(double rotation, double[] coord)
    {
        double tempX;
        double tempY;
        tempX = coord[0] - centerX;
        tempY = coord[1] - centerY;

        coord[0] = (tempX * Math.cos(rotation)) - (tempY * Math.sin(rotation)) + centerX;
        coord[1] = (tempY * Math.cos(rotation)) + (tempX * Math.sin(rotation)) + centerY;
    }





}

