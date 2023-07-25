package handler;

import entity.MemberDO;
import services.Impl.MemberServiceImpl;
import views.MemberManagementView;
import views.NewMemberView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Xiang Weng
 */
public class NewMemberViewHandler implements ActionListener {
    private NewMemberView view;
    private MemberManagementView memberManagementView;

    public NewMemberViewHandler(NewMemberView view, MemberManagementView memberManagementView) {
        this.view = view;
        this.memberManagementView = memberManagementView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if (text.equals("Join Now")) {
            MemberServiceImpl memberService = new MemberServiceImpl();
            MemberDO memberDO = view.getMemberDO();
            boolean result = memberService.add(memberDO);
            if (result){
                //reload table
                memberManagementView.refreshTable();
                view.dispose();
            } else JOptionPane.showMessageDialog(view,"Failed to add member.");
        }
    }
}
