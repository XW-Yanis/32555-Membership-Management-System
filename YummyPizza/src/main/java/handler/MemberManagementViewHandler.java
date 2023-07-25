package handler;

import services.Impl.MemberServiceImpl;
import services.MemberService;
import views.MemberManagementView;
import views.NewMemberView;
import views.UpdateMemberView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Xiang Weng
 */
public class MemberManagementViewHandler implements ActionListener {
    private MemberManagementView view;
    public MemberManagementViewHandler(MemberManagementView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        String text = btn.getText();
        if (text.equals("Add")){
            new NewMemberView(this.view);
        } else if (text.equals("Update")){
            int[] selectedMemberIDs = view.getSelectedMemberIDs();
            if (selectedMemberIDs.length != 1){
                JOptionPane.showMessageDialog(view,"Only 1 line can be changed per time.");
                return;
            }
            new UpdateMemberView(view, selectedMemberIDs[0]);
        } else if (text.equals("Delete")){
            int[] selectedMemberIDs = view.getSelectedMemberIDs();
            if (selectedMemberIDs.length == 0){
                JOptionPane.showMessageDialog(view,"Choose at least one record to delete.");
                return;
            }
            int opt = JOptionPane.showConfirmDialog(view,"You sure to delete " + selectedMemberIDs.length
            + " records?", "YES",JOptionPane.YES_NO_OPTION);

            if (opt == JOptionPane.YES_OPTION){
                MemberService memberService = new MemberServiceImpl();
                boolean result = memberService.delete(selectedMemberIDs);
                if (result){
                    view.refreshTable();
                } else JOptionPane.showMessageDialog(view,"Failed to delete.");
            }
        } else if (text.equals("Search")){
            view.setCurrentPage(1);
            view.refreshTable();
        } else if (text.equals("Next Page")){
            view.setCurrentPage(view.getCurrentPage()+1);
            view.refreshTable();
        } else if (text.equals("Previous Page")){
            view.setCurrentPage(view.getCurrentPage()-1);
            view.refreshTable();
        }
    }
}
