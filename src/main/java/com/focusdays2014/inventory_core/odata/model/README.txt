eclipse
- tunnel port 3306 to server port 3306 via ssh
- defined database connection via eclipse tools
- make project "JPA" available via nature 2.1
- add new "JPA entities from table"
- add 

ODATA example via JPA 2.0 provider
https://github.com/odata4j/odata4j/blob/0.7/odata4j-examples/src/main/java/org/odata4j/examples/producer/jpa/NorthwindJpaProducerExample.java

maven
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.25</version>
		</dependency>
		
		<!-- for JPA, use hibernate-entitymanager instead of hibernate-core -->
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-entitymanager</artifactId>
			<version>4.3.5.Final</version>
		</dependency>