FROM jenkins

USER root
RUN apt-get update && apt-get install -y vim curl openssh-server

#USER jenkins

#COPY plugins.txt /usr/share/jenkins/ref/
#COPY batch-install-jenkins-plugins.sh /usr/share/jenkins/ref/
#RUN /usr/local/bin/install-plugins.sh cobertura
#RUN /usr/share/jenkins/ref/batch-install-jenkins-plugins.sh -p /usr/share/jenkins/ref/plugins.txt  -d /var/lib/jenkins/plugins/

#ADD JobCreatorConfig.xml /tmp/config.xml
#RUN chown -R jenkins:root /var/jenkins_home/jobs/JobsCreator