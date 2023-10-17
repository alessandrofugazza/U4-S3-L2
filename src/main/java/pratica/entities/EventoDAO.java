package pratica.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.UUID;

public class EventoDAO {
    private final EntityManager em;

    public EventoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Evento event) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(event);
        transaction.commit();
        System.out.println("New event saved successfully.");
    }

    public Evento getById(UUID id) {
        return em.find(Evento.class, id);
    }

    public void delete(UUID id) {

        Evento found = em.find(Evento.class, id);

        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("Event deleted successfully.");
        } else {
            System.err.println("Event for id " + id + " wasn't found.");
        }

    }

    public void refresh(Evento event) {
            em.refresh(event);
            System.out.println("Event \"" + event.getTitolo() + "\"has been refreshed.");
    }
}
