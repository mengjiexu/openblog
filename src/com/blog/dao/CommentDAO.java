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

import com.blog.entity.Comment;

/**
 * A data access object (DAO) providing persistence and search support for
 * Comment entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.blog.entity.Comment
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class CommentDAO {
	private static final Logger log = LoggerFactory.getLogger(CommentDAO.class);
	// property constants
	public static final String CONTENT = "content";
	public static final String FROMID = "fromid";
	public static final String TOID = "toid";
	public static final String AID = "aid";
	public static final String TAG = "tag";
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

	public void save(Comment transientInstance) {
		log.debug("saving Comment instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Comment persistentInstance) {
		log.debug("deleting Comment instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Comment findById(java.lang.Integer id) {
		log.debug("getting Comment instance with id: " + id);
		try {
			Comment instance = (Comment) getCurrentSession().get(
					"com.blog.entity.Comment", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Comment> findByExample(Comment instance) {
		log.debug("finding Comment instance by example");
		try {
			List<Comment> results = (List<Comment>) getCurrentSession()
					.createCriteria("com.blog.entity.Comment")
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
		log.debug("finding Comment instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Comment as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Comment> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List<Comment> findByFromid(Object fromid) {
		return findByProperty(FROMID, fromid);
	}

	public List<Comment> findByToid(Object toid) {
		return findByProperty(TOID, toid);
	}

	public List<Comment> findByAid(Object aid) {
		return findByProperty(AID, aid);
	}

	public List<Comment> findByTag(Object tag) {
		return findByProperty(TAG, tag);
	}

	public List<Comment> findByIscheck(Object ischeck) {
		return findByProperty(ISCHECK, ischeck);
	}

	public List findAll() {
		log.debug("finding all Comment instances");
		try {
			String queryString = "from Comment";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Comment merge(Comment detachedInstance) {
		log.debug("merging Comment instance");
		try {
			Comment result = (Comment) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Comment instance) {
		log.debug("attaching dirty Comment instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Comment instance) {
		log.debug("attaching clean Comment instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CommentDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CommentDAO) ctx.getBean("CommentDAO");
	}
}