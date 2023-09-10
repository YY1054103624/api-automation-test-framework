env.MAVEN_TESTS_RESULT_SUMMARY='default'
env.MAVEN_BUILD_RESULT='default'
env.MAVEN_TESTS_RESULT='default';
env.COMMITTED_INFO='default'
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
        stage('Set global variables') {
            steps {
                script {
                    env.MAVEN_TESTS_RESULT_SUMMARY=sh(script: 'grep "Tests run:.*[0-9]$" $HUDSON_HOME/jobs/$JOB_NAME/builds/$BUILD_NUMBER/log | sed -n -e "s/^.*\\(Tests run.*\\)/\\1/p"', returnStdout:true).trim()
                    env.MAVEN_BUILD_RESULT=sh(script: "grep BUILD $HUDSON_HOME/jobs/$JOB_NAME/builds/$BUILD_NUMBER/log | sed -n -e 's/^.*\\(BUILD .*\\)/\\1/p'", returnStdout:true).trim()
                    env.MAVEN_TESTS_RESULT=sh(script: 'grep "Tests run.*Failures" $HUDSON_HOME/jobs/$JOB_NAME/builds/$BUILD_NUMBER/log | sed -n -e "s/^.*\\(Tests run.*\\)/\\1/p"', returnStdout:true).trim()
                    env.COMMITTED_INFO="${params.COMMIT_INFO}";
                }
                println "${params.COMMIT_INFO}"
                println "${currentBuild.getBuildCauses}"
            }
        }
        stage('Send Emails - Build by Upstream') {
            when {
              allOf {
                triggeredBy 'BuildUpstreamCause'
              }
            }
            steps {
                emailext (
                    attachLog: true,
                    attachmentsPattern: 'target/generated-html-report/index.html',
                    body:
'''
Maven Test Status: ${ENV,var="MAVEN_BUILD_RESULT"}
Date of build: ${CURRENT_TIME}
Test summary: ${ENV,var="MAVEN_TESTS_RESULT_SUMMARY"}

Report URL: ${BUILD_URL}My_20Report/
Build URL: ${BUILD_URL}
Project Name: ${PROJECT_NAME}
GIT_REVISION: ${GIT_REVISION}

Test run:
${ENV,var="MAVEN_TESTS_RESULT"}

Commit info:
${ENV,var="COMMITTED_INFO"}
''',
                    subject: '${PROJECT_NAME} - Started by Upstream project - ${ENV,var="MAVEN_BUILD_RESULT"}',
                    to: '18301926330@163.com'
                )
            }
        }
        stage('Send Emails - Build by Push code to api-automation-test repository') {
            when {
              allOf {
                triggeredBy 'SCMTrigger'
              }
            }
            steps {
                println "out: ${MAVEN_TESTS_RESULT_SUMMARY}"
                println "info: ${COMMITTED_INFO}"
                emailext (
                    attachLog: true,
                    attachmentsPattern: 'target/generated-html-report/index.html',
                    body:
'''
Maven Test Status: ${ENV,var="MAVEN_BUILD_RESULT"}
Date of build: ${CURRENT_TIME}
Test summary: ${ENV,var="MAVEN_TESTS_RESULT_SUMMARY"}

Report URL: ${BUILD_URL}My_20Report/
Build URL: ${BUILD_URL}
Project Name: ${PROJECT_NAME}
GIT_REVISION: ${GIT_REVISION}

Test run:
${ENV,var="MAVEN_TESTS_RESULT"}
''',
                    subject: '${PROJECT_NAME} - Started by Upstream project - ${ENV,var="MAVEN_BUILD_RESULT"}',
                    to: '1054103624@qq.com'
                )
            }
        }
    }
    post {
        always {
            script {
                publishHTML (
                    target :
                        [
                            allowMissing: false,
                            alwaysLinkToLastBuild: true,
                            keepAll: true,
                            reportDir: 'target/generated-html-report',
                            reportFiles: 'index.html',
                            reportName: 'Test report',
                            reportTitles: 'report'
                        ]
                )
            }
            sh 'rm -rf $WORKSPACE/Jenkinsfile $WORKSPACE/README.md $WORKSPACE/pom.xml $WORKSPACE/src'
        }
    }
}
