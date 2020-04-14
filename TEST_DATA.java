
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TEST_DATA
{
    static int WAIT = 5000;
    static int TICK = 20;
    static int ticks = 1000000;
    static int counter = 0;
    static Timer timer;
    static Random random;
    static int direction_seconds = 5;

    public static void populate()
    {
        random = new Random();

    }

    public static void testPlayer()
    {
        double delta = 0.70710678118;
        double speed = DATA.speed;
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask()
        {

            @Override
            public void run()
            {
                counter++;
                if(counter < ticks)
                {
                    randomize_direction();
                    switch(DATA.direction)
                    {
                        case 1:
                            DATA.x -= delta * speed;
                            DATA.y -= delta * speed;
                            break;
                        case 2:
                            DATA.y -= speed;
                            break;
                        case 3:
                            DATA.x += delta * speed;
                            DATA.y -= delta * speed;
                            break;
                        case 4:
                            DATA.x -= speed;
                            break;
                        case 5:
                            break;
                        case 6:
                            DATA.x += speed;
                            break;
                        case 7:
                            DATA.x -= delta * speed;
                            DATA.y += delta * speed;
                            break;
                        case 8:
                            DATA.y += speed;
                            break;
                        case 9:
                            DATA.x += delta * speed;
                            DATA.y += delta * speed;
                            break;
                    }

                    for(BOTS b : DATA.bots)
                    {
                        switch(b.getDirection())
                        {
                            case 1:
                                if(b.getX() - delta * speed > 0)
                                {
                                    b.setX(b.getX() - delta * speed);
                                }
                                else
                                {
                                    b.setDirection(9);
                                }

                                if(b.getY() - delta * speed > 0)
                                {
                                    b.setY(b.getY() - delta * speed);
                                }
                                else
                                {
                                    b.setDirection(9);
                                }

                                break;
                            case 2:
                                if(b.getY() - speed > 0)
                                {
                                    b.setY(b.getY() - speed);
                                }
                                else
                                {
                                    b.setDirection(8);
                                }

                                break;
                            case 3:
                                if(b.getX() + delta * speed < DATA.width)
                                {
                                    b.setX(b.getX() + delta * speed);
                                }
                                else
                                {
                                    b.setDirection(7);
                                }

                                if(b.getY() - delta * speed > 0)
                                {
                                    b.setY(b.getY() - delta * speed);
                                }
                                else
                                {
                                    b.setDirection(7);
                                }

                                break;
                            case 4:
                                if(b.getX() - speed > 0)
                                {
                                    b.setX(b.getX() - speed);
                                }
                                else
                                {
                                    b.setDirection(6);
                                }

                                break;
                            case 5:
                                break;
                            case 6:
                                if(b.getX() + speed < DATA.width)
                                {
                                    b.setY(b.getX() + speed);
                                }
                                else
                                {
                                    b.setDirection(4);
                                }

                                break;
                            case 7:
                                if(b.getX() - delta * speed > 0)
                                {
                                    b.setX(b.getX() - delta * speed);
                                }
                                else
                                {
                                    b.setDirection(3);
                                }

                                if(b.getY() + delta * speed < DATA.height)
                                {
                                    b.setY(b.getY() + delta * speed);
                                }
                                else
                                {
                                    b.setDirection(3);
                                }

                                break;
                            case 8:
                                if(b.getY() + speed < DATA.height)
                                {
                                    b.setY(b.getY() + speed);
                                }
                                else
                                {
                                    b.setDirection(2);
                                }

                                break;
                            case 9:
                                if(b.getX() + delta * speed < DATA.width)
                                {
                                    b.setX(b.getX() + delta * speed);
                                }
                                else
                                {
                                    b.setDirection(1);
                                }

                                if(b.getY() + delta * speed < DATA.height)
                                {
                                    b.setY(b.getY() + delta * speed);
                                }
                                else
                                {
                                    b.setDirection(1);
                                }

                                break;
                        }
                    }
                }
                else
                {
                    timer.cancel();
                    timer.purge();
                }
            }
        }, WAIT, TICK);
    }

    public static void add_bots(int n)
    {
        for(int i = 0; i < n; i++)
        {
            DATA.bots.add(new BOTS(random.nextInt(DATA.width), random.nextInt(DATA.height), DATA.radius, 1));
        }
    }

    public static void randomize_direction()
    {
        int temp = (int) (direction_seconds * 1000 / TICK);
        if(counter % temp == 0)
        {
            for(BOTS b : DATA.bots)
            {
                b.setDirection(random.nextInt(9) + 1);
            }
        }
    }
}
