package agiletravel.dai.dao.user;

import agiletravel.dai.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findById(String openid) {
        String sql="select * from users where openid =?";
        List<User> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class),openid);
        return list.size()==0?null:list.get(0);
    }

    @Override
    public void addUser(User user) {
        String sql="insert into users values(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,user.getOpenid(),user.getNickname(),user.getGender(),user.getCountry(),
                    user.getProvince(),user.getCity(),user.getAge(),user.getPhoneNumber());

    }

    @Override
    public void updateUser(User user) {
        String sql="update users set nickname=?, gender=?, country=?, province=?, city=?, age=?, phonenumber=? where openid=?";
        jdbcTemplate.update(sql,user.getNickname(),user.getGender(),user.getCountry(),user.getProvince(),
                user.getCity(),user.getAge(),user.getPhoneNumber(),user.getOpenid());

    }
}
