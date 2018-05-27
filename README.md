# EventDispatcher

This is a fork/adaption of the event dispatching system from v2.X of 
[Discord4J](https://github.com/Discord4J/Discord4J/). It has been adapted 
such that it can be used independently of Discord4J.

## Notice
This is the culmination of ~3 years of development so the current codebase 
should be stable enough to use in a production environment. However, it will
no longer receive regular updates as the focus of development is on v3 of 
Discord4J. That being said, pull requests and issue reports are still encouraged.

## Using the EventDispatcher
### Importing the project
Using maven:
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.github.Discord4J</groupId>
    <artifactId>EventDispatcher</artifactId>
    <version>1.0.0</version>
  </dependency>
</dependencies>
```
Using gradle:
```groovy
repositories {
  maven {
    url  "https://jitpack.io"
  }
}

dependencies {
  compile "com.github.Discord4J:EventDispatcher:1.0.0"
}
```
### Dealing with events
Dispatching events:
```java
EventDispatcher dispatcher = new EventDispatcher(new CallerRunsPolicy(), 1,
                                                 Runtime.getRuntime().availableProcessors() * 4,
                                                 256, 60L, TimeUnit.SECONDS);

MyEvent event = new MyEvent(); //This implements Event
dispatcher.dispatch(event);
```
Listening to events:
```java
//Via IListener:
MyListener ilistener = new IListener<MyEvent>() {
    @Override
    public void handle(MyEvent event) {
        System.out.println("MyEvent received!");
    }
};

//Via EventSubscriber
Object subscriber = new Object() {
    @EventSubscriber
    public void handle(MyEvent event) {
        System.out.println("MyEvent received!");
    }
};

//Registering the listeners
dispatcher.registerListeners(ilistener, subscriber);
```
