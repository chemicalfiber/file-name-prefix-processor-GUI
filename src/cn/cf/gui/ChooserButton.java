package cn.cf.gui;

import javax.swing.*;
import java.io.File;

public class ChooserButton extends JButton {
    public ChooserButton(JTextField folderPathTextField){
        setText("选择路径(Choose a path)");
        // 文件选择器的按钮事件
        addActionListener(e -> {
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
    }
}
