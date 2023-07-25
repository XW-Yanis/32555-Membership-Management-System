package handler;

import entity.MemberDO;
import services.Impl.MemberServiceImpl;
import views.MemberManagementView;
import views.NewMemberView;
import views.UpdateMemberView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Xiang Weng
 */
public class UpdateMemberViewHandler implements ActionListener {
    private UpdateMemberView view;
    private MemberManagementView memberManagementView;

    public UpdateMemberViewHandler(UpdateMemberView view, MemberManagementView memberManagementView) {
        this.view = view;
        this.memberManagementView = memberManagementView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if (text.equals("Confirm")) {
            MemberServiceImpl memberService = new MemberServiceImpl();
            MemberDO memberDO = view.getUpdatedMemberDO();
            boolean result = memberService.update(memberDO);
            if (result){
                memberManagementView.refreshTable();
                view.dispose();
            } else JOptionPane.showMessageDialog(view, "Failed to update.");
        }
    }
}
