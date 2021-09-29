package cn.cf.gui;

import javax.swing.*;

public class MyDialog extends JDialog {
    public MyDialog(String message){
        add(new JLabel(message));
        // 与主窗口大小一致，以便盖住主窗口
        setBounds(300,300,1340,300);
        setVisible(true);
    }
}
