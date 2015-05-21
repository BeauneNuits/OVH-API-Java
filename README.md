# OVH API Java

This is a small wrapper for the OVH API in Java. It aims to provide tools to handle the most off-putting part of using this API.

## Installation

If you have a Maven installation, navigate to the folder in which you cloned the repo, and type :
 
```bash
mvn package
```

to compile and package the sources into a JAR file (usual output directory is 'target'). To install the packages inside your local Maven repository, type :
 
```bash
mvn install
```

You can also fetch artifcats from a custom repository :

```
<repositories>
    <repository>
        <id>nexus-rabian-public-releases</id>
        <url>http://unsec.maven.rabian.fr/content/repositories/releases</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>fr.rabian.ovhApi</groupId>
        <artifactId>OVH-API-core</artifactId>
        <version>0.1.1</version>
    </dependency>
</dependencies>
```

If you can not / do not want to use Maven, check the latest binaries in [Nexus](http://unsec.maven.rabian.fr/#nexus-search;gav~fr.rabian.ovhApi~OVH-API-core~~~).

## Sample usage

Include the Jar file as a dependency, then import the classes :

```java
import fr.rabian.ovhApi.core.beans.*;
```

You can then create an application and its manager, and get your first consumer :

```java
Application a = new Application(AK, AS, Endpoint.ovh_eu);
AppManager am = a.getAppManager();
Consumer c;

ScopeElement g = new ScopeElement("GET", "/*");
List<ScopeElement> scope = new ArrayList<>();
scope.add(g);
                     
try {
    c = am.getConsumer(scope, "");
    System.out.println(nc.getConsumerKey());
    Scanner in = new Scanner(System.in);
    String wait = in.nextLine();
    System.out.println(am.sendGetReq("/me", c));
} catch (NOKResponseException e) {
    e.printStackTrace();
}
```

For further documentation : 
- Check the sources
- Generate the javadoc locally using maven `mvn javadoc:javadoc`
- Look at the javadoc [online](http://unsec.maven.rabian.fr/content/sites/ovh-api-site/apidocs/index.html)

## License

This software is licensed under GNU GPL v3 license. Check the [license](https://github.com/BeauneNuits/OVH-API-Java/blob/master/License.txt) file for details.

