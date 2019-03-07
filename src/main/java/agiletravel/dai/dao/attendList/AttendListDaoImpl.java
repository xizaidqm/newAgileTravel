package agiletravel.dai.dao.attendList;

import agiletravel.dai.entity.Activity;
import agiletravel.dai.entity.User;
import agiletravel.dai.form.reViewHistory;
import agiletravel.dai.form.reViewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("attendListDao")
public class AttendListDaoImpl implements AttendListDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void updateComment(String comment, String travelid, String openid) {
        String sql="update attendList set comment=? where travelid=? and openid=?";
        jdbcTemplate.update(sql,comment,travelid,openid);
    }

    @Override
    public List<reViewUser> findByTravelid(String travelid) {
        String sql="select att.openid, nickName, phoneNumber from attendlist as att, users as us where att.openid = us.openid and att.travelid=?";
        List<reViewUser> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(reViewUser.class),travelid);
        return list;
    }

    @Override
    public List<reViewHistory> findByOpenid(String openid) {
        String sql="select travelName, startTime, description, cost from attendList as att, activities as act where att.travelid=act.travelid and att.openid = ?";
        List<reViewHistory> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(reViewHistory.class),openid);
        return list;
    }

    @Override
    public void addAttendList(String travelid, String openid) {
        String sql="insert into attendlist values(?,?,?)";
        jdbcTemplate.update(sql,travelid,openid,"默认好评");
    }

    @Override
    public void deleteAttendList(String travelid, String openid) {
        String sql="delete from attendlist where travelid=? and openid=?";
        jdbcTemplate.update(sql,travelid,openid);
    }


}
