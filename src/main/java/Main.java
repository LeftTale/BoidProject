import java.awt.*;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        ScreenSpace space = new ScreenSpace();
        space.init();
        ArrayList<Boid> activeBoids = new ArrayList<>();

        for (int i = 0; i < 1; i++)
        {
            activeBoids.add(new Boid(space.getScreenSizeX(),space.getScreenSizeY(),Color.WHITE));
        }

        space.setActiveBoid(activeBoids);

        FrameTimer timer = new FrameTimer(space,activeBoids);
        timer.run();
    }
}
