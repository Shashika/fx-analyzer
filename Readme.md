mvn clean install tomcat7:undeploy tomcat7:deploy

docker run -it --rm --name FxAnalyzerProject -v "$PWD":/home/shashika/installs/apache-maven-3.3.9 -w /home/shashika/installs/apache-maven-3.3.9 maven:3.3.9-jdk-8 mvn clean install

docker run -it --rm \
       -v "$(pwd)":/opt/maven \
       -w /opt/maven \
       maven:3.2-jdk-7 \
       mvn clean install