pipeline {
    agent any
    stages {
        stage('Build') { 
            steps {
                sh '''
                cp /var/lib/jenkins/workspace/application-dev.yml ./renren-admin/src/main/resources/
                mvn install
                mvn clean package
                java -jar ./renren-admin/target/obio-admin.jar
                ''' 
            }
        }
    }
}