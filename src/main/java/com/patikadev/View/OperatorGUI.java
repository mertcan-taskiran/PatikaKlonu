package com.patikadev.View;
import com.patikadev.Helper.*;
import com.patikadev.Model.Operator;

import javax.swing.*;
import java.awt.*;

public class OperatorGUI extends JFrame {
    private JPanel wrapper;

    private final Operator operator;

    public OperatorGUI(Operator operator){
        this.operator = operator;
        Helper.setLayout();
        add(wrapper);
        setSize(1000, 500);
        setLocation(Helper.scCenter("x", getSize()), Helper.scCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
    }

    public static void main(String[] args) {
        Operator op = new Operator();
        op.setId(1);
        op.setName("Mert Taş");
        op.setUname("mertcan");
        op.setPass("taskiran");
        op.setType("Operator");
        OperatorGUI opGUI = new OperatorGUI(op);
    }
}
