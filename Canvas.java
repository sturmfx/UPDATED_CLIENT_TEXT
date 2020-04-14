
import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel
{
    private void doDraw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        for(WALL w : DATA.walls)
        {
            g2d.setColor(WALL.colors[w.getColor()]);
            g2d.drawLine(w.getX1(), w.getY1(), w.getX2(), w.getY2());
        }

        for(BOTS b : DATA.bots)
        {
            g2d.setColor(BOTS.colors[b.getColor()]);
            g2d.fillOval((int) b.getX() - b.getRadius(), (int) b.getY() - b.getRadius(), 2 * b.getRadius(), 2 * b.getRadius());
            g2d.drawString("BOT " + b.getId(), (int) b.getX() - b.getRadius(), (int) b.getY() - b.getRadius());
        }

        g2d.setColor(Color.BLUE);
        g2d.fillOval((int) DATA.x - DATA.radius, (int) DATA.y - DATA.radius, 2 * DATA.radius, 2 * DATA.radius);
        g2d.drawString(DATA.username, (int) DATA.x - DATA.radius, (int) DATA.y - DATA.radius);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        doDraw(g);
    }
}
