# jpa-hibernate-interceptors

Experimenting with JPA Hibernate interceptors.

Useful links:

- <https://docs.jboss.org/hibernate/orm/5.4/userguide/html_single/Hibernate_User_Guide.html#events>
- <https://docs.jboss.org/hibernate/orm/5.4/javadocs/org/hibernate/Interceptor.html>
- <https://docs.jboss.org/hibernate/jpa/2.1/api/javax/persistence/package-summary.html>

Pros

- More fined grained access to the persistence lifecycle.
- Full access to the entity being loaded / persisted.

Cons

- Applies to all entites in the persistence session.
- Can leave the presistence session / entity in a dirty state when you change entity attributes in some intercepts.
- Greater understanding of the persistence lifecycle required.
- Can be difficult to configure in a SpringBoot environment.
- Hibernate specific.
