# Hibernate metamodel and Lombok

Small demo project which combines the Hibernate Metamodel with Lombok.

The interesting part is in the pom.xml:

```xml

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <configuration>
        <annotationProcessorPaths>
            <path>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>${lombok.version}</version>
            </path>
            <path>
                <groupId>org.hibernate</groupId>
                <artifactId>hibernate-jpamodelgen</artifactId>
                <version>${hibernate.version}</version>
            </path>
        </annotationProcessorPaths>
    </configuration>
</plugin>
```

This configures the Maven compiler plugin to pick up the Lombok and the metamodel generator annotation processors.

The `hibernate-jpamodelgen` writes the generated metamodel classes into `target/generated-sources/annotations`, where
Maven picks it up.

This works in IntelliJ too, as long as you enable annotation processing in the settings.

The metamodel classes are used in the `TaskRepository2` class.