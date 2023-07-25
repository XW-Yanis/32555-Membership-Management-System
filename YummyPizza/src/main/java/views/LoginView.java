package views;

import handler.LoginViewHandler;

import javax.swing.*;
import java.awt.*;

/**
 * @author Xiang Weng
 */
public class LoginView extends JFrame {
    JLabel name = new JLabel("Yummy Pizza System",JLabel.CENTER);
    JLabel usernameLabel = new JLabel("Username:");
    JTextField username = new JTextField();
    JLabel pwd = new JLabel("Password:");
    JTextField pwdField = new JPasswordField();
    JButton login = new JButton("Login");
    JButton clear = new JButton("Clear");
    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);
    Container contentPane = getContentPane();
    LoginViewHandler handler;
    public LoginView(){
        super("Login");
        handler = new LoginViewHandler(this);
        initFont();
        addComponents(centerPanel,contentPane);
        positioningComponents(springLayout);
        bindBtnActions();
        initWindow();
    }
    private void initWindow() {
        setSize(600,400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void addComponents(JPanel centerPanel, Container contentPane) {
        centerPanel.add(name);
        centerPanel.add(usernameLabel);
        centerPanel.add(username);
        centerPanel.add(pwd);
        centerPanel.add(pwdField);
        centerPanel.add(login);
        centerPanel.add(clear);
        contentPane.add(name,BorderLayout.NORTH);
        contentPane.add(centerPanel,BorderLayout.CENTER);
    }
    private void initFont() {
        name.setFont(new Font("Arial",Font.PLAIN,35));
        Font arial = new Font("Arial", Font.BOLD, 20);
        usernameLabel.setFont(arial);
        pwd.setFont(arial);
        login.setFont(arial);
        clear.setFont(arial);
        username.setPreferredSize(new Dimension(200,20));
        pwdField.setPreferredSize(new Dimension(200,20));
    }
    private void positioningComponents(SpringLayout layout){
        // layout for username label
        layout.putConstraint(SpringLayout.WEST,usernameLabel,140,SpringLayout.WEST,centerPanel);
        layout.putConstraint(SpringLayout.NORTH,usernameLabel,35,SpringLayout.SOUTH,name);
        // layout for username textfield
        layout.putConstraint(SpringLayout.WEST, username,15, SpringLayout.EAST,usernameLabel);
        layout.putConstraint(SpringLayout.NORTH, username,5, SpringLayout.NORTH,usernameLabel);
        //layout for pwd
        layout.putConstraint(SpringLayout.EAST, pwd,0, SpringLayout.EAST,usernameLabel);
        layout.putConstraint(SpringLayout.NORTH, pwd,30, SpringLayout.SOUTH,usernameLabel);
        //layout for pwd textfield
        layout.putConstraint(SpringLayout.WEST, pwdField,15, SpringLayout.EAST,pwd);
        layout.putConstraint(SpringLayout.NORTH, pwdField,5, SpringLayout.NORTH,pwd);
        //layout for the btns
        layout.putConstraint(SpringLayout.WEST, login,0, SpringLayout.WEST,usernameLabel);
        layout.putConstraint(SpringLayout.NORTH, login,30, SpringLayout.SOUTH,pwd);

        layout.putConstraint(SpringLayout.EAST, clear,0, SpringLayout.EAST,username);
        layout.putConstraint(SpringLayout.NORTH, clear,30, SpringLayout.SOUTH,pwd);
    }
    private void bindBtnActions(){
        login.addActionListener(handler);
        clear.addActionListener(handler);
    }

    public JTextField getUsername() {
        return username;
    }

    public JTextField getPwdField() {
        return pwdField;
    }

    public static void main(String[] args) {
        new LoginView();
    }
}
