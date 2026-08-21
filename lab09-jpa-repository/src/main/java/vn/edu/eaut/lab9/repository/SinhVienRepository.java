package vn.edu.eaut.lab9.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.edu.eaut.lab9.config.JPAUtil;
import vn.edu.eaut.lab9.model.SinhVien;

import java.util.List;

public class SinhVienRepository {

    public List<SinhVien> findAll() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT s FROM SinhVien s ORDER BY s.id DESC", SinhVien.class).getResultList();
        } finally {
            em.close();
        }
    }

    public SinhVien findById(Integer id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.find(SinhVien.class, id);
        } finally {
            em.close();
        }
    }

    public boolean existsByMaSinhVien(String maSinhVien, Integer excludedId) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            String jpql = "SELECT COUNT(s) FROM SinhVien s WHERE s.maSinhVien = :maSinhVien";
            if (excludedId != null) {
                jpql += " AND s.id <> :excludedId";
            }
            var query = em.createQuery(jpql, Long.class)
                    .setParameter("maSinhVien", maSinhVien);
            if (excludedId != null) {
                query.setParameter("excludedId", excludedId);
            }
            return query.getSingleResult() > 0;
        } finally {
            em.close();
        }
    }

    public void save(SinhVien sinhVien) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(sinhVien);
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx.isActive()) tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    public void update(SinhVien sinhVien) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(sinhVien);
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx.isActive()) tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    public void delete(Integer id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            SinhVien sv = em.find(SinhVien.class, id);
            if (sv != null) {
                em.remove(sv);
            }
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx.isActive()) tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    public List<SinhVien> search(String keyword) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            String jpql = "SELECT s FROM SinhVien s WHERE LOWER(s.hoTen) LIKE :kw OR LOWER(s.lop) LIKE :kw";
            return em.createQuery(jpql, SinhVien.class)
                    .setParameter("kw", "%" + keyword.toLowerCase() + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }
}