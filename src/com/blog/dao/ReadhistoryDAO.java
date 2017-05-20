package com.blog.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.blog.entity.Readhistory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Readhistory entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.blog.entity.Readhistory
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ReadhistoryDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ReadhistoryDAO.class);
	// property constants
	public static final String UID = "uid";
	public static final String AID = "aid";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Readhistory transientInstance) {
		log.debug("saving Readhistory instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Readhistory persistentInstance) {
		log.debug("deleting Readhistory instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Readhistory findById(java.lang.Integer id) {
		log.debug("getting Readhistory instance with id: " + id);
		try {
			Readhistory instance = (Readhistory) getCurrentSession().get(
					"com.blog.entity.Readhistory", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Readhistory> findByExample(Readhistory instance) {
		log.debug("finding Readhistory instance by example");
		try {
			List<Readhistory> results = (List<Readhistory>) getCurrentSession()
					.createCriteria("com.blog.entity.Readhistory")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Readhistory instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Readhistory as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Readhistory> findByUid(Object uid) {
		return findByProperty(UID, uid);
	}

	public List<Readhistory> findByAid(Object aid) {
		return findByProperty(AID, aid);
	}

	public List findAll() {
		log.debug("finding all Readhistory instances");
		try {
			String queryString = "from Readhistory";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Readhistory merge(Readhistory detachedInstance) {
		log.debug("merging Readhistory instance");
		try {
			Readhistory result = (Readhistory) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Readhistory instance) {
		log.debug("attaching dirty Readhistory instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Readhistory instance) {
		log.debug("attaching clean Readhistory instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReadhistoryDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ReadhistoryDAO) ctx.getBean("ReadhistoryDAO");
	}
}