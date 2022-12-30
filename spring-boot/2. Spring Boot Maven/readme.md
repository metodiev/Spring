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

<p>The layout property defaults to a value determined by the archive type (jar or war). The following
layouts are available:
<p>• JAR: regular executable JAR layout.
<p>• WAR: executable WAR layout. provided dependencies are placed in WEB-INF/lib-provided to avoid
any clash when the war is deployed in a servlet container.
<p>• ZIP (alias to DIR): similar to the JAR layout using PropertiesLauncher.
<p>• NONE: Bundle all dependencies and project resources. Does not bundle a bootstrap loader.

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
  
  <p> Disable layot
    
```sh
 <project>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <layers>
                        <enabled>false</enabled>
                    </layers>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
 ```

## Custom Layers Configuration
    
```sh
    <project>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <layers>
                        <enabled>true</enabled>
                        <configuration>${project.basedir}/src/layers.xml</configuration>
                    </layers>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```


<p> layers.xml

  
```sh
  <layers xmlns="http://www.springframework.org/schema/boot/layers"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.springframework.org/schema/boot/layers
  https://www.springframework.org/schema/boot/layers/layers3.0.xsd">
    <application>
        <into layer="spring-boot-loader">
            <include>org/springframework/boot/loader/**</include>
        </into>
        <into layer="application" />
    </application>
    <dependencies>
        <into layer="application">
            <includeModuleDependencies />
        </into>
        <into layer="snapshot-dependencies">
            <include>*:*:*SNAPSHOT</include>
        </into>
        <into layer="dependencies" />
    </dependencies>
    <layerOrder>
        <layer>dependencies</layer>
        <layer>spring-boot-loader</layer>
        <layer>snapshot-dependencies</layer>
        <layer>application</layer>
    </layerOrder>
</layers>
```
  

 <p>  The layers XML format is defined in three sections:
 <p>• The <application> block defines how the application classes and resources should be layered.
 <p>• The <dependencies> block defines how dependencies should be layered.
 <p>• The <layerOrder> block defines the order that the layers should be written.
  
  <p> r if you prefer to keep the original artifact and attach the repackaged one with a
different classifier, configure the plugin as shown in the following example:
    
 ```xml
    <project>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <executions>
                    <execution>
                        <id>repackage</id>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                        <configuration>
                            <classifier>exec</classifier>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>

 ```   
