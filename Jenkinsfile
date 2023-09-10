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
        stage('Send Email') {
            environment {
                MAVEN_BUILD_RESULT=sh(script: "grep BUILD $HUDSON_HOME/jobs/$JOB_NAME/builds/$BUILD_NUMBER/log | sed -n -e 's/^.*\\(BUILD .*\\)/\\1/p'", returnStdout:true).trim()
                MAVEN_TESTS_RESULT=sh(script: 'grep "Tests run" $HUDSON_HOME/jobs/$JOB_NAME/builds/$BUILD_NUMBER/log | sed -n -e "s/^.*\\(Tests run.*\\)/\\1/p"', returnStdout:true).trim()
                MAVEN_TESTS_RESULT_SUMMARY=sh(script: 'grep "Tests run:.*[0-9]$" $HUDSON_HOME/jobs/$JOB_NAME/builds/$BUILD_NUMBER/log | sed -n -e "s/^.*\\(Tests run.*\\)/\\1/p"', returnStdout:true).trim()
                MAVEN_TESTS_TOTAL_COUNT=sh(script: 'grep "Tests run:.*[0-9]$" $HUDSON_HOME/jobs/$JOB_NAME/builds/$BUILD_NUMBER/log | sed -n -e "s/^.*Tests run: \\([0-9]\\),.*/\\1/p"', returnStdout:true).trim()
                MAVEN_TESTS_FAILURE_COUNT=sh(script: 'grep "Tests run:.*[0-9]$" $HUDSON_HOME/jobs/$JOB_NAME/builds/$BUILD_NUMBER/log | sed -n -e "s/^.*Failures: \\([0-9]\\),.*/\\1/p"', returnStdout:true).trim()
            }
            when {
              allOf {
                triggeredBy 'BuildUpstreamCause'
              }
            }
            steps {
                println "MAVEN_TESTS_RESULT=${MAVEN_TESTS_RESULT}"
                emailext (
                    attachLog: true,
                    attachmentsPattern: 'target/generated-html-report/index.html',
                    body:
'''
Maven Test Status: ${ENV,var="MAVEN_BUILD_RESULT"}
Date of build: ${CURRENT_TIME}
Test summary: ${ENV,var="MAVEN_TESTS_RESULT_SUMMARY"}

Build URL: ${BUILD_URL}
Project Name: ${PROJECT_NAME}
GIT_REVISION: ${GIT_REVISION}

Test run:
${ENV,var="MAVEN_TESTS_RESULT"}

Committer:
${params.COMMIT_INFO}
''',
                    subject: '${PROJECT_NAME} - Started by Upstream project - ${MAVEN_BUILD_RESULT}',
                    to: '18301926330@163.com'
                )
            }
        }
    }
    post {
        always {
            publishHTML(
                [
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: false,
                    reportDir: 'target/generated-html-report/',
                    reportFiles: 'index.html',
                    reportName: 'HTML Report',
                    reportTitles: '',
                    useWrapperFileDirectly: true
                ]
            )
            sh 'rm -rf $WORKSPACE/Jenkinsfile $WORKSPACE/README.md $WORKSPACE/pom.xml $WORKSPACE/src'
        }
    }
}
