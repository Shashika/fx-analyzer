## FX-Analyzer (Foriegn Exchange Analyzer)

Java web application to analyze foreign exchange deals. Application is capable to handle thousands of records in CSV format within few seconds.

![Alt text](src/main/webapp/resources/images/screenshot.png?raw=true "Title")

 **CSV format** 
1. Record ID
2. Currency From (ISO format)
3. Currency To (ISO format)
4. Timestamp
5. Tansaction Amount

Application contains a dummy CSV file creator with random data `CSVCreator`. Generated file will be stored `src/main/resources/files` directory.


## Deployment

**tomcat7-maven-plugin**

```mvn clean install tomcat7:undeploy tomcat7:deploy```

**docker**

```docker run -it --rm -v "$(pwd)":/opt/maven -w /opt/maven maven:3.3.9-jdk-8 mvn clean install```
