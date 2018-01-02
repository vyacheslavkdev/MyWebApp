package ru.develop.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.develop.model.Book;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao{
    private static final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);
        logger.info("КНИГА ДОБАВЛЕНА. Info: " + book);
    }

    @Override
    public void updateBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.update(book);
        logger.info("КНИГА ОБНОВЛЕНА. Info: " + book);
    }

    @Override
    public void removeBook(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, id);
        if (book != null){
            session.delete(book);
        }
        logger.info("КНИГА УДАЛЕНА. Info: " + book);
    }

    @Override
    public Book getBookById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, id);
        logger.info("КНИГА ЗАГРУЖЕНА. Info: " + book);
        return book;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> listBooks() {
        Session session = sessionFactory.getCurrentSession();
        List<Book> bookList = (List<Book>)session.createQuery("from Book").list();
        for(Book book:bookList){
            logger.info("Book list: " + book);
        }
        return bookList;
    }
}
