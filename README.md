# Testing @restful-booker-platform
Small project for automatic, API and stress tests on the [Restful-booker website](https://automationintesting.online/).

<br>

## Features

**User page tests**

Automatic tests for submitting a booking or inserting contact info.
Tests only UI.

<br>

**Admin page tests**

Automatic tests for authenticating and adding rooms.
Tests both API response and UI.

<br>

**JMeter stress tests**

Tested using REST get calls.

<br>

**Reports**

Automatic tests are generated using the [Maven Surefire Report Plugin](https://maven.apache.org/surefire/maven-surefire-report-plugin/), located in 
<i>./testp/target/surefire-reports</i>.

JMeter reports are located in <i>./jmeter/report</i> folder.

<br>

## Help

**Generate JMeter reports**

Example:

    .../apache-jmeter-5.5/bin/jmeter -f -n -t ./api_stress_load_test.jmx -l loadtest.csv -e -o report


Structure: 


    {jmeter run file} -f -n -t {data export location} -e -o {report folder location}


<br>

**Surefire reports**

Surefire reports are generated automatically by running the following command in IDE terminal (using CTRL + ENTER)

    mvn test
  
  
<br>

## Tech

**Environment:** [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/#section=linux), [Apache JMeter](https://jmeter.apache.org/)

**Languages:** Java

**Other:** [Maven](https://maven.apache.org/), [Selenium](https://www.selenium.dev/)

<div>
  <img src="https://github.com/devicons/devicon/blob/master/icons/java/java-original-wordmark.svg" title="Java" alt="Java" width="70" height="70"/>&nbsp;
  <img src="https://github.com/devicons/devicon/blob/master/icons/apache/apache-plain-wordmark.svg" title="Apache" **alt="Apache" width="70" height="70"/>&nbsp;&nbsp;&nbsp;
    <img src="https://github.com/devicons/devicon/blob/master/icons/intellij/intellij-original.svg" title="Intellij" **alt="Intellij" width="55" height="55"/>&nbsp;&nbsp;&nbsp;
            <img src="https://github.com/devicons/devicon/blob/master/icons/selenium/selenium-original.svg" title="Selenium" **alt="Selenium" width="55" height="55"/>&nbsp;
</div>

<br>

## References

- [Restful-booker - Github](https://github.com/mwinteringham/restful-booker-platform)

- [Restful-booker - API](https://restful-booker.herokuapp.com/apidoc/index.html)

- [Restful-booker - Website](https://automationintesting.online/)

<br>

## Authors

- [@v-gabriel](https://github.com/v-gabriel)

