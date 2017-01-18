job('CodeStability') {
    logRotator(-1, 10)
    scm {
        git('https://github.com/OpsTree/ContinuousIntegration.git')
    }
    triggers {
        scm('H/15 * * * *')
    }
    steps {
        maven('clean compile','Spring3HibernateApp/pom.xml')
       
        
    }
    
}

job('CodeQuality') {
    logRotator(-1, 10)
    
    scm {
        git('https://github.com/OpsTree/ContinuousIntegration.git')
    }
    triggers {
        scm('H/15 * * * *')
    }
    steps {
        maven('pmd:pmd checkstyle:checkstyle','Spring3HibernateApp/pom.xml')
        
      publishers {
        checkstyle('**/checkstyle-result.xml')
    }
    }
    
}

job('CodeCoverage') {
    logRotator(-1, 10)
    
    scm {
        git('https://github.com/OpsTree/ContinuousIntegration.git')
    }
    triggers {
        scm('H/15 * * * *')
    }
    steps {
        maven('cobertura:cobertura ','Spring3HibernateApp/pom.xml')
        
      publishers {
        
         cobertura('**/target/site/cobertura/coverage.xml') {
            failNoReports(true)
            sourceEncoding('ASCII')
         }
        
        
     }
    }
    
}


job('dockerRegistry') {
  description('Docker Registry')
  logRotator {
        daysToKeep(60)
        numToKeep(20)
        artifactDaysToKeep(1)
    }
  scm {
        git {
      remote {
        url("https://github.com/sathish-shettyc/crud-php-simple.git")
      }
      branch("*/master")
     }
    }

    steps{
        shell ("cd docker-registry \n"  +
"make run")
    }
}

job('ImageGenerator') {
  description('ImageGenerator')
  logRotator {
        daysToKeep(60)
        numToKeep(20)
        artifactDaysToKeep(1)
    }

     scm {
        git {
      remote {
        url("https://github.com/sathish-shettyc/crud-php-simple.git")
      }
      branch("*/master")
     }
    }

    steps {
        dockerBuildAndPublish {
            repositoryName('localhost:5000/php-application')
            forcePull(false)
            createFingerprints(true)
            skipDecorate()
	    dockerfileDirectory('application/Dockerfile')
        }
    }
}


job('CodeDeployer') {
  description('CodeDeployer')
  logRotator {
        daysToKeep(60)
        numToKeep(20)
        artifactDaysToKeep(1)
    }
    steps{
    	shell ("docker rm -vf php-app || true \n"  +
"docker run -d -p 8080:80  --link some-mysql:mysql --name php-app  localhost:5000/php-application")
    }
}



pipelineJob("CodeDeploymentPipeline") {
  logRotator {
    daysToKeep(60)
    numToKeep(20)
    artifactDaysToKeep(1)
  }
  description('CI Pipe Line')
  definition {
    cpsScm {
      scm {
        git {
          branch("*/master")
          remote {
            name('')
            refspec('')
            url("https://github.com/sathish-shettyc/crud-php-simple.git")
          }
        }
        scriptPath('jenkins/pipelinesteps/CodeDeploymentPipelineSteps.groovy')
      }
    }
  }
}
