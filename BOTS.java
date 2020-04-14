
import java.awt.*;

public class BOTS
{
    public static Color[] colors = {Color.BLUE, Color.RED};
    public static int counter = 1;
    private volatile double x;
    private volatile double y;
    private int radius;
    private int color;
    private int id;
    private volatile int direction = 5;
    private volatile int n = 0;
    public BOTS(double x, double y, int radius, int color)
    {
        this.id = counter;
        counter++;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public int getRadius()
    {
        return radius;
    }

    public void setRadius(int radius)
    {
        this.radius = radius;
    }

    public int getColor()
    {
        return color;
    }

    public void setColor(int color)
    {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
