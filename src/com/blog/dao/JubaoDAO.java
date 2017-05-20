package com.blog.dao;

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

import com.blog.entity.Jubao;

/**
 * A data access object (DAO) providing persistence and search support for Jubao
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.blog.entity.Jubao
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class JubaoDAO {
	private static final Logger log = LoggerFactory.getLogger(JubaoDAO.class);
	// property constants
	public static final String FROMUID = "fromuid";
	public static final String TOUID = "touid";
	public static final String CONTENT = "content";
	public static final String ISCHECK = "ischeck";

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

	public void save(Jubao transientInstance) {
		log.debug("saving Jubao instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Jubao persistentInstance) {
		log.debug("deleting Jubao instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Jubao findById(java.lang.Integer id) {
		log.debug("getting Jubao instance with id: " + id);
		try {
			Jubao instance = (Jubao) getCurrentSession().get(
					"com.blog.entity.Jubao", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Jubao> findByExample(Jubao instance) {
		log.debug("finding Jubao instance by example");
		try {
			List<Jubao> results = (List<Jubao>) getCurrentSession()
					.createCriteria("com.blog.entity.Jubao")
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
		log.debug("finding Jubao instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Jubao as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Jubao> findByFromuid(Object fromuid) {
		return findByProperty(FROMUID, fromuid);
	}

	public List<Jubao> findByTouid(Object touid) {
		return findByProperty(TOUID, touid);
	}

	public List<Jubao> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List<Jubao> findByIscheck(Object ischeck) {
		return findByProperty(ISCHECK, ischeck);
	}

	public List findAll() {
		log.debug("finding all Jubao instances");
		try {
			String queryString = "from Jubao";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Jubao merge(Jubao detachedInstance) {
		log.debug("merging Jubao instance");
		try {
			Jubao result = (Jubao) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Jubao instance) {
		log.debug("attaching dirty Jubao instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Jubao instance) {
		log.debug("attaching clean Jubao instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static JubaoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (JubaoDAO) ctx.getBean("JubaoDAO");
	}
}