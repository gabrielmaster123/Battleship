import javax.swing.*;
public class Oberflaeche {
    public static void main(String[] args) {
        JFrame mFrame = new JFrame();
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.setSize(615,639);//more pixels to adjust for the title bar
        mFrame.setResizable(false);
        GUI mGUI = new GUI();
        
        mFrame.setContentPane(mGUI);
        
        //generate the playground and the ships in the array 
        Playground  sf = new Playground(12, 12);
        generateShips(5,4,3,3,2,sf);
        Playground.print();
                
        mFrame.setVisible(true);
        
    }

    public static void generateShips(int size1, int size2, int size3, int size4, int size5, Playground field) {
        field.ship(size1);
        field.ship(size2);
        field.ship(size3);
        field.ship(size4);
        field.ship(size5);
      }
    
}
