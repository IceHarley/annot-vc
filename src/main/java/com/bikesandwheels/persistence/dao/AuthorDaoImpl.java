//package com.bikesandwheels.persistence.dao;
//
//import com.bikesandwheels.persistence.model.Author;
//import org.hibernate.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Repository
//public class AuthorDaoImpl implements AuthorDao {
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    public AuthorDaoImpl() {
//    }
//
//    @Transactional
//    public List<Author> list() {
//        @SuppressWarnings("unchecked")
//        List<Author> authorList = (List<Author>) sessionFactory.getCurrentSession()
//                .createCriteria(Author.class)
//                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
//
//        return authorList;
//    }
//
//    @Transactional
//    public void saveOrUpdate(Author author) {
//        sessionFactory.getCurrentSession().saveOrUpdate(author);
//    }
//
//    @Transactional
//    public void delete(int id) {
//        Author author = new Author();
//        author.setAuthorId(id);
//        sessionFactory.getCurrentSession().delete(author);
//    }
//
//    @Transactional
//    public Author get(int id) {
//        String hql = String.format("from Author where id=%d", id);
//        Query query = sessionFactory.getCurrentSession().createQuery(hql);
//
//        @SuppressWarnings("unchecked")
//        List<Author> authorList = (List<Author>) query.list();
//
//        if (authorList != null && !authorList.isEmpty())
//            return authorList.get(0);
//
//        return null;
//    }
//}
