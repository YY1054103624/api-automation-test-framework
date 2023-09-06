pipeline {
    triggers {
        cron '''TZ=Asia/Shanghai
        H 10 * * 1-5'''
    }
    options {
        timeout(time: 5, unit: 'MINUTES')
        timestamps()
    }
    agent {
        docker {
            image 'maven:3.9.4-eclipse-temurin-17-alpine' 
            args '-v /root/.m2:/root/.m2' 
        }
    }
    stages {
        stage('Test') {
            steps {
                echo currentBuild.upstreamBuilds
                // sh 'mvn -Dtest=TestRunner test'
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
