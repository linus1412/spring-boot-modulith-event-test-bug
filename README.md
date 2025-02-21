# Event Bug Reproducer

Using Spring 3.3 and Spring Modulith 1.2 you could use ApplicationContextRunner as a lightweight way to test you event listeners responded to events when annotated with @ApplicationModuleListener.

After upgrading to Spring Boot 3.4 and Modulith 1.3 this no longer works.  Listenters annotated with @EventListener do still work.

The EventProcessingTest class fails on the main branch but passes on the spring-3-3-modulith-1-2 branch.
