package views;

import entity.MemberDO;
import handler.NewMemberViewHandler;

import javax.swing.*;
import java.awt.*;

/**
 * @author Xiang Weng
 */
public class NewMemberView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
    JLabel nickname = new JLabel("Nickname:", JLabel.RIGHT);
    JTextField nicknameField = new JTextField();
    JLabel email = new JLabel("Email:", JLabel.RIGHT);
    JTextField emailField = new JTextField();
    JLabel phone = new JLabel("Phone:", JLabel.RIGHT);
    JTextField phoneField = new JTextField();
    JLabel pwd = new JLabel("Password:", JLabel.RIGHT);
    JTextField pwdField = new JTextField();
    JButton jButton = new JButton("Join Now");
    NewMemberViewHandler handler;


    public NewMemberView(MemberManagementView view) {
        super(view, "New Member", true);
        handler = new NewMemberViewHandler(this,view);


        nickname.setPreferredSize(new Dimension(80, 30));
        email.setPreferredSize(new Dimension(80, 30));
        phone.setPreferredSize(new Dimension(80, 30));
        pwd.setPreferredSize(new Dimension(80, 30));
        nicknameField.setPreferredSize(new Dimension(200, 30));
        emailField.setPreferredSize(new Dimension(200, 30));
        phoneField.setPreferredSize(new Dimension(200, 30));
        pwdField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(nickname);
        jPanel.add(nicknameField);
        jPanel.add(email);
        jPanel.add(emailField);
        jPanel.add(phone);
        jPanel.add(phoneField);
        jPanel.add(pwd);
        jPanel.add(pwdField);
        jButton.addActionListener(handler);
        jPanel.add(jButton);

        Container contentPane = getContentPane();
        contentPane.add(jPanel);

        setSize(350, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }

    public MemberDO getMemberDO(){
        MemberDO memberDO = new MemberDO();
        memberDO.setNickname(nicknameField.getText());
        memberDO.setEmail(emailField.getText());
        memberDO.setPhone(phoneField.getText());
        memberDO.setPwd(pwdField.getText());
        return memberDO;
    }
}
