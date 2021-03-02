import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
        File boidNames = new File("C:\\Users\\caola\\OneDrive\\Documents\\Java Projects\\BoidProject\\src\\main\\resources\\BoidNames.txt");

            Scanner namer = new Scanner(boidNames);

        String chosenName;

        BoidGraphics space = new BoidGraphics();
        ArrayList<Boid> activeBoids = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        {
            int tempRand = (int) (Math.random() * 2000);
            for(int j = 0; j < tempRand; j++)
            {
                namer.next();
            }
            chosenName = namer.next();
            activeBoids.add(new Boid(space.getScreenSizeX(),space.getScreenSizeY(),Color.WHITE,chosenName));
        }


        space.setActiveBoid(activeBoids);
        space.init();

        FrameTimer timer = new FrameTimer(space,activeBoids);


        try
        {
            timer.start();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
