## Appium Realm

Project to practice mobile test automation utilizing not only appium.

What can be found inside:
- Appium, run programmatically
- SauceLabs, BrowserStack
- Selenium WebDriver
- native app testing on Android
- web page testing on Android stock browser
- modular framework design
- JUnit, TestNG runners

### Build project
~~~
mvn clean
mvn verify
~~~

## Notes
__Override Cucumber Options__

Maven goal to run integration tests with surefire plugin.
Run files *Test.class by default. Overridden in POM to run testng.xml.

~~~
mvn verify -Dcucumber.options="--tags @debug PATH/TO/FEATURE"
~~~

## Tips & tricks
[Upload app to SauceLabs storage](https://wiki.saucelabs.com/display/DOCS/Uploading+Mobile+Applications+to+Sauce+Storage+for+Testing)

[Cucumber run by TestNG](https://medium.com/agile-vision/cucumber-bdd-part-2-creating-a-sample-java-project-with-cucumber-testng-and-maven-127a1053c180)

[Cucumber feature/glue lookup](https://docs.cucumber.io/cucumber/api/#from-the-command-line)

[SauceLabs DesiredCaps configurator](https://wiki.saucelabs.com/display/DOCS/Platform+Configurator
)
