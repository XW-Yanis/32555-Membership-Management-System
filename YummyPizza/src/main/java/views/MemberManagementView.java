package views;


import req.TableDTO;
import handler.MemberManagementViewHandler;
import req.MemberRequest;
import services.Impl.MemberServiceImpl;
import util.Util;
import views.extend.MemberTable;
import views.extend.MemberTableModel;

import javax.swing.*;
import java.awt.*;

/**
 * @author Xiang Weng
 */
public class MemberManagementView extends JFrame {
    // Components in  North section
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addBtn = new JButton("Add");
    JButton updateBtn = new JButton("Update");
    JButton deleteBtn = new JButton("Delete");

    JTextField searchField = new JTextField(15);
    JButton searchBtn = new JButton("Search");

    // Components in South section
    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    JButton previousBtn = new JButton("Previous Page");
    JButton nextBtn = new JButton("Next Page");

    MemberTable memberTable = new MemberTable();
    MemberManagementViewHandler handler;

    // page index
    private int currentPage = 1;
    // display number of data per page
    private int pageSize = 20;

    public static void main(String[] args) {
        new MemberManagementView();
    }

    public MemberManagementView() {
        super("Member Management");
        Container contentPane = getContentPane();
        handler = new MemberManagementViewHandler(this);
        assignNorth(contentPane);
        assignCenter(contentPane);
        assignSouth(contentPane);

        init();
    }

    private void init() {
        //basic setting for the window
        setBounds(Util.getBounds());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void assignCenter(Container contentPane) {

        TableDTO tableDTO = getTableDTO();
        MemberTableModel memberTableModel = MemberTableModel.assembleModel(tableDTO.getData());
        memberTable.setTableModel(memberTableModel);
        memberTable.renderRules();
        JScrollPane jScrollPane = new JScrollPane(memberTable);
        contentPane.add(jScrollPane, BorderLayout.CENTER);
        showPreNextBtn(tableDTO.getTotalCount());
    }

    private TableDTO getTableDTO() {
        MemberServiceImpl memberService = new MemberServiceImpl();
        MemberRequest memberRequest = new MemberRequest();
        memberRequest.setCurrentPage(currentPage);
        memberRequest.setPageSize(pageSize);
        memberRequest.setSearchKey(searchField.getText());

        TableDTO tableDTO = memberService.retrieveMembers(memberRequest);
        return tableDTO;
    }

    private void assignSouth(Container contentPane) {
        southPanel.add(previousBtn);
        southPanel.add(nextBtn);
        previousBtn.addActionListener(handler);
        nextBtn.addActionListener(handler);
        contentPane.add(southPanel, BorderLayout.SOUTH);
    }

    private void assignNorth(Container contentPane) {
        northPanel.add(addBtn);
        northPanel.add(updateBtn);
        northPanel.add(deleteBtn);
        northPanel.add(searchField);
        northPanel.add(searchBtn);

        addBtn.addActionListener(handler);
        updateBtn.addActionListener(handler);
        deleteBtn.addActionListener(handler);
        searchBtn.addActionListener(handler);
        contentPane.add(northPanel, BorderLayout.NORTH);
    }

    public void refreshTable() {
        TableDTO tableDTO = getTableDTO();
        MemberTableModel.updateModel(tableDTO.getData());
        memberTable.renderRules();
        showPreNextBtn(tableDTO.getTotalCount());
    }

    public void showPreNextBtn(int totalCount){
        if (currentPage == 1) previousBtn.setVisible(false);
        else previousBtn.setVisible(true);
        int numberOfPage = 0;
        if(totalCount % pageSize == 0)
            numberOfPage = totalCount / pageSize;
        else
            numberOfPage = totalCount / pageSize + 1;
        if (currentPage == numberOfPage)
            nextBtn.setVisible(false);
        else nextBtn.setVisible(true);
    }

    public int[] getSelectedMemberIDs(){
        int[] highlightedRows = memberTable.getSelectedRows();
        int[] ids = new int[highlightedRows.length];
        for (int i = 0; i < highlightedRows.length; i++) {
            int rowIdx = highlightedRows[i];
            Object id = memberTable.getValueAt(rowIdx, 0);
            ids[i] = Integer.valueOf(id.toString());
        }
        return ids;
    }
}
