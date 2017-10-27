package utilitarios;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.hibernate.Session;



public class DAOGenerico {

    EntityManager em;

    public List lista(Class classe) {
        em = Banco.getInstancia().getEm();
        
        Query q = em.createQuery("from " + classe.getSimpleName());
        
        return q.getResultList();
    }

    public void inserir(Object obj) {
        em = Banco.getInstancia().getEm();
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
        
    }

    public void excluir(Object objeto) throws Exception {
        em = Banco.getInstancia().getEm();
        em.getTransaction().begin();
        Method getChave = objeto.getClass().getMethod("getId", new Class[0]);
        objeto = em.find(objeto.getClass(), getChave.invoke(objeto, new Object[0]));
        em.remove(objeto);
        em.getTransaction().commit();

    }

    public void salvar(Object objeto) {
        em = Banco.getInstancia().getEm();
        em.getTransaction().begin();
        em.merge(objeto);
        em.getTransaction().commit();
    }

    public Object recupera(Class classe, Long id) {
        em = Banco.getInstancia().getEm();
        Object retornando = null;
        
        retornando = em.find(classe, id);
        
        return retornando;
    }
}

