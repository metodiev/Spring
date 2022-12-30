### Agenda:
## Spring Using the Plugin



Inheriting the Starter Parent POM:
```sh
<!-- Inherit defaults from Spring Boot -->
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>3.0.1</version>
</parent>

```

Without Starter parent POM:
```sh
<dependencyManagement>
  <dependencies>
  <dependency>
  <!-- Import dependency management from Spring Boot -->
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-dependencies</artifactId>
  <version>3.0.1</version>
  <type>pom</type>
  <scope>import</scope>
  </dependency>
  </dependencies>
</dependencyManagement>
```
