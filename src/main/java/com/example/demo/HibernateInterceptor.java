package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Base64;
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
    log.info("OnFlushDirty: Entity is dirty: {}", entity);
    // Need to filter to find entities with secrets.
    if (entity instanceof Customer) {
      // Need to check if other fields have changed besides the secret.
      // Should proceed ONLY if it's just the secret has that has changed.
      Customer customer = (Customer) entity;
      String encodedSecret = customer.getSecret();
      byte[] decodedBytes = Base64.getDecoder().decode(encodedSecret);
      String decodedSecret = new String(decodedBytes);
      log.info("OnFlushDirty: Secret decoded: {} -> {}", encodedSecret, decodedSecret);
      customer.setSecret(decodedSecret); // This has no effect as the entity is reloaded with the current state.
      customer.setEncoded(false);
      // Just changing the current state results in the decoded secret being
      // persisted!
      currentState[2] = decodedSecret; // Should lookup the index from the propertyNames.
      // But if we also change the previous state then Hibernate is fooled into
      // thinking there is no change!
      previousState[2] = decodedSecret;
      log.info("OnFlushDirty: currentState and previousState changed to fool Hibernate!");
      return true; // Have to return true so the new currentState is picked up.
    }
    return false;
  }

  @Override
  public boolean onLoad(
      Object entity,
      Serializable id,
      Object[] state,
      String[] propertyNames,
      Type[] types) {
    log.info("OnLoad: Before entity loaded from database: {}", entity);
    return false;
  }

  @Override
  public boolean onSave(
      Object entity,
      Serializable id,
      Object[] state,
      String[] propertyNames,
      Type[] types) {
    log.info("OnSave: Before entity saved to database: {}", entity);
    return false;
  }

  @Override
  public void postFlush(Iterator entities) {
    log.info("PostFlush: After flush that pushed entities to the database.");
  }

  @Override
  public void preFlush(Iterator entities) {
    log.info("PreFlush: Before flush.");
  }

  @Override
  public int[] findDirty(
      Object entity,
      Serializable id,
      Object[] currentState,
      Object[] previousState,
      String[] propertyNames,
      Type[] types) {
    log.info("FindDirty: Is entity dirty: {}", entity);
    return null;
  }
}
