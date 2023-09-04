pipeline {
    agent {
        docker {
            image 'maven:3.9.4-eclipse-temurin-17-alpine' 
            args '-v /root/.m2:/root/.m2' 
        }
    }
    stages {
        stage('Test') { 
            steps {
                sh 'printenv'
                sh 'mvn -Dtest=TestRunner test'
            }
        }
        stage('Email') {
            steps {
                emailext 
                body: '${JOB_URL}', 
                    recipientProviders: [buildUser()], 
                    subject: 'Hello - this is an email sent by Jenkins', 
                    to: '18301926330@163.com'
            }
        }
    }
}
