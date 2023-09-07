LOG_DIR="$HUDSON_HOME/jobs/$JOB_NAME/builds/$BUILD_NUMBER/log"
pipeline {
    agent any
    /*triggers {
        cron '''TZ=Asia/Shanghai
        H 10 * * 1-5'''
    }*/
    cleanWs()
    options {
         timeout(time: 2, unit: 'HOURS')
         timestamps()
    }
    stages {
        stage('Test') {
            agent {
                docker {
                    image 'maven:3.9.4-eclipse-temurin-17-alpine'
                    args '-v /root/.m2:/root/.m2'
                    reuseNode true
                }
            }
            steps {
                sh 'mvn -Dtest=TestRunner test'

            }
        }
        stage('Jenkins') {
            steps {
                sh "whoami"
                sh "hostname"
                println "${LOG_DIR}"
                println "${HUDSON_HOME}"
            }
        }
        stage('When') {
            environment {
                // MY_ENV= sh(script: 'cat /var/jenkins_home/workspace/api-automation-test/target/generated-json-report/cucumber.json', returnStdout:true).trim()
                MY_ENV= sh(script: 'cat /var/jenkins_home/jobs/api-automation-test/builds/147/log', returnStdout:true).trim()
            }
            steps {

                println "MY_ENV=${MY_ENV}"
            }

        }
    }
    /*post {
        success {
        // Send emails to all related devlopers when building process is succeed.
          emailext attachmentsPattern: 'target/generated-html-report/index.html', body: '''BUILD SUCCESS

Build URL: ${BUILD_URL}
Project Name: ${PROJECT_NAME}
Date of build: ${CURRENT_TIME}
''', recipientProviders: [contributor()], subject: '${PROJECT_NAME} - Build # ${BUILD_NUMBER} - Succcessful', to: 'cc:1054103624@qq.com'}
        failure {
        // Send the email to the builder when building process is failed.
            emailext attachLog: true, body: '''BUILD SUCCESS

Build URL: ${BUILD_URL}
Project Name: ${PROJECT_NAME}
Date of build: ${CURRENT_TIME}''', recipientProviders: [buildUser()], subject: '${PROJECT_NAME} - Build # ${BUILD_NUMBER} - Unsuccessful'
        }
    }*/
}
