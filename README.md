# game2048-test

This project automates [2048](http://gabrielecirulli.github.io/2048/) web game.

### How to run
**1. From comand line:**

Run `mvn clean -Dtest.browser=safari test -D=Game2048MainPageTest` from project root directory to start the test.

You can choose browser by changing `-Dtest.browser` param.

**2. From your favourite IDE:**

Import the project and run `Game2048MainPageTest` class as simple TestNG test.

You can choose browser by changing `default_browser` property in the `project.properties` file.

### Known issues:
**Safari browser:** 

For SafariDriver 2.45.0 you have to install SafariDriver [Extension](https://github.com/SeleniumHQ/selenium/wiki/SafariDriver#getting-started) manually

**Chrome browser:**

As for now, project supports only MacOS and Windows ChromeDriver. 
