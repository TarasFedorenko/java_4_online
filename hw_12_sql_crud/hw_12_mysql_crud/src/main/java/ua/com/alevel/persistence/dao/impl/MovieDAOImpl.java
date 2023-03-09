package ua.com.alevel.persistence.dao.impl;

import ua.com.alevel.persistence.config.JDBCService;
import ua.com.alevel.persistence.config.impl.JDBCServiceImpl;
import ua.com.alevel.persistence.dao.MovieDAO;
import ua.com.alevel.persistence.dto.MovieGenreDTO;
import ua.com.alevel.persistence.entity.Movie;
import ua.com.alevel.persistence.type.MovieGenre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class MovieDAOImpl implements MovieDAO {
    JDBCService jdbcService = new JDBCServiceImpl();
    public static final String CREATE_MOVIE = "insert into movies values(default, CURRENT_TIMESTAMP,?,?,?)";
    public static final String UPDATE_MOVIE = "update movies set title = ?,movie_genre = ?, release_year =? where id = ?";
    public static final String DELETE_MOVIE = "delete from movies where id = ?";
    public static final String FIND_MOVIES_BY_ID = "select * from movies where id = ";
    public static final String FIND_ALL_MOVIES = "select * from movies";
    public static final String FIND_ALL_MOVIES_BY_ACTOR = "select id, created, title, movie_genre, release_year from movies\n" +
            "left join act_mov am on movies.id = am.mov_id where act_id =";
    public static final String FIND_NUMBERS_OF_ACTORS_BY_MOVIE_GENRE = "select movie_genre, count(distinct act_id) as actors_count\n" +
            "from movies join act_mov on id = act_mov.mov_id\n" +
            "group by movie_genre";

    @Override
    public void createMovie(Movie movie) {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(CREATE_MOVIE)) {
            preparedStatement.setString(1, movie.getTitle());
            MovieGenre movieGenre = movie.getMovieGenre();
            String movieGenres = movieGenre.name();
            preparedStatement.setString(2, movieGenres);
            preparedStatement.setInt(3, movie.getReleaseYear());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Movie> getMovie(Long id) {
        try (ResultSet resultSet = jdbcService.getStatement().executeQuery(FIND_MOVIES_BY_ID + id)) {
            while (resultSet.next()) {
                return Optional.of(initMoviesByResultSet(resultSet));
            }
        } catch (SQLException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public void updateMovie(Movie movie) {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(UPDATE_MOVIE)) {
            preparedStatement.setString(1, movie.getTitle());
            MovieGenre movieGenre = movie.getMovieGenre();
            String movieGenres = movieGenre.name();
            preparedStatement.setString(2, movieGenres);
            preparedStatement.setInt(3, movie.getReleaseYear());
            preparedStatement.setLong(4, movie.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMovie(Long id) {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(DELETE_MOVIE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Movie> getAllMovie() {
        List<Movie> movies = new ArrayList<>();
        try (ResultSet resultSet = jdbcService.getStatement().executeQuery(FIND_ALL_MOVIES)) {
            while (resultSet.next()) {
                movies.add(initMoviesByResultSet(resultSet));
            }
        } catch (SQLException e) {
            return movies;
        }
        return movies;
    }

    @Override
    public Collection<Movie> getAllMoviesByActor(Long actorId) {
        List<Movie> movies = new ArrayList<>();
        try (ResultSet resultSet = jdbcService.getStatement().executeQuery(FIND_ALL_MOVIES_BY_ACTOR + actorId)) {
            while (resultSet.next()) {
                movies.add(initMoviesByResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }

    @Override
    public Collection<MovieGenreDTO> getNumberOfActorsByMovieGenre() {
        List<MovieGenreDTO> movies = new ArrayList<>();
        try (ResultSet resultSet = jdbcService.getStatement().executeQuery(FIND_NUMBERS_OF_ACTORS_BY_MOVIE_GENRE)) {
            while (resultSet.next()) {
                movies.add(generateMovieGenreDTO(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }


    private Movie initMoviesByResultSet(ResultSet resultSet) throws SQLException {
        String title = resultSet.getString("title");
        String genre = resultSet.getString("movie_genre");
        MovieGenre movieGenre = MovieGenre.valueOf(genre);
        int releaseYear = resultSet.getInt("release_year");
        Long id = resultSet.getLong("id");
        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        movie.setMovieGenre(movieGenre);
        movie.setReleaseYear(releaseYear);
        return movie;
    }

    private MovieGenreDTO generateMovieGenreDTO(ResultSet resultSet) throws SQLException {
        String genre = resultSet.getString("movie_genre");
        MovieGenre movieGenre = MovieGenre.valueOf(genre);
        int actorsCount = resultSet.getInt("actors_count");
        return new MovieGenreDTO(movieGenre, actorsCount);
    }
}