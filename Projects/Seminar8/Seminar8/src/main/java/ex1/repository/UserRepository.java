package ex1.repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ex1.dto.UserDto;
import ex1.entity.User;
import ex1.exception.UserAlreadyOnDbException;
import ex1.exception.UserNotFoundException;
import ex1.mapper.UserMapper;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserMapper userMapper;

    public User getUser(String username) {
        String sqlQuery = "SELECT * from laborator8_db.users WHERE laborator8_db.users.username = ? ";
        RowMapper<User> rowMapper = (resultSet, rowNo) -> User.builder()
                .id(resultSet.getLong("id"))
                .build();
        List<User> users = jdbcTemplate.query(sqlQuery, rowMapper, username);
        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    public User save(User user) {

        User userAdded = getUser(user.getUsername());
        if(userAdded!=null)
            throw new UserAlreadyOnDbException(String.format("User %s is already on the db", user.getUsername()));

        String saveUser = "INSERT INTO laborator8_db.users (username, full_name, user_type) VALUES (?,?,?)";
        jdbcTemplate.update(saveUser, user.getUsername(), user.getFullName(), user.getUserType());

        if (user.getUserDetails() != null) {
            userAdded = getUser(user.getUsername());
            Long userId = userAdded.getId();
            String saveUserDetails = "INSERT INTO laborator8_db.user_details (user_id, cnp, age, other_information) VALUES (?,?,?,?)";
            jdbcTemplate.update(saveUserDetails, userId, user.getUserDetails().getCnp(), user.getUserDetails().getAge(),
                    user.getUserDetails().getOtherInformation());
        }
        return getUserDetails(user.getUsername());
    }


    public User getUserDetails(String username) {
        String getUser = "SELECT laborator8_db.user_details.*, laborator8_db.users.* FROM laborator8_db.user_details "
                + "JOIN laborator8_db.users ON laborator8_db.user_details.user_id = laborator8_db.users.id "
                + "WHERE laborator8_db.users.username = ? "
                + "ORDER BY laborator8_db.user_details.user_id";

        RowMapper<UserDto> rowMapper = (resultSet, rowNo) -> UserDto.builder()
                .id(resultSet.getLong("user_id"))
                .fullName(resultSet.getString("full_name"))
                .userType(resultSet.getString("user_type"))
                .username(resultSet.getString("username"))
                .cnp(resultSet.getString("cnp"))
                .age(resultSet.getInt("age"))
                .otherInformation(resultSet.getString("other_information"))
                .build();
        List<UserDto> userDtos = jdbcTemplate.query(getUser, rowMapper, username);

        if (!userDtos.isEmpty()) {
            return userMapper.mapToUser(userDtos.get(0));
        }
        throw new UserNotFoundException(String.format("No %s user found in the db.", username));
    }


    public List<UserDto> getAllUsersWithDetails() {
        String getUser = "SELECT laborator8_db.user_details.*, laborator8_db.users.* FROM laborator8_db.user_details "
                + "JOIN laborator8_db.users ON laborator8_db.user_details.user_id = laborator8_db.users.id "
                + "ORDER BY laborator8_db.user_details.user_id";

        RowMapper<UserDto> rowMapper = (resultSet, rowNo) -> UserDto.builder()
                .id(resultSet.getLong("user_id"))
                .fullName(resultSet.getString("full_name"))
                .userType(resultSet.getString("user_type"))
                .username(resultSet.getString("username"))
                .cnp(resultSet.getString("cnp"))
                .age(resultSet.getInt("age"))
                .otherInformation(resultSet.getString("other_information"))
                .build();
        List<UserDto> userDtos = jdbcTemplate.query(getUser, rowMapper);

        if (!userDtos.isEmpty()) {

            return userDtos;

        }
        throw new UserNotFoundException(String.format("The user list is empty!"));
    }

}
