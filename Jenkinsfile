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
        stage('Generate HTML report') {
            cucumber buildStatus: 'UNSTABLE',
                    reportTitle: 'My report',
                    fileIncludePattern: '**/*.json',
                    trendsLimit: 10,
                    classifications: [
                        [
                            'key': 'Browser',
                            'value': 'Firefox'
                        ]
                    ]
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
