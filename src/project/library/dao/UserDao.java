package project.library.dao;

import lombok.SneakyThrows;
import project.library.entity.Gender;
import project.library.entity.Role;
import project.library.entity.UserEntity;
import project.library.util.ConnectionManager;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class UserDao implements Dao<Integer, UserEntity> {

    private static final UserDao INSTANCE = new UserDao();

    private static final String SAVE_SQL =
            "INSERT INTO users (name, birthday, email, password, role, gender, image) VALUES" +
            "(?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_BY_EMAIL_AND_PASSWORD_SQL =
            "SELECT * FROM users WHERE email = ? AND password = ?";


    @Override
    public List<UserEntity> findAll() {
        return List.of();
    }

    @Override
    public Optional<UserEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(UserEntity userEntity) {
        return false;
    }

    @Override
    public void update(UserEntity entity) {

    }

    @Override
    @SneakyThrows
    public UserEntity save(UserEntity entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getName());
            preparedStatement.setObject(2, entity.getBirthday());
            preparedStatement.setObject(3, entity.getEmail());
            preparedStatement.setObject(4, entity.getPassword());
            preparedStatement.setObject(5, entity.getRole().name());
            preparedStatement.setObject(6, entity.getGender().name());
            preparedStatement.setObject(7, entity.getImage());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Integer.class));

            return entity;
        }
    }

    @SneakyThrows
    public Optional<UserEntity> findByEmailAndPassword(String email, String password) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(GET_BY_EMAIL_AND_PASSWORD_SQL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            var resultSet = preparedStatement.executeQuery();
            UserEntity user = null;
            if (resultSet.next()) {
                user = buildEntity(resultSet);
            }

            return Optional.ofNullable(user);
        }
    }



    public static UserDao getInstance() {
        return INSTANCE;
    }

    private UserEntity buildEntity(ResultSet resultSet) throws java.sql.SQLException {
        return UserEntity.builder()
                .id(resultSet.getObject("id", Integer.class))
                .name(resultSet.getObject("name", String.class))
                .birthday(resultSet.getObject("birthday", Date.class).toLocalDate())
                .image(resultSet.getObject("image", String.class))
                .email(resultSet.getObject("email", String.class))
                .password(resultSet.getObject("password", String.class))
                .role(Role.find(resultSet.getObject("role", String.class)).orElse(null))
                .gender(Gender.valueOf(resultSet.getObject("gender", String.class)))
                .build();

    }

}