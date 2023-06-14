package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;

import javax.swing.*;

public class EducatorGUI extends JFrame{
    private JPanel wrapper;
    public EducatorGUI(){
        add(wrapper);
        setSize(400, 400);
        setLocation(Helper.scCenter("x", getSize()), Helper.scCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
    }
}
