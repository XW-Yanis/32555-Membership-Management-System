package views;

import entity.MemberDO;
import handler.UpdateMemberViewHandler;
import services.Impl.MemberServiceImpl;
import services.MemberService;

import javax.swing.*;
import java.awt.*;

/**
 * @author Xiang Weng
 */
public class UpdateMemberView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel id = new JLabel("ID", JLabel.RIGHT);
    JTextField idField = new JTextField();
    JLabel nickname = new JLabel("Nickname:", JLabel.RIGHT);
    JTextField nicknameField = new JTextField();
    JLabel email = new JLabel("Email:",JLabel.RIGHT);
    JTextField emailField = new JTextField();
    JLabel phone = new JLabel("Phone:",JLabel.RIGHT);
    JTextField phoneField = new JTextField();
    JLabel pwd = new JLabel("Password:",JLabel.RIGHT);
    JTextField pwdField = new JTextField();
    JButton jButton = new JButton("Confirm");
    UpdateMemberViewHandler handler;

    public UpdateMemberView(MemberManagementView view, int selectedMemberID){
        super(view, "Update Info",true);
        handler = new UpdateMemberViewHandler(this,view);

        MemberService memberService = new MemberServiceImpl();
        MemberDO memberDO = memberService.getByID(selectedMemberID);

        id.setPreferredSize(new Dimension(80,30));
        nickname.setPreferredSize(new Dimension(80,30));
        email.setPreferredSize(new Dimension(80,30));
        phone.setPreferredSize(new Dimension(80,30));
        pwd.setPreferredSize(new Dimension(80,30));

        idField.setPreferredSize(new Dimension(200,30));
        idField.setText(memberDO.getId().toString());
        idField.setEditable(false);
        nicknameField.setPreferredSize(new Dimension(200,30));
        nicknameField.setText(memberDO.getNickname());
        emailField.setPreferredSize(new Dimension(200,30));
        emailField.setText(memberDO.getEmail());
        phoneField.setPreferredSize(new Dimension(200,30));
        phoneField.setText(memberDO.getPhone());
        pwdField.setPreferredSize(new Dimension(200,30));
        pwdField.setText(memberDO.getPwd());
        jPanel.add(id);
        jPanel.add(idField);
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

        setSize(350,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }

    public MemberDO getUpdatedMemberDO() {
        MemberDO memberDO = new MemberDO();
        memberDO.setId(Integer.valueOf(idField.getText()));
        memberDO.setNickname(nicknameField.getText());
        memberDO.setEmail(emailField.getText());
        memberDO.setPhone(phoneField.getText());
        memberDO.setPwd(pwdField.getText());
        return memberDO;
    }
}
