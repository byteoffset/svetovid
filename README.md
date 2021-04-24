# svetovid

### Description

Svetovid is a library what simplify reading properties from file.

Library allows you to use environment variables in property file.

### Properties file
* Svetovid by default is looking for `application.properties` in `resources` directory in you Java project.
* `application.property` can hold 'references' to environment variables. To use that functionality, you have to write environment variable 'reference' using such notation:
```
${ENVIRONMENT_VARIABLE_NAME}
```
* Please check example `application.properties` bellow:
```
enable.authentication=true
property0=foo bar bas
property1=32.64f

url.property=http://example.org
random.string.property=2f^&-)_(s7df 87a8 f0a708f2fxf")(U9
java.home=${JAVA_HOME}
```

### Usage
* Svetovid is based on annotations. In case, when class field must be read from `application.properties`, you need to use `@ApplicationProperty` annotation with one string argument. Annotation takes one of property name.
* To inject value to your class field you have to call `ApplicationPropertiesInjector.inject`. Method takes one argument, and it is object, where injection will be done.

### Example
Please check bellow class:
```
public class AuthenticationConfiguration {
    @ApplicationProperty("enable.authentication")
    private boolean isAuthenticationEnabled;

    AuthenticationConfiguration() {
        ApplicationPropertiesInjector.inject(this);
    }
}
```
