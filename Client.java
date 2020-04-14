
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

public class Client extends JFrame
{
    private int last_direction = 5;
    private static final int PORT = 7788;
    private static InetAddress SERVER_ADDRESS = null;
    static
    {
        try
        {
            SERVER_ADDRESS = InetAddress.getByName("127.0.0.1");
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
    }
    private Timer timer;
    private DatagramSocket socket;

    volatile boolean isW = false;
    volatile boolean isA = false;
    volatile boolean isS = false;
    volatile boolean isD = false;


    public Client() throws SocketException
    {
        initUI();
        initDrawTimer();
    }

    private void initUI() throws SocketException
    {
        socket = new DatagramSocket();
        JPanel canvas = new Canvas();
        canvas.setDoubleBuffered(true);
        add(canvas);
        setSize(DATA.width, DATA.height);
        setResizable(false);
        setTitle("ONLINE GAME");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {

            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyChar() == 'w') isW = true;
                if(e.getKeyChar() == 'a') isA = true;
                if(e.getKeyChar() == 's') isS = true;
                if(e.getKeyChar() == 'd') isD = true;
                try
                {
                    updateDirection();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                if(e.getKeyChar() == 'w') isW = false;
                if(e.getKeyChar() == 'a') isA = false;
                if(e.getKeyChar() == 's') isS = false;
                if(e.getKeyChar() == 'd') isD = false;
                try
                {
                    updateDirection();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void updateDirection() throws IOException
    {
        int direction = 5;

        if(isW && isA && !isS && !isD) direction = 1;
        if(isW && !isA && !isS && !isD) direction = 2;
        if(isW && !isA && !isS && isD) direction = 3;
        if(!isW && isA && !isS && !isD) direction = 4;
        if(!isW && !isA && !isS && !isD) direction = 5;
        if(!isW && !isA && !isS && isD) direction = 6;
        if(!isW && isA && isS && !isD) direction = 7;
        if(!isW && !isA && isS && !isD) direction = 8;
        if(!isW && !isA && isS && isD) direction = 9;

        DATA.direction = direction;
        if(last_direction != DATA.direction)
        {
            last_direction = DATA.direction;
            String result = String.valueOf(last_direction);
            DatagramPacket packet = new DatagramPacket(result.getBytes(), result.length(), SERVER_ADDRESS, PORT);
            socket.send(packet);
        }
    }

    public void initDrawTimer()
    {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {

            @Override
            public void run()
            {
                repaint();
            }
        }, 0, 10);
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            Client c = null;
            try
            {
                c = new Client();
            }
            catch (SocketException e)
            {
                e.printStackTrace();
            }
            TEST_DATA.populate();
            TEST_DATA.add_bots(10);
            TEST_DATA.testPlayer();
            c.setVisible(true);
        });
    }
}
