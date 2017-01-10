git clone https://github.com/saurabhvaj/crud-php-simple.git

go to jenkins directory and run 

$ make build
$ make run2

Go to jenkkins UI by typing 

localhost:<jenkins port for 8080>

To know jenkins port use 

$ docker ps

And find port corresponding to 8080.

Configure your jenkins user. 

To know your temp password run foolowing on your terminal.

$ docker exec jenkins2  cat /var/jenkins_home/secrets/initialAdminPassword

Create a jenkins job to initialize other jobs. Using job dsl script present at jenkins/jobdsl/completejobsdsl.groovy

Run  your first job

