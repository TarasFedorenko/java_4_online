package ua.com.alevel.persistence.dao.impl;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.com.alevel.persistence.config.HibernateConfig;
import ua.com.alevel.persistence.dao.ActorDao;
import ua.com.alevel.persistence.dto.ActorDTO;
import ua.com.alevel.persistence.entity.Actor;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ActorDaoImpl implements ActorDao {
    SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    @Override
    public Collection<ActorDTO> getNumberOfMoviesByActor() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select new ua.com.alevel.persistence.dto.ActorDTO (a ,count (a.id)) from Actor a join a.movies group by a.id");
            Collection<ActorDTO> actorDTOS = query.getResultList();
            transaction.commit();
            return actorDTOS;
        } catch (Exception e) {
            System.out.println(e + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public void create(Actor actor) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(actor);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<Actor> getById(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Actor a where a.id = :id").setParameter("id", id);
            Actor actor = (Actor) query.getResultList().get(0);
            transaction.commit();
            return Optional.of(actor);
        } catch (Exception e) {
            System.out.println(e + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Optional.empty();
    }

    @Override
    public Collection<Actor> getAll() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Actor");
            List<Actor> actors = query.getResultList();
            transaction.commit();
            return actors;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public void update(Actor actor) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.update(actor);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(Actor actor) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.delete(actor);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}