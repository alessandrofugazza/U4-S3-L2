package pratica;

import pratica.entities.Evento;
import pratica.entities.EventoDAO;
import pratica.entities.TipoEvento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.UUID;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneventi");

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
        try {
            EventoDAO sd = new EventoDAO(em);

            Evento evento = new Evento("test title", new Date(2023, 10, 18), "test descr", TipoEvento.PRIVATO, 18);
            sd.save(evento);

            Evento event = sd.getById(UUID.fromString("64d1fd91-c03e-481f-995c-3e3db41c3764"));
            if (event != null)
                System.out.println(event);

            sd.delete(UUID.fromString("64d1fd91-c03e-481f-995c-3e3db41c3764"));

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
}
