pipeline {
    agent any
    stages {
        stage('Build') { 
            steps {
                sh '''
                cp /var/lib/jenkins/workspace/application-dev.yml ./renren-admin/src/main/resources/
                mvn install
                mvn clean package
                kill -9 `ps -ef | grep "java -jar" | head -n 1 | awk '{print $2}'`
                nohup java -jar ./renren-admin/target/obio-admin.jar > process.log 2>&1 &
                ''' 
            }
        }
    }
}