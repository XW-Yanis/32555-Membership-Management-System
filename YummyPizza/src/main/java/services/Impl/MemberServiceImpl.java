package services.Impl;

import entity.MemberDO;
import req.TableDTO;
import req.MemberRequest;
import services.MemberService;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @author Xiang Weng
 */
public class MemberServiceImpl implements MemberService {
    @Override
    public TableDTO retrieveMembers(MemberRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("select id, nickname,email,phone_number from member");
        if (request != null && !request.getSearchKey().equals("")) {
            sb.append(" where nickname like '%" + request.getSearchKey() + "%'");
        }
        sb.append(" order by id asc limit ")
                .append(request.startIdx())
                .append(",")
                .append(request.getPageSize());

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TableDTO tableDTO = new TableDTO();

        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(sb.toString());
            rs = ps.executeQuery();

            tableDTO.setData(fillData(rs));
            sb.setLength(0);

            sb.append("select count(*) from member ");
            if (request != null && !request.getSearchKey().equals("")) {
                sb.append(" where nickname like '%" + request.getSearchKey() + "%'");
            }
            ps = connection.prepareStatement(sb.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                int cnt = rs.getInt(1);
                tableDTO.setTotalCount(cnt);
            }
            return tableDTO;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConnection(connection);
        }

        return null;
    }

    @Override
    public boolean delete(int[] selectedMemberIDs) {
        StringBuilder sb = new StringBuilder();
        sb.append("delete from member where id in ( ");
        for (int i = 0; i < selectedMemberIDs.length; i++) {
            if (i == selectedMemberIDs.length - 1)
                sb.append(" ? ");
            else sb.append(" ?, ");
        }
        sb.append(" ) ");

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(sb.toString());
            for (int i = 0; i < selectedMemberIDs.length; i++) {
                ps.setInt(i+1,selectedMemberIDs[i]);
            }
            return ps.executeUpdate() == selectedMemberIDs.length;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePs(ps);
            DBUtil.closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean update(MemberDO memberDO) {
        StringBuilder sb = new StringBuilder();
        sb.append("update member set nickname = ?, email = ?,phone_number = ?,password = ?");
        sb.append("where id = ? ");

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(sb.toString());
            ps.setString(1,memberDO.getNickname());
            ps.setString(2,memberDO.getEmail());
            ps.setString(3,memberDO.getPhone());
            ps.setString(4,memberDO.getPwd());
            ps.setInt(5,memberDO.getId());
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePs(ps);
            DBUtil.closeConnection(connection);
        }
        return false;
    }

    @Override
    public MemberDO getByID(int selectedMemberID) {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from member where id = ? ");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        MemberDO memberDO = new MemberDO();
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(sb.toString());
            ps.setInt(1,selectedMemberID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String nickname = rs.getString("nickname");
                String email = rs.getString("email");
                String phone = rs.getString("phone_number");
                String pwd = rs.getString("password");
                memberDO.setId(id);
                memberDO.setNickname(nickname);
                memberDO.setEmail(email);
                memberDO.setPhone(phone);
                memberDO.setPwd(pwd);
            }
            return memberDO;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConnection(connection);
        }
        return null;
    }

    @Override
    public boolean add(MemberDO memberDO) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into member(nickname,email,phone_number,password) ");
        sb.append("values(?,?,?,?)");

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(sb.toString());
            ps.setString(1,memberDO.getNickname());
            ps.setString(2,memberDO.getEmail());
            ps.setString(3,memberDO.getPhone());
            ps.setString(4,memberDO.getPwd());
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePs(ps);
            DBUtil.closeConnection(connection);
        }
        return false;
    }

    private Vector<Vector<Object>> fillData(ResultSet rs) throws SQLException {
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            Vector<Object> record = new Vector<>();
            int id = rs.getInt(1);
            String nickname = rs.getString("nickname");
            String email = rs.getString("email");
            String phone = rs.getString("phone_number");

            record.addElement(id);
            record.addElement(nickname);
            record.addElement(email);
            record.addElement(phone);
            data.addElement(record);
        }
        return data;
    }
}
