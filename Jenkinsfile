pipeline{

    agent any

    stages{

       stage('stage-1')
       {
          steps
          {
             bat "mvn clean package -DskipTests"
          }
       }

       stage('stage-2')
       {
          steps
          {
            bat "docker build -t=imran24081996/selenium ."
          }
       }

       stage('stage-3')
       {
          steps
          {
            bat "docker push imran24081996/selenium"
          }
       }
    }

}