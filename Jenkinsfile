import groovy.json.JsonSlurper

env.EMAIL_COMMIT_INFO='default'
env.EMAIL_ADDRESSES='default'

env.MAVEN_TESTS_RESULT_SUMMARY='default'
env.MAVEN_BUILD_RESULT='default'
env.MAVEN_TESTS_RESULT='default';
env.COMMITTED_INFO='default'
pipeline {
    agent any

    triggers {
        GenericTrigger (
            causeString: 'Generic Cause', 
            genericVariables: [
                [
                    defaultValue: '', 
                    key: 'COMMIT_INFO', 
                    regexpFilter: '', 
                    value: '$.commits[0:].[\'message\', \'committer\', \'added\', \'removed\', \'modified\']'
                ],
                [
                    defaultValue: '',
                    key: 'committer_name',
                    regexpFilter: '',
                    value: '$.commits[0].committer.name'
                ]
            ],
            printPostContent: true,
            printContributedVariables: true,
            regexpFilterExpression: '^((?!GitHub))',
            regexpFilterText: '$committer_name',
            token: 'my_trigger_token', 
            tokenCredentialId: ''
        )
    }
    parameters {
        string defaultValue: 'default', name: 'COMMIT_INFO'
    }
    options {
         timeout(time: 2, unit: 'HOURS')
         timestamps()
    }
    stages {
        stage('Maven Test') {
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
        stage('Set global variables from build log') {
            steps {
                script {
                    env.MAVEN_TESTS_RESULT_SUMMARY=sh(script: 'grep "Tests run:.*[0-9]$" $HUDSON_HOME/jobs/$JOB_NAME/builds/$BUILD_NUMBER/log | sed -n -e "s/^.*\\(Tests run.*\\)/\\1/p"', returnStdout:true).trim()
                    env.MAVEN_BUILD_RESULT=sh(script: "grep BUILD $HUDSON_HOME/jobs/$JOB_NAME/builds/$BUILD_NUMBER/log | sed -n -e 's/^.*\\(BUILD .*\\)/\\1/p'", returnStdout:true).trim()
                    env.MAVEN_TESTS_RESULT=sh(script: 'grep "Tests run.*Failures" $HUDSON_HOME/jobs/$JOB_NAME/builds/$BUILD_NUMBER/log | sed -n -e "s/^.*\\(Tests run.*\\)/\\1/p"', returnStdout:true).trim()


                }
            }
        }
        stage('Set global variables from upstream build') {
            steps {
                script {
                    if (params.COMMIT_INFO != 'default') {
                        def jsonSlurper = new JsonSlurper()
                        def commits = jsonSlurper.parseText(params.COMMIT_INFO)
                        def formattedCommitsInfo = new StringBuilder();
                        def emailAddresses = new StringBuilder();
                        for (i=0; i<commits.size(); i++) {
                            commitsInfoFormatter(commits, formattedCommitsInfo)
                            emailAdressesFormatter(commits, emailAddresses, i)
                        }
                        env.EMAIL_COMMIT_INFO=formattedCommitsInfo
                        env.EMAIL_ADDRESSES=emailAddresses
                    }
                    
                }
            }
        }
    }
    post {
        success {
            script {
                if (isEmailNeeded(currentBuild.getBuildCauses())) {
                    emailext (
                    body:
'''
Maven Test Status: ${ENV,var="MAVEN_BUILD_RESULT"}
Test summary: ${ENV,var="MAVEN_TESTS_RESULT_SUMMARY"}

Report URL: ${BUILD_URL}Test_20Report/
Build URL: ${BUILD_URL}
Project Name: ${PROJECT_NAME}
GIT_REVISION: ${GIT_REVISION}

Test run:
${ENV,var="MAVEN_TESTS_RESULT"}
''',
                    subject: '${PROJECT_NAME} - Started by BuildUpstreamCause - ${ENV,var="MAVEN_TESTS_RESULT_SUMMARY"}',
                    to: '${ENV, var="EMAIL_ADDRESSES"}'
                    )
                }
                
            }
        }
        failure {
            script {
                if (isEmailNeeded(currentBuild.getBuildCauses())) {
                    emailext (
                    body:
'''
Maven Test Status: ${ENV,var="MAVEN_BUILD_RESULT"}
Test summary: ${ENV,var="MAVEN_TESTS_RESULT_SUMMARY"}

Report URL: ${BUILD_URL}Test_20Report/
Build URL: ${BUILD_URL}
Project Name: ${PROJECT_NAME}
GIT_REVISION: ${GIT_REVISION}

Test run:
${ENV,var="MAVEN_TESTS_RESULT"}

Commit info:
${ENV, var="EMAIL_COMMIT_INFO"}
''',
                    subject: '${PROJECT_NAME} - Started by BuildUpstreamCause - ${ENV,var="MAVEN_BUILD_RESULT"}',
                    to: '${ENV, var="EMAIL_ADDRESSES"}'
                    )
                }
            }
        }
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

def getTriggerCause(cause) {
    String longTriggerReason = cause[0]['_class']
    return longTriggerReason.tokenize(".")[-1]
}

def isEmailNeeded(cause) {
    def pureCause = getTriggerCause(cause);
    switch (pureCause) {
        case "BuildUpstreamCause":
            println "Build by anothor job";
            return true;
        case "Cause\$UserIdCause":
            println "Build by a Jenkins user";
            return false;
        case "GenericCause":
            println "Build by Generic Webhook Trigger";
            return true;
        case "TimerTrigger":
            println "Build by Build periodically";
            return false;
        default:
            println "${pureCause} isn't supported.";
            return true;
    }
}

def commitsInfoFormatter(commits, formattedCommitsInfo) {
    formattedCommitsInfo.append("Commiter: " + commits[i].committer.name)
    formattedCommitsInfo.append("\n")
    formattedCommitsInfo.append("Email: " + commits[i].committer.email)
    formattedCommitsInfo.append("\n")
    formattedCommitsInfo.append("Message: " + commits[i].message)
    
    
    if (!commits[i].added.isEmpty()) {
        formattedCommitsInfo.append("\n")
        formattedCommitsInfo.append("Added files: " + commits[i].added)
    }
    
    if (!commits[i].removed.isEmpty()) {
        formattedCommitsInfo.append("\n")
        formattedCommitsInfo.append("Removed files: " + commits[i].removed)
    }
    
    if (!commits[i].modified.isEmpty()) {
        formattedCommitsInfo.append("\n")
        formattedCommitsInfo.append("Removed files: " + commits[i].modified)
    }
    formattedCommitsInfo.append("\n\n")
}

def emailAdressesFormatter(commits, emailAddresses, index) {
    emailAddresses.append(commits[i].committer.email)
    if (index != commits.size()-1)
        emailAddresses.append(",")
}
