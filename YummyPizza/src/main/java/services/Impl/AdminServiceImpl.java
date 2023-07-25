package services.Impl;

import entity.AdminDO;
import services.AdminService;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Xiang Weng
 */
public class AdminServiceImpl implements AdminService {
    @Override
    public boolean validate(AdminDO adminDO) {

        String sql = "select pwd from admin where user_name = ?";
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            if (connection == null) return false;
            ps = connection.prepareStatement(sql);
            ps.setString(1,adminDO.getUsername());
            resultSet = ps.executeQuery();
            while (resultSet.next()){
                String pwd = resultSet.getString(1);
                if (pwd.equals(adminDO.getPwd())) return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeRs(resultSet);
            DBUtil.closePs(ps);
            DBUtil.closeConnection(connection);
        }
        return false;
    }
}
