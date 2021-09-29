package cn.cf.gui;

import cn.cf.util.PrefixProcessor;

import javax.swing.*;

public class ProcessButton extends JButton {
    public ProcessButton(JTextField folderPathTextField,JTextField prefixField, JComboBox<String> treatmentOptionsComboBox){
        setText("处理(Process)");
        // 处理按钮事件
        addActionListener(e -> {
            // 处理结果的flag
            boolean flag = false;
            // 获取路径和要处理的前缀
            String folderPath = folderPathTextField.getText();
            String prefix = prefixField.getText();
            // 获取选择的处理方式
            int treatmentOptions = treatmentOptionsComboBox.getSelectedIndex();
            // 当未选择路径时
            if (folderPath.isEmpty()||folderPath.equals("在右边选择路径(Click on the button on the right to choose a path)：")){
                new MyDialog("你必须选择一个目录！ You must choose a directory !");
                return;
            }
            // 当未输入前缀时
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
            // 显示处理结果
            if (flag){
                new MyDialog("成功！");
                // 重置路径框和前缀输入框
                folderPathTextField.setText("在右边选择路径(Click on the button on the right to choose a path)：");
                prefixField.setText("");
            }else{
                new MyDialog("没有找到以「" + prefix + "」开头的文件！ Cannot find the file whose name starts with 【" + prefix + "】");
            }
        });
    }
}
