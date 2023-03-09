package ua.com.alevel.persistence.dao.impl;

import ua.com.alevel.persistence.config.JDBCService;
import ua.com.alevel.persistence.config.impl.JDBCServiceImpl;
import ua.com.alevel.persistence.dao.ActorDAO;
import ua.com.alevel.persistence.dto.ActorDTO;
import ua.com.alevel.persistence.entity.Actor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ActorDAOImpl implements ActorDAO {

    JDBCService jdbcService = new JDBCServiceImpl();

    public static final String CREATE_ACTOR = "insert into actors values(default, CURRENT_TIMESTAMP,?, ?, ? )";
    public static final String UPDATE_ACTOR = "update actors set name = ?,surname = ?, age =? where id = ?";
    public static final String DELETE_ACTOR = "delete from actors where id = ?";
    public static final String FIND_ACTORS_BY_ID = "select * from actors where id = ";
    public static final String FIND_ALL_ACTORS = "select * from actors";
    public static final String FIND_ALL_ACTORS_BY_MOVIE = "select id, created, name, surname, age from actors\n" +
            "left join act_mov am on actors.id = am.act_id where mov_id =";
    public static final String ADD_ACTOR_TO_MOVIE = "insert into act_mov values (?,?)";
    public static final String DELETE_ACTOR_FROM_MOVIE = "delete from act_mov where act_id = ? and mov_id =?";
    public static final String FIND_NUMBERS_OF_MOVIES_BY_ACTOR = "select id, created, name, surname, age, count(mov_id) as movies_count " +
            "from actors left join act_mov as am on actors.id = am.act_id group by id";

    @Override
    public void createActor(Actor actor) {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(CREATE_ACTOR)) {
            preparedStatement.setString(1, actor.getName());
            preparedStatement.setString(2, actor.getSurname());
            preparedStatement.setInt(3, actor.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Actor> getActor(Long id) {
        try (ResultSet resultSet = jdbcService.getStatement().executeQuery(FIND_ACTORS_BY_ID + id)) {
            while (resultSet.next()) {
                return Optional.of(initActorsByResultSet(resultSet));
            }
        } catch (SQLException e) {
            Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public void updateActor(Actor actor) {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(UPDATE_ACTOR)) {
            preparedStatement.setString(1, actor.getName());
            preparedStatement.setString(2, actor.getSurname());
            preparedStatement.setInt(3, actor.getAge());
            preparedStatement.setLong(4, actor.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteActor(Long id) {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(DELETE_ACTOR)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Actor> getAllActor() {
        List<Actor> actors = new ArrayList<>();
        try (ResultSet resultSet = jdbcService.getStatement().executeQuery(FIND_ALL_ACTORS)) {
            while (resultSet.next()) {
                actors.add(initActorsByResultSet(resultSet));
            }
        } catch (SQLException e) {
            return actors;
        }
        return actors;
    }

    @Override
    public Collection<Actor> getAllActorsByMovie(Long movieId) {
        List<Actor> actors = new ArrayList<>();
        try (ResultSet resultSet = jdbcService.getStatement().executeQuery(FIND_ALL_ACTORS_BY_MOVIE + movieId)) {
            while (resultSet.next()) {
                actors.add(initActorsByResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return actors;
    }

    @Override
    public void addActorToMovie(Long actorId, Long movieId) {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(ADD_ACTOR_TO_MOVIE)) {
            preparedStatement.setLong(1, actorId);
            preparedStatement.setLong(2, movieId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteActorFromMovie(Long actorId, Long movieId) {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(DELETE_ACTOR_FROM_MOVIE)) {
            preparedStatement.setLong(1, actorId);
            preparedStatement.setLong(2, movieId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<ActorDTO> getNumberOfMoviesByActor() {
        List<ActorDTO> actors = new ArrayList<>();
        try (ResultSet resultSet = jdbcService.getStatement().executeQuery(FIND_NUMBERS_OF_MOVIES_BY_ACTOR)) {
            while (resultSet.next()) {
                actors.add(generateActorsDTO(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return actors;
    }


    private Actor initActorsByResultSet(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        int age = resultSet.getInt("age");
        Long id = resultSet.getLong("id");
        Actor actor = new Actor();
        actor.setId(id);
        actor.setName(name);
        actor.setSurname(surname);
        actor.setAge(age);
        return actor;
    }

    private ActorDTO generateActorsDTO(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Timestamp timestamp = resultSet.getTimestamp("created");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        int age = resultSet.getInt("age");
        int moviesCount = resultSet.getInt("movies_count");
        Actor actor = new Actor();
        actor.setId(id);
        actor.setCreated(timestamp);
        actor.setName(name);
        actor.setSurname(surname);
        actor.setAge(age);
        return new ActorDTO(actor, moviesCount);
    }
}