import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.event.MouseAdapter;


public class GUI extends JPanel {
    Playground  sf = new Playground(12, 12);
    final int sqrMnt = 12;
    public GUI() {
        setLayout(new BorderLayout()); // Set BorderLayout as the layout manager
        add(new RedBox(), BorderLayout.CENTER); // Add the red box to the center of the panel
        generateShips(5,4,3,3,2,sf);
        sf.print();
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
            int mouseX = me.getX();
            int mouseY = me.getY();
            int windowWidth = me.getComponent().getWidth();
            int windowHeight = me.getComponent().getHeight();
            boxPressed(mouseX, mouseY, windowWidth, windowHeight);
        }
        } );
    }
    
    public void generateShips(int size1, int size2, int size3, int size4, int size5, Playground field) {
        sf.resetShipCount();
        field.ship(size1);
        field.ship(size2);
        field.ship(size3);
        field.ship(size4);
        field.ship(size5);
    }
    

    private class RedBox extends JComponent {
        private boolean drawGrid = true;
        private Image win;
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int boxSize = 600 / sqrMnt;
            g.setColor(Color.BLACK);
            for (int i = 0; i < sqrMnt; i++) {
                g.fillRect(0, i*boxSize, boxSize, boxSize);
                g.fillRect((sqrMnt-1)*boxSize, i*boxSize, boxSize, boxSize);
                g.fillRect(i*boxSize, 0, boxSize, boxSize);
                g.fillRect(i*boxSize, (sqrMnt-1)*boxSize, boxSize, boxSize);
            }
            try {
                win = new ImageIcon("win.png").getImage();
            } catch (Exception e) {
            }
            for (int o = 1; o < 11; o++){
                for (int i = 1; i < 11; i++) {
                    switch (sf.getElementAt(o,i)) {
                        case 0:
                        case 1:
                        case 2:
                        case 4:
                        case 6:
                        case 8:
                        case 10:
                            g.setColor(Color.BLUE);
                            g.fillRect(o*boxSize, i*boxSize, boxSize, boxSize);
                            break;
                        case 3:
                        case 5:
                        case 7:
                        case 9:
                        case 11:
                            g.setColor(Color.GREEN);
                            g.fillRect(o*boxSize, i*boxSize, boxSize, boxSize);
                            break;
                        case 12:
                            g.setColor(Color.ORANGE);
                            g.fillRect(o*boxSize, i*boxSize, boxSize, boxSize);
                            break;
                        case 13:
                            g.setColor(Color.RED);
                            g.fillRect(o*boxSize, i*boxSize, boxSize, boxSize);
                            break;
                        case 14:
                            drawGrid = false;
                            g.drawImage(win, 0, 0, null);
                            break;
                    }
                }
            }
            if(drawGrid){
                g.setColor(Color.RED);
                for (int i = 0; i < sqrMnt; i++) {
                    int x = boxSize * i;
                    g.drawLine(x, 0, x, boxSize * sqrMnt);
                    g.drawLine(0, x, boxSize * sqrMnt, x);
                }
            }
            
        }
    }

    
    
    public void boxPressed(int mouseX, int mouseY, int windowWidth, int windowHeight) {
        int boxWidth = windowWidth / sqrMnt;
        int boxHeight = windowHeight / sqrMnt;
        
        int boxX = mouseX / boxWidth;
        int boxY = mouseY / boxHeight;
        
        System.out.println("Box pressed at: (" + boxX + ", " + boxY + ")");
        sf.shoot(boxX,boxY);
        repaint();
        sf.print();
    }
}
