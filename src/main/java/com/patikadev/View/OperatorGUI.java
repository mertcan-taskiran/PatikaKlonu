package com.patikadev.View;
import com.patikadev.Helper.*;
import com.patikadev.Model.Operator;
import com.patikadev.Model.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperatorGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane tab_operator;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JPanel pnl_userList;
    private JScrollPane scrl_userList;
    private JTable tbl_userList;
    private JPanel pnl_userForm;
    private JTextField fld_userName;
    private JTextField fld_userUname;
    private JPasswordField fld_userPass;
    private JComboBox cmb_userType;
    private JButton btn_userAdd;
    private JButton btn_userDelete;
    private JTextField fld_userId;
    private DefaultTableModel mdl_userList;
    private Object[] row_userList;

    private final Operator operator;

    public OperatorGUI(Operator operator){
        this.operator = operator;

        add(wrapper);
        setSize(1000, 500);
        setLocation(Helper.scCenter("x", getSize()), Helper.scCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        lbl_welcome.setText("Hoşgeldin " + operator.getName());

        // ModelUserList
        mdl_userList = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_userList = {"Id", "Ad Soyad", "Kullanıcı Adı", "Şifre", "Üyelik Tipi"};
        mdl_userList.setColumnIdentifiers(col_userList);

        row_userList = new Object[col_userList.length];

        loadUserModel();

        tbl_userList.setModel(mdl_userList);
        tbl_userList.getTableHeader().setReorderingAllowed(false);

        tbl_userList.getSelectionModel().addListSelectionListener(e -> {
            try{
                String select_user_id = tbl_userList.getValueAt(tbl_userList.getSelectedRow(), 0).toString();
                fld_userId.setText(select_user_id);
            } catch (Exception exception){

            }
        });

        btn_userAdd.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_userName) || Helper.isFieldEmpty(fld_userUname) || Helper.isFieldEmpty(fld_userPass)){
                Helper.showMsg("fill");
            } else
            {
                String name = fld_userName.getText();
                String uname = fld_userUname.getText();
                String pass = fld_userPass.getText();
                String type = cmb_userType.getSelectedItem().toString();

                if (User.add(name, uname, pass, type)){
                    Helper.showMsg("done");

                    loadUserModel();

                    fld_userPass.setText(null);
                    fld_userName.setText(null);
                    fld_userUname.setText(null);

                } else
                {
                    Helper.showMsg("error");
                }
            }
        });

        btn_userDelete.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_userId)){
                Helper.showMsg("fill");
            } else
            {
                int fld_user_id = Integer.parseInt(fld_userId.getText());
                if (User.delete(fld_user_id)){
                    Helper.showMsg("done");
                    loadUserModel();
                }else {
                    Helper.showMsg("error");
                }
            }
        });
    }

    public void loadUserModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_userList.getModel();
        clearModel.setRowCount(0);

        for (User obj : User.getList()){

            row_userList[0] = obj.getId();
            row_userList[1] = obj.getName();
            row_userList[2] = obj.getUname();
            row_userList[3] = obj.getPass();
            row_userList[4] = obj.getType();
            mdl_userList.addRow(row_userList);
        }
    }

    public static void main(String[] args) {
        Helper.setLayout();
        Operator op = new Operator();
        op.setId(1);
        op.setName("Mert Taş");
        op.setUname("mertcan");
        op.setPass("taskiran");
        op.setType("Operator");
        OperatorGUI opGUI = new OperatorGUI(op);
    }
}
