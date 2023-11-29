#!/usr/bin/env groovy
pipeline {
 
    agent any
 
     tools {
         jdk 'jdk17'
         maven 'M3'
     }
 
     triggers { pollSCM('*/5 * * * *') }
 
     options {
         buildDiscarder(logRotator(numToKeepStr: '5'))
     }
 
     stages {
 
     	stage('Build Stage') {
  			
             steps {
 	      		sh "mvn clean install"
 	    	}
 	 	}
 
 	 	stage('SonarQube analysis') {
	 	
 	    	steps {
 		    	withSonarQubeEnv('AWS SONAR') {
                      sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.1.2184:sonar'
                         }
 		    }
   	   }
 
 
   	   stage('Aws authentication'){
        	   		steps {
        	        	sh '$(aws ecr get-login --no-include-email --region eu-west-3)'
        	        }
        	   }
 
        stage('Docker Stage for latest version ') {
                 when { branch "develop" }
                 steps {
                     sh "mvn dockerfile:build"
                     sh "mvn dockerfile:build -P common"
 
                 }
            }
 
        	   stage('Push to ECR latest version ') {
        	   		when { branch "develop" }
        	   	
        	   		steps {
        	      		sh "mvn dockerfile:push"
        	      		sh "mvn dockerfile:push -P common"
        	      	}
        	   }
         /*stage('Update ECS Fargate Service CI') {
        	    when { branch "develop" }
            	steps {
           	     sh 'aws ecs update-service --service codeonce-ioevent-studio-service --cluster grizzly-wave-ci   --region eu-central-1 --force-new-deployment'
            	}
        }*/
 
     }
     post {
         always {
             junit '**/surefire-reports/*.xml'
         }
 
         failure {
              emailext (
 		      subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
 		      body: """<p>FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
 		        <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>""",
 		      recipientProviders: [[$class: 'DevelopersRecipientProvider'],[$class: 'CulpritsRecipientProvider']]
 		    )
         }
     }
 }