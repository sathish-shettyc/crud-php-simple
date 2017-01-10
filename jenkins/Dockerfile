FROM jenkins

USER root
RUN apt-get update && apt-get install -y vim curl openssh-server

#USER jenkins

#COPY plugins.txt /usr/share/jenkins/ref/
#COPY batch-install-jenkins-plugins.sh /usr/share/jenkins/ref/
#RUN /usr/local/bin/install-plugins.sh cobertura
#RUN /usr/share/jenkins/ref/batch-install-jenkins-plugins.sh -p /usr/share/jenkins/ref/plugins.txt  -d /var/lib/jenkins/plugins/

COPY batch-install-jenkins-plugins.sh /tmp/batch-install-jenkins-plugins.sh
COPY plugins.txt /tmp/plugins.txt
RUN  mkdir -p /usr/share/jenkins/ref/plugins
RUN  chmod +x /tmp/batch-install-jenkins-plugins.sh
RUN  /tmp/batch-install-jenkins-plugins.sh --plugins /tmp/plugins.txt  --plugindir /usr/share/jenkins/ref/plugins
 
RUN mkdir -p /var/jenkins_home/jobs/JobsCreator
COPY JobCreatorConfig.xml /var/jenkins_home/jobs/JobsCreator/config.xml
RUN chown -R jenkins:jenkins /var/jenkins_home/jobs/JobsCreator
