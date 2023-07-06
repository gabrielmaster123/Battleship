import javax.swing.*;
public class Oberflaeche {
    public static void main(String[] args) {
        JFrame mFrame = new JFrame();
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.setSize(615,639);//more pixels to adjust for the title bar
        mFrame.setResizable(true);
        GUI mGUI = new GUI();
        
        mFrame.setContentPane(mGUI);                
        mFrame.setVisible(true);
        
    }

    
    
}
