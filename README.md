# jeed
## Introduction to Jakarta Enterprise Edition - JPA (Hibernate)

### Used technologies
- Java 21
- Apache Tomcat 10.1
- Hibernate ORM 6.6
- Maven 3
    - See pom.xml for details
- Eclipse IDE
    - Set Project Properties, Facets, Runtime to Tomcat 10.1

### As JDBC resource almost any other RDBMS should do
- For setup see [hron](https://github.com/egalli64/hron)
- Let Tomcat know how to connect to it
  - The JDBC jar should be placed in Tomcat lib folder
  - Add a resource element in context.xml with name jdbc/hron
    - See META-INF/context.xml for details
