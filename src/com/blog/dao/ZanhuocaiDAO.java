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

import com.blog.entity.Zanhuocai;

/**
 * A data access object (DAO) providing persistence and search support for
 * Zanhuocai entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.blog.entity.Zanhuocai
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ZanhuocaiDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ZanhuocaiDAO.class);
	// property constants
	public static final String UID = "uid";
	public static final String AID = "aid";
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

	public void save(Zanhuocai transientInstance) {
		log.debug("saving Zanhuocai instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Zanhuocai persistentInstance) {
		log.debug("deleting Zanhuocai instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Zanhuocai findById(java.lang.Integer id) {
		log.debug("getting Zanhuocai instance with id: " + id);
		try {
			Zanhuocai instance = (Zanhuocai) getCurrentSession().get(
					"com.blog.entity.Zanhuocai", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Zanhuocai> findByExample(Zanhuocai instance) {
		log.debug("finding Zanhuocai instance by example");
		try {
			List<Zanhuocai> results = (List<Zanhuocai>) getCurrentSession()
					.createCriteria("com.blog.entity.Zanhuocai")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	/**
	 * 通过uid和aid获得赞或踩的记录
	 * @param userid
	 * @param articleid
	 * @return
	 */
	public List findByUidAndArticleId(int userid,int articleid){
		try{
			String queryString = "from Zanhuocai as model where model."
					+ "uid= ? and aid=?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, userid);
			queryObject.setParameter(1,articleid);
			return queryObject.list();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Zanhuocai instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Zanhuocai as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Zanhuocai> findByUid(Object uid) {
		return findByProperty(UID, uid);
	}

	public List<Zanhuocai> findByAid(Object aid) {
		return findByProperty(AID, aid);
	}

	public List<Zanhuocai> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findAll() {
		log.debug("finding all Zanhuocai instances");
		try {
			String queryString = "from Zanhuocai";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Zanhuocai merge(Zanhuocai detachedInstance) {
		log.debug("merging Zanhuocai instance");
		try {
			Zanhuocai result = (Zanhuocai) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Zanhuocai instance) {
		log.debug("attaching dirty Zanhuocai instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Zanhuocai instance) {
		log.debug("attaching clean Zanhuocai instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ZanhuocaiDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ZanhuocaiDAO) ctx.getBean("ZanhuocaiDAO");
	}
}