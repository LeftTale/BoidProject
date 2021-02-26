import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.Vector;

public class Boid
{
    /*
    This class contains a constructor for a basic boid.
    It also contains variables related to the boid as well as methods
    which control the boids movement and behaviour.
     */
    private Color boidColor;
    private Path2D boidShape;
    double boidX1 ,boidX2,boidX3;
    double boidY1, boidY2,boidY3;
    double[] coord1 = new double[2];
    double[] coord2 = new double[2];
    double[] coord3 = new double[2];

    private int wide;
    private int tall;
    private double movement = 5.0;

    private double centerX;
    private double centerY;

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

    public void GenerateBoid()
    {
        //Generates 2 random numbers within the size of the screen
        int posX = (int) (Math.random() * wide);
        int posY = (int) (Math.random() * tall);

        //Sets one point of the triangle as the base
        coord2[0] = posX;
        //It then generates the triangle based on this
        coord1[0] = posX - 10;
        coord3[0] = posX + 10;

        //It then does the same for the Y Coords
        coord2[1] = posY;

        coord1[1] = posY + 25;
        coord3[1] = posY + 25;

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
    }
    public double getCenterX() {
        return centerX;
    }
    public double getCenterY() {
        return centerY;
    }





    Direction dirVert = Direction.UP;
    Direction dirHorz = Direction.LEFT;
    public void MoveBoidVertical()
    {
        if(coord2[1] < 0)
        {
            dirVert = Direction.DOWN;
        }
        else if(coord2[1] > tall)
        {
            dirVert = Direction.UP;
        }

        if(dirVert == Direction.UP)
        {
            coord1[1] = coord1[1] - movement;
            coord2[1] = coord2[1] - movement;
            coord3[1] = coord3[1] - movement;
            //TODO Implement Fixed Time Step Update
        }
        else
        {
            coord1[1] = coord1[1] + movement;
            coord2[1] = coord2[1] + movement;
            coord3[1] = coord3[1] + movement;
            //TODO Implement Fixed Time Step Update
        }
        boidShape = new Path2D.Double();
        boidShape.moveTo(coord2[0],coord2[1]);
        boidShape.lineTo(coord1[0],coord1[1]);
        boidShape.lineTo(coord3[0],coord3[1]);
        boidShape.closePath();
    }

    public void MoveBoidHorizontal()
    {
        if (coord1[0] < 0)
        {
            dirHorz = Direction.RIGHT;
        }
        else if (coord3[0] > wide)
        {
            dirHorz = Direction.LEFT;
        }

        if(dirHorz == Direction.LEFT)
        {
            coord1[0] = coord1[0] - movement;
            coord2[0] = coord2[0] - movement;
            coord3[0] = coord3[0] - movement;
            //TODO Implement Fixed Time Step Update
        }
        else
        {
            coord1[0] = coord1[0] + movement;
            coord2[0] = coord2[0] + movement;
            coord3[0] = coord3[0] + movement;
            //TODO Implement Fixed Time Step Update
        }
        boidShape = new Path2D.Double();
        boidShape.moveTo(coord2[0],coord2[1]);
        boidShape.lineTo(coord1[0],coord1[1]);
        boidShape.lineTo(coord3[0],coord3[1]);
        boidShape.closePath();
    }

}

