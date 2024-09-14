package view.GUI.panels;

import javax.swing.*;
import java.awt.*;


public class MainScreenPanel extends JFrame{
    private static final String FRAME_NAME = "Doctor Dashboard";

    public  MainScreenPanel(){
        super();
        this.setTitle(FRAME_NAME);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
    }

}
