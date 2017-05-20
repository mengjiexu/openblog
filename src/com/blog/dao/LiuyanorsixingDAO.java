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

import com.blog.entity.Liuyanorsixing;

/**
 * A data access object (DAO) providing persistence and search support for
 * Liuyanorsixing entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.blog.entity.Liuyanorsixing
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class LiuyanorsixingDAO {
	private static final Logger log = LoggerFactory
			.getLogger(LiuyanorsixingDAO.class);
	// property constants
	public static final String FROMID = "fromid";
	public static final String TOID = "toid";
	public static final String CONTENT = "content";
	public static final String TYPE = "type";

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

	public void save(Liuyanorsixing transientInstance) {
		log.debug("saving Liuyanorsixing instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Liuyanorsixing persistentInstance) {
		log.debug("deleting Liuyanorsixing instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Liuyanorsixing findById(java.lang.Integer id) {
		log.debug("getting Liuyanorsixing instance with id: " + id);
		try {
			Liuyanorsixing instance = (Liuyanorsixing) getCurrentSession().get(
					"com.blog.entity.Liuyanorsixing", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Liuyanorsixing> findByExample(Liuyanorsixing instance) {
		log.debug("finding Liuyanorsixing instance by example");
		try {
			List<Liuyanorsixing> results = (List<Liuyanorsixing>) getCurrentSession()
					.createCriteria("com.blog.entity.Liuyanorsixing")
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
		log.debug("finding Liuyanorsixing instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Liuyanorsixing as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Liuyanorsixing> findByFromid(Object fromid) {
		return findByProperty(FROMID, fromid);
	}

	public List<Liuyanorsixing> findByToid(Object toid) {
		return findByProperty(TOID, toid);
	}

	public List<Liuyanorsixing> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List<Liuyanorsixing> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findAll() {
		log.debug("finding all Liuyanorsixing instances");
		try {
			String queryString = "from Liuyanorsixing";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Liuyanorsixing merge(Liuyanorsixing detachedInstance) {
		log.debug("merging Liuyanorsixing instance");
		try {
			Liuyanorsixing result = (Liuyanorsixing) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Liuyanorsixing instance) {
		log.debug("attaching dirty Liuyanorsixing instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Liuyanorsixing instance) {
		log.debug("attaching clean Liuyanorsixing instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LiuyanorsixingDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LiuyanorsixingDAO) ctx.getBean("LiuyanorsixingDAO");
	}
}