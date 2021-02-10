# SpigotResourcesAPI
a Java wrapper of the Spigot resources REST API ([XenforoResourceManagerAPI](https://github.com/SpigotMC/XenforoResourceManagerAPI))

SpigotResourcesAPI aims to be simple, thread-safe and efficient.

[![Release](https://jitpack.io/v/robertlit/SpigotResourcesAPI.svg)](https://jitpack.io/#robertlit/SpigotResourcesAPI)

[Javadoc](https://jitpack.io/com/github/robertlit/SpigotResourcesAPI/latest/javadoc/)

[See on SpigotMC](https://www.spigotmc.org/threads/spigotresourcesapi-get-information-about-resources-and-authors.447967/)



# How to use
Currently, SpigotResourcesAPI is only available through JitPack.
In the future, it may be deployed to maven central, and will be deployed to GitHub Packages if authentication will not be required.

## Maven
Add JitPack as a repository
``` xml
<repositories>
  <!--   it is recommended to specify JitPack after all other repositories   -->
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```
Add SpigotResourcesAPI as a dependency
``` xml
<dependencies>
  <dependency>
    <groupId>com.github.robertlit</groupId>
    <artifactId>SpigotResourcesAPI</artifactId>
    <version>1.2</version>
  </dependency>
</dependencies>
```

## Gradle
Add JitPack as a repository
```
repositories {
    maven { url 'https://jitpack.io' }
}
```
Add SpigotResourcesAPI as a dependency
```
dependencies {
    implementation 'com.github.robertlit:SpigotResourcesAPI:1.2'
}
```

Or, clone the repository, install the artifact locally (using ```mvn install```, for example)
and use the artifact information that is in the ```pom.xml``` file as a dependency.

# Code exmaples
``` Java
// Construct an API and specify how long should data be cached for
SpigotResourcesAPI api = new SpigotResourcesAPI(1, TimeUnit.HOURS);

// Get Author by id
CompletableFuture<Author> future = api.getAuthor(740512);
future.thenAccept(author -> {
  // ...
});

// Get Resources by Author id
CompletableFuture<Collection<Resource>> future = api.getResourcesByAuthor(740512);
future.thenAccept(resources -> {
  // ...
});

// Get Resource by id
CompletableFuture<Resource> future = api.getResource(72343);
future.thenAccept(resource -> {
  // ...
});
```


If this has helped you, please consider [donating via PayPal](https://www.paypal.me/robertlitmc).