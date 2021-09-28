package cn.cf;

import cn.cf.gui.MyDialog;
import cn.cf.util.PrefixProcessor;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        // 新建窗口
        JFrame jFrame = new JFrame("文件名前缀处理器-File Name Prefix Processor");
        jFrame.setBounds(300,300,1340,300);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new GridLayout(4,2,20,20));

        // 路径选择器和展示框
        JTextField folderPathTextField = new JTextField("在右边选择路径(Click on the button on the right to choose a path)：");
        folderPathTextField.setEditable(false);
        JButton chooserButton = new JButton("选择路径(Choose a path)");

        // 输入前缀的提示文本和输入框
        JLabel prefixTips = new JLabel("请输入你要删除的文件名前缀(Please enter the file name prefix that you want to delete)：");
        JTextField prefixField = new JTextField();

        // 选择处理方式
        JLabel treatmentOptionsLabel = new JLabel("上述文件是通过下列哪个下载器下载的？(Which of the following downloader used to download these files?)");
        JComboBox<String> treatmentOptionsComboBox = new JComboBox<>();
        treatmentOptionsComboBox.addItem("我没有使用下载器(I didn't use any downloader)");
        treatmentOptionsComboBox.addItem("downie");

        // 处理按钮
        JLabel processTipsLabel = new JLabel("点击右边的按钮处理(Click the button on the right to start processing)");
        JButton processButton = new JButton("处理(Process)");

        // 文件选择器的按钮事件
        chooserButton.addActionListener(e -> {
            try{
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.showDialog(new JLabel(),"选择(Choose This)");
                String pathStr = chooser.getSelectedFile().getAbsolutePath();
                // bug：如果你是进入文件夹后才点击选择按钮，得出的路径会是「真实路径+最后一级目录」，比如「/Users/chemicalfiber/Downloads/Downloads」，但是它的真实路径是「/Users/chemicalfiber/Downloads」
                // 解决方案：如果返回的字符串是可访问的目录，说明字符串没问题，否则就剪切掉最后面的目录
                if (!new File(pathStr).isDirectory()){
                    int lastSeparatorIndex = pathStr.lastIndexOf(File.separator);
                    String lastDir = pathStr.substring(lastSeparatorIndex);
                    System.out.println(lastDir);
                    folderPathTextField.setText(pathStr.substring(0, pathStr.length() - lastDir.length()));
                }else {
                    folderPathTextField.setText(pathStr);
                }
            }catch (NullPointerException npe){
                new MyDialog("你必须选择一个目录！ You must choose a directory !");
            }
        });

        // 处理按钮
        processButton.addActionListener(e -> {
            boolean flag = false;
            String folderPath = folderPathTextField.getText();
            String prefix = prefixField.getText();
            int treatmentOptions = treatmentOptionsComboBox.getSelectedIndex();
            if (folderPath.isEmpty()||folderPath.equals("在右边选择路径(Click on the button on the right to choose a path)：")){
                new MyDialog("你必须选择一个目录！ You must choose a directory !");
                return;
            }
            if (prefix.isEmpty()){
                new MyDialog("请输入要处理的前缀！Please enter the prefix that you want to delete");
                return;
            }
            switch (treatmentOptions){
                case 0:
                    flag = PrefixProcessor.commonProcess(folderPath, prefix);
                    break;
                case 1:
                    flag = PrefixProcessor.downiePrefixProcess(folderPath,prefix);
                    break;
            }
            if (flag){
                new MyDialog("成功！");
            }else{
                new MyDialog("没有找到以「" + prefix + "」开头的文件！ Cannot find the file whose name starts with 【" + prefix + "】");
            }
        });

        // 将上面的组件添加到窗口内
        jFrame.add(folderPathTextField);
        jFrame.add(chooserButton);

        jFrame.add(prefixTips);
        jFrame.add(prefixField);

        jFrame.add(treatmentOptionsLabel);
        jFrame.add(treatmentOptionsComboBox);

        jFrame.add(processTipsLabel);
        jFrame.add(processButton);

        // 如果不放在最后就会在Mac上出渲染bug的奇怪语句
        jFrame.setVisible(true);
    }
}
