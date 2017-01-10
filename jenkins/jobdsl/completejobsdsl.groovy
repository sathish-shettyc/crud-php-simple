job('CodeCoverage') {
  description('CodeCoverage')
  logRotator {
        daysToKeep(60)
        numToKeep(20)
        artifactDaysToKeep(1)
    }
    steps{
    	shell ('echo "This is code Coverege"')
    }
}



job('CodeQuality') {
  description('CodeQuality')
  logRotator {
        daysToKeep(60)
        numToKeep(20)
        artifactDaysToKeep(1)
    }
    steps{
    	shell ('echo "This is code Quality"')
    }
}



job('CodeStability') {
  description('CodeStability')
  logRotator {
        daysToKeep(60)
        numToKeep(20)
        artifactDaysToKeep(1)
    }
    steps{
    	shell ('echo "This is Code Stability"')
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
        url("https://github.com/saurabhvaj/crud-php-simple.git")
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
	    dockerfileDirectory('application')
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
            url("https://github.com/saurabhvaj/crud-php-simple.git")
          }
        }
        scriptPath('jenkins/pipelinesteps/CodeDeploymentPipelineSteps.groovy')
      }
    }
  }
}
