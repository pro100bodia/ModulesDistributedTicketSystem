node {
   def mvnHome
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
}