import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;

public class Boid
{
    /*
    This class contains a constructor for a basic boid.
    It also contains variables related to the boid as well as methods
    which control the boids movement and behaviour.
     */
    private Color boidColor;
    private double speed = 3;
    String boidName;

    Shape lineOfSight;
    private Path2D boidShape;
    @SuppressWarnings("CanBeFinal")
    double[] coord1 = new double[2];
    @SuppressWarnings("CanBeFinal")
    double[] coord2 = new double[2];
    @SuppressWarnings("CanBeFinal")
    double[] coord3 = new double[2];

    @SuppressWarnings("CanBeFinal")
    double[] forwardDir = new double[2];
    double[] targetDir = new double[2];

    private final int wide;
    private final int tall;

    private double centerX;
    private double centerY;
    double sightEndCordX;
    double sightEndCordY;

    Direction moveState = Direction.STRAIGHT;
    Direction previousState;

    public Boid(int wide, int tall, Color color, String boidName)
    {
        this.boidColor = color;
        this.wide = wide;
        this.tall = tall;
        this.boidName = boidName;
        GenerateBoid();
    }

    public Path2D getBoidShape() {
        return boidShape;
    }
    public Color getBoidColor()
    {
        return boidColor;
    }
    public double[] getForwardDir()
    {
        return forwardDir;
    }
    public double[] getCoord2()
    {
        return coord2;
    }
    public Shape getLineOfSight() {
        return lineOfSight;
    }
    public String getBoidName() {
        return boidName;
    }
    public double getCenterX() {
        return centerX;
    }
    public double getCenterY() {
        return centerY;
    }
    public Rectangle getBounds() {
        return new Rectangle((int)centerX -10, (int)centerY-20, 25, 40);
    }
    public Direction getMoveState() {
        return moveState;
    }
    public Direction getPreviousState() {
        return previousState;
    }

    public void setBoidColor(Color boidColor){this.boidColor = boidColor;}
    public void setMoveState(Direction moveState) {
        this.moveState = moveState;
    }
    public void setPreviousState(Direction previousState) {
        this.previousState = previousState;
    }
    public void setTargetDir(double[] targetDir) {
        this.targetDir = targetDir;
    }

    public void GenerateBoid()
    {
        //Generates 2 random numbers within the size of the screen
        int posX = (int) ((Math.random() * (wide - 400)) +100);
        int posY = (int) ((Math.random() * (tall - 300)) +100);

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
        CallRotate(randomRot);

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
    }

    void BoidSteering()
    {
        if(moveState.equals(Direction.STARBOARD_WALL))
        {
            CallRotate(.1);
            MoveBoidForward(forwardDir[0],forwardDir[1],speed);

        }
        else if(moveState.equals(Direction.STRAIGHT))
        {
            MoveBoidForward(forwardDir[0],forwardDir[1],speed);
        }
    }
    void BoidReroute()
    {
        double tempX;
        double tempY;
        tempX =  sightEndCordX - coord2[0];
        tempY = sightEndCordY - coord2[1];

        sightEndCordX = (tempX * Math.cos(Math.random()*5)) - (tempY * Math.sin(Math.random()*5)) + coord2[0];
        sightEndCordY = (tempY * Math.cos(Math.random()*5)) + (tempX * Math.sin(Math.random()*5)) + coord2[1];

        lineOfSight = new Line2D.Double(coord2[0],coord2[1],sightEndCordX,sightEndCordY);
    }

    /*
    Calculates the forward direction of the boid and then normalises it to a length of one
     */
    public void FindForward()
    {
        centers();
        double[] forwardV = new double[2];
        forwardV[0] = coord2[0] - centerX;
        forwardV[1] = coord2[1] - centerY;

        double mag = Math.sqrt((forwardV[0] * forwardV[0]) + (forwardV[1] * forwardV[1]));

        forwardDir[0] = forwardV[0]/mag;
        forwardDir[1] = forwardV[1]/mag;
    }

    //Find and return the custom Direction betweeen two points
    public double[] FindForward(double x1,double y1,double x2,double y2)
    {
        double[] forwardV = new double[2];
        forwardV[0] = x1 - x2;
        forwardV[1] = y1 - y2;

        double mag = Math.sqrt((forwardV[0] * forwardV[0]) + (forwardV[1] * forwardV[1]));

        double[] customDir = new double[2];
        customDir[0] = forwardV[0]/mag;
        customDir[1] = forwardV[1]/mag;

        return customDir;
    }

    void MoveBoidForward(double forwardX, double forwardY, double speed)
    {
        coord1[0] = coord1[0] + (forwardX * speed);
        coord2[0] = coord2[0] + (forwardX * speed);
        coord3[0] = coord3[0] + (forwardX * speed);

        coord1[1] = coord1[1] + (forwardY * speed);
        coord2[1] = coord2[1] + (forwardY * speed);
        coord3[1] = coord3[1] + (forwardY * speed);

        RedefineBoid();
    }

    void CallRotate(double angle)
    {
        centers();

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

    public void BoidSight()
    {

        sightEndCordX = coord2[0] + ( 80 * forwardDir[0]);
        sightEndCordY = coord2[1] + ( 80 * forwardDir[1]);
        lineOfSight = new Line2D.Double(coord2[0],coord2[1],sightEndCordX,sightEndCordY);
    }
}

