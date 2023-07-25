package handler;

import entity.AdminDO;
import services.Impl.AdminServiceImpl;
import views.LoginView;
import views.MemberManagementView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Xiang Weng
 */
public class LoginViewHandler implements ActionListener {
    private LoginView loginView;

    public LoginViewHandler(LoginView view) {
        loginView = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String text = source.getText();
        if (text.equals("Login")) {
            String username = loginView.getUsername().getText();
            String pwd = loginView.getPwdField().getText();

            if (username.equals("") || username == null)
                JOptionPane.showMessageDialog(loginView,"Username is empty.");
            else if (pwd.equals("") || pwd == null)
                JOptionPane.showMessageDialog(loginView,"Please enter password.");
            // DB validate
            AdminServiceImpl adminService = new AdminServiceImpl();
            AdminDO adminDO = new AdminDO();
            adminDO.setUsername(username);
            adminDO.setPwd(pwd);
            boolean isValid = adminService.validate(adminDO);
            if (isValid) {
                new MemberManagementView();
                loginView.dispose();
            } else {
                JOptionPane.showMessageDialog(loginView, "Invalid username or passwords.");
            }

        } else if (text.equals("Clear")) {
            //get user input
            loginView.getUsername().setText("");
            loginView.getPwdField().setText("");
        }
    }
}
