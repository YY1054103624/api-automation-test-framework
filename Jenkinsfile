LOG_DIR="$HUDSON_HOME/jobs/$JOB_NAME/builds/$BUILD_NUMBER/log"
pipeline {
    agent any
    /*triggers {
        cron '''TZ=Asia/Shanghai
        H 10 * * 1-5'''
    }*/
    parameters {
        string defaultValue: 'no commit info', name: 'COMMIT_INFO'
    }
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
                script {
                    catchError(message: 'Maven build failure', stageResult: 'FAILURE') {
                        sh 'mvn -Dtest=TestRunner test'
                    }
                }

            }
        }
        stage('Jenkins') {
            steps {
                println "${params.COMMIT_INFO}"
            }
        }
        stage('When') {
            environment {
                MAVEN_BUILD_RESULT=sh(script: "grep BUILD $HUDSON_HOME/jobs/$JOB_NAME/builds/$BUILD_NUMBER/log | sed -n -e 's/^.*\\(BUILD .*\\)/\\1/p'", returnStdout:true).trim()
                MAVEN_TESTS_RESULT=sh(script: 'grep "Tests run" $HUDSON_HOME/jobs/$JOB_NAME/builds/$BUILD_NUMBER/log | sed -n -e "s/^.*\\(Tests run.*\\)/\\1/p"', returnStdout:true).trim()
                MAVEN_TESTS_TOTAL_COUNT=sh(script: 'grep "Tests run:.*[0-9]$" $HUDSON_HOME/jobs/$JOB_NAME/builds/$BUILD_NUMBER/log | sed -n -e "s/^.*Tests run: \\([0-9]\\),.*/\\1/p"', returnStdout:true).trim()
                MAVEN_TESTS_FAILURE_COUNT=sh(script: 'grep "Tests run:.*[0-9]$" $HUDSON_HOME/jobs/$JOB_NAME/builds/$BUILD_NUMBER/log | sed -n -e "s/^.*Failures: \\([0-9]\\),.*/\\1/p"', returnStdout:true).trim()
            }
            steps {
                println "MAVEN_BUILD_RESULT=${MAVEN_BUILD_RESULT}"
                println "MAVEN_TESTS_RESULT=${MAVEN_TESTS_RESULT}"
                println "MAVEN_TESTS_TOTAL_COUNT=${MAVEN_TESTS_TOTAL_COUNT}"
                println "MAVEN_TESTS_FAILURE_COUNT=${MAVEN_TESTS_FAILURE_COUNT}"
            }

        }
        stage('Send Email') {
            steps {
                emailext (
                    attachLog: true,
                    attachmentsPattern: 'target/generated-html-report/index.html',
                    body:
'''BUILD SUCCESS

Build URL: ${BUILD_URL}
Project Name: ${PROJECT_NAME}
Date of build: ${CURRENT_TIME}
''',
                    subject: '${PROJECT_NAME} - Build # ${BUILD_NUMBER} - Succcessful',
                    to: '18301926330@163.com'
                )
            }
        }
    }
    post {
        always {
            sh 'rm -rf $WORKSPACE/Jenkinsfile $WORKSPACE/README.md $WORKSPACE/pom.xml $WORKSPACE/src $WORKSPACE/target'
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
