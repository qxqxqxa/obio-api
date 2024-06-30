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
                kill -9 `cat pid.txt`
                rm pid.txt
                nohup java -jar ./renren-admin/target/obio-admin.jar > process.log 2>&1 &
                echo $! > pid.txt
                ''' 
            }
        }
    }
}