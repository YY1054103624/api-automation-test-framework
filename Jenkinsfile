pipeline {
    triggers {
        cron '''TZ=Asia/Shanghai
        H 10 * * 1-5'''
    }
    agent {
        docker {
            image 'maven:3.9.4-eclipse-temurin-17-alpine' 
            // args '-v /root/.m2:/root/.m2' 
        }
    }
    stages {
        stage('Test') { 
            steps {
                sh 'printenv'
                sh 'mvn -Dtest=TestRunner test'
            }
            
        }
        stage('Report'){
            steps {
                publishHTML (target : [allowMissing: false,
                     alwaysLinkToLastBuild: true,
                     keepAll: true,
                     reportDir: 'target/generated-html-report/',
                     reportFiles: 'index.html',
                     reportName: 'My Reports',
                     reportTitles: 'The Report',
                     useWrapperFileDirectly : false])
            }
        }
        stage('Email') {
            steps {
                mail to: '18301926330@163.com',
                    subject: "Job '${JOB_NAME}' (${BUILD_NUMBER}) is waiting for input",
                    body: "Please go to ${BUILD_URL} and verify the build"
            }
        }
    }
}
