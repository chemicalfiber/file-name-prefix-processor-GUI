package cn.cf.gui;

import javax.swing.*;

public class MyDialog extends JDialog {
    public MyDialog(String message){
        add(new JLabel(message));
        setBounds(300,300,1340,300);
        setVisible(true);
    }
}
