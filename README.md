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

First of all, you must register an application on the OVH side. If you are unfamiliar with the concepts 
specific to the OVH API authentication, you may want to catch up [here](https://api.ovh.com/g934.first_step_with_api).  
You can create a [regular app](https://eu.api.ovh.com/createApp/), which allows you to authenticate against multiple users, or 
a [script](https://eu.api.ovh.com/createToken/), in which case you register a single user once.

Then, it is time to use the wrapper : include the Jar file as a dependency, then import the classes :

```java
import fr.rabian.ovhApi.core.beans.*;
```

You can then create the application you registered earlier, get its manager, and get your first consumer (if needed) :

```java
Application a = new Application(AK, AS, Endpoint.ovh_eu);
AppManager am = a.getAppManager();
Consumer c;

ScopeElement g = new ScopeElement("GET", "/*");
List<ScopeElement> scope = new ArrayList<>();
scope.add(g);
                     
try {
    c = am.getConsumer(scope, "");
    System.out.println(c.getValidationUrl());
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

