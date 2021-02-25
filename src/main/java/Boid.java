import java.awt.*;

public class Boid
{

    /*
    This class contains a constructor for a basic boid.
    It also contains variables related to the boid as well as methods
    which control the boids movement and behaviour.

     */

    private Color boidColor = Color.white;
    int boidX1 ,boidX2,boidX3;
    int boidY1, boidY2,boidY3;
    private float boidSpeed = 3;
    private float boidSize;//Not implemented
    private int wide;
    private int tall;
    private Shape boidShape;


    public Boid()
    {
        GenerateBoid();
    }

    public Boid(int wide, int tall)
    {
        this.wide = wide;
        this.tall = tall;
        GenerateBoid();
        boidShape = new Polygon(new int[]{boidX1, boidX2,boidX3}, new int[]{boidY1,boidY2,boidY3},3);
    }



    public Boid(Color boidColor, float boidSpeed)
    {

    }

    public Shape getBoidShape() {
        return (Shape) boidShape;
    }

    public Color getBoidColor() {
        return boidColor;
    }

    public void GenerateBoid()
    {
        //Generates 2 random numbers within the size of the screen
        int posX = (int) (Math.random() * wide);
        int posY = (int) (Math.random() * tall);

        //Sets one point of the triangle as the base
        boidX2 = posX;
        //It then generates the triangle based on this
        boidX1 = posX - 5;
        boidX3 = posX + 5;

        //It then does the same for the Y Coords
        boidY2 = posY;

        boidY1 = posY + 10;
        boidY3= posY + 10;
    }

    public void MoveBoidForward()
    {
        boidY1 = boidY1 +2;
        boidY2 = boidY2 -5;
        boidY3 = boidY3 +2;
        boidShape = new Polygon(new int[]{boidX1, boidX2,boidX3}, new int[]{boidY1,boidY2,boidY3},3);
        //TODO Implement Fixed Time Step Update
    }
}
