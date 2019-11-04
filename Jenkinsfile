node {
    registry = 'bodiagoogle/tm'
    registryCredential = 'docker'
    dockerImage = ''
    def mvnHome
    def remote = [:]
    remote.name = 'ec2-user'
    remote.host = '3.86.206.165'
    remote.allowAnyHosts = true

    stage('Preparation') {
          git 'https://github.com/pro100bodia/ModulesDistributedTicketLib.git'
          mvnHome = tool '3.6.1'
    }
    stage('Build') {
       withEnv(["MVN_HOME=$mvnHome"]) {
         bat(/"%MVN_HOME%\bin\mvn" -Dmaven.test.failure.ignore clean install/)
       }
    }
    stage('SonarQube analysis') {
       withEnv(["MVN_HOME=$mvnHome"]) {
         bat(/"%MVN_HOME%\bin\mvn" sonar:sonar/)
       }
    }
    stage('Results') {
       junit '**/target/surefire-reports/TEST-*.xml'
       archiveArtifacts '*/target/*.jar'
    }
    stage('Building image') {
        script {
            dockerImage = docker.build registry + ":$BUILD_NUMBER"
        }
    }
    stage('Deploy Image to DockerHub') {
        script {
            docker.withRegistry( '', registryCredential ) {
                dockerImage.push()
            }
        }
    }
}