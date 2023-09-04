pipeline {
    agent any
    stages {
        stage('begin') {
            steps {
                echo 'Hello pipeline'
                }
        }
    }
    post {
        always {
            echo 'say goodbay'
        }
    }
}
