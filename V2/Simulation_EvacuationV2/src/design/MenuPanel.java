package design;

import java.awt.*;
import javax.swing.JPanel;

public class MenuPanel extends JPanel
{
    private Image background;
 
    public MenuPanel(Image background)
    {
        this.background = background;
        setLayout( new BorderLayout() );
    }
 
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
 
        g.drawImage(background, 0, 0, null); // image full size
        //g.drawImage(background, 0, 0, getWidth(), getHeight(), null); // image scaled
    }
 
    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(background.getWidth(this), background.getHeight(this));
    }
}