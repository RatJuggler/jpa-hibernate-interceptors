package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Iterator;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

@Component
public class HibernateInterceptor extends EmptyInterceptor {

	private static final Logger log = LoggerFactory.getLogger(HibernateInterceptor.class);

  @Override
	public boolean onFlushDirty(
			Object entity, 
			Serializable id, 
			Object[] currentState, 
			Object[] previousState, 
			String[] propertyNames, 
			Type[] types) {
    log.info("Entity is dirty durring flush: {}", entity);
    return false;
	}

	@Override
	public boolean onLoad(
			Object entity, 
			Serializable id, 
			Object[] state, 
			String[] propertyNames, 
			Type[] types) {
    log.info("Before entity loaded from database: {}", entity);
    return false;
	}

	@Override
	public boolean onSave(
			Object entity, 
			Serializable id, 
			Object[] state, 
			String[] propertyNames, 
			Type[] types) {
    log.info("Before entity saved to database: {}", entity);
    return false;
	}

	@Override
	public void postFlush(Iterator entities) {
    log.info("After flush that pushed entities to the database.");
	}

	@Override
	public void preFlush(Iterator entities) {
    log.info("Before flush to push entities to the database.");
	}
}
