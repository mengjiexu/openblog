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

import com.blog.entity.Fensi;

/**
 * A data access object (DAO) providing persistence and search support for Fensi
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.blog.entity.Fensi
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class FensiDAO {
	private static final Logger log = LoggerFactory.getLogger(FensiDAO.class);
	// property constants
	public static final String UID = "uid";
	public static final String FENSIID = "fensiid";

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

	public void save(Fensi transientInstance) {
		log.debug("saving Fensi instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Fensi persistentInstance) {
		log.debug("deleting Fensi instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Fensi findById(java.lang.Integer id) {
		log.debug("getting Fensi instance with id: " + id);
		try {
			Fensi instance = (Fensi) getCurrentSession().get(
					"com.blog.entity.Fensi", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Fensi> findByExample(Fensi instance) {
		log.debug("finding Fensi instance by example");
		try {
			List<Fensi> results = (List<Fensi>) getCurrentSession()
					.createCriteria("com.blog.entity.Fensi")
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
		log.debug("finding Fensi instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Fensi as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Fensi> findByUid(Object uid) {
		return findByProperty(UID, uid);
	}

	public List<Fensi> findByFensiid(Object fensiid) {
		return findByProperty(FENSIID, fensiid);
	}

	public List findAll() {
		log.debug("finding all Fensi instances");
		try {
			String queryString = "from Fensi";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Fensi merge(Fensi detachedInstance) {
		log.debug("merging Fensi instance");
		try {
			Fensi result = (Fensi) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Fensi instance) {
		log.debug("attaching dirty Fensi instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Fensi instance) {
		log.debug("attaching clean Fensi instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FensiDAO getFromApplicationContext(ApplicationContext ctx) {
		return (FensiDAO) ctx.getBean("FensiDAO");
	}
}