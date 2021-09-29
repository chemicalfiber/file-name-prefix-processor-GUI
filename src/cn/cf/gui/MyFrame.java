package cn.cf.gui;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    public MyFrame(String title){
        // 新建窗口
        setTitle(title);
        setBounds(300,300,1340,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4,2,20,20));
        setResizable(false);

        // 路径选择器和展示框
        JTextField folderPathTextField = new JTextField("在右边选择路径(Click on the button on the right to choose a path)：");
        folderPathTextField.setEditable(false);
//        JButton chooserButton = new JButton("选择路径(Choose a path)");
        ChooserButton chooserButton = new ChooserButton(folderPathTextField);

        // 输入前缀的提示文本和输入框
        JLabel prefixTips = new JLabel("请输入你要删除的文件名前缀(Please enter the file name prefix that you want to delete)：");
        JTextField prefixField = new JTextField();

        // 选择处理方式
        JLabel treatmentOptionsLabel = new JLabel("上述文件是通过下列哪个下载器下载的？(Which of the following downloader used to download these files?)");
//        JComboBox<String> treatmentOptionsComboBox = new JComboBox<>();
        OptionsComboBox treatmentOptionsComboBox = new OptionsComboBox();

        // 处理按钮
        JLabel processTipsLabel = new JLabel("点击右边的按钮处理(Click the button on the right to start processing)");
//        JButton processButton = new JButton("处理(Process)");
        ProcessButton processButton = new ProcessButton(folderPathTextField, prefixField, treatmentOptionsComboBox);

        // 将上面的组件添加到窗口内
        add(folderPathTextField);
        add(chooserButton);

        add(prefixTips);
        add(prefixField);

        add(treatmentOptionsLabel);
        add(treatmentOptionsComboBox);

        add(processTipsLabel);
        add(processButton);

        // 如果不放在最后就会在Mac上出现渲染bug的奇怪语句
        setVisible(true);
    }
}
