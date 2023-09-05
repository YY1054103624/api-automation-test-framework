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
                sh 'mvn -Dtest=TestRunner test'
            }          
        }
    }
    post { 
        aways { 
            echo 'I will always say Hello again!'
        }
        success {
            emailext attachLog: true, body: '''Build Success
            
            Project URL: ${PROJECT_URL}''', recipientProviders: [buildUser()], subject: '${PROJECT_NAME}', to: '18301926330@163.com 1054103624@qq.com'
        }
    }
}
