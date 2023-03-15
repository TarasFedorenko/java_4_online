package ua.com.alevel.persistence.dao.impl;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.com.alevel.persistence.config.HibernateConfig;
import ua.com.alevel.persistence.dao.MovieDao;
import ua.com.alevel.persistence.dto.MovieDTO;
import ua.com.alevel.persistence.entity.Movie;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MovieDaoImpl implements MovieDao {
    SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    @Override
    public void create(Movie movie) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<Movie> getById(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Movie m where m.id = :id").setParameter("id", id);
            Movie movie = (Movie) query.getResultList().get(0);
            transaction.commit();
            return Optional.of(movie);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Optional.empty();
    }

    @Override
    public Collection<Movie> getAll() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Movie");
            List<Movie> movies = query.getResultList();
            transaction.commit();
            return movies;
        } catch (Exception e) {
            System.out.println(e + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;

    }

    @Override
    public void update(Movie movie) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.update(movie);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(Movie movie) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.delete(movie);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Collection<MovieDTO> getNumberOfActorsByMovie() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select new ua.com.alevel.persistence.dto.MovieDTO (m ,count (m.id)) from Movie m join m.actors group by m.id");
            Collection<MovieDTO> movieDTOS = query.getResultList();
            transaction.commit();
            return movieDTOS;
        } catch (Exception e) {
            System.out.println(e + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList();
    }
}