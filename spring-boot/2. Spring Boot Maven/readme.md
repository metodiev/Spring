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

## Packaging Executable Archives
Packaging an executable archive is performed by the repackage goal, as shown in the following
example:

```sh
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <executions>
                <execution>
                    <goals>
                        <goal>repackage</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

The layout property defaults to a value determined by the archive type (jar or war). The following
layouts are available:
• JAR: regular executable JAR layout.
• WAR: executable WAR layout. provided dependencies are placed in WEB-INF/lib-provided to avoid
any clash when the war is deployed in a servlet container.
• ZIP (alias to DIR): similar to the JAR layout using PropertiesLauncher.
• NONE: Bundle all dependencies and project resources. Does not bundle a bootstrap loader.

```sh
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <mainClass>${start.class}</mainClass>
                <layout>ZIP</layout>
            </configuration>
            <executions>
                <execution>
                    <goals>
                        <goal>repackage</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>

```
