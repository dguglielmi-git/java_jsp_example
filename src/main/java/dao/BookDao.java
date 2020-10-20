package dao;

import javax.persistence.EntityManager;
import model.Book;

public class BookDao extends GenericDao<Book, Long> {
    
    public BookDao(EntityManager entityManager) {
        super(entityManager);
    }
    
}
