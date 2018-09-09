pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps {
        git(url: 'https://github.com/skipidar/youtube4kidz.gcpappengine.git', branch: 'master')
      }
    }
    stage('Build') {
      steps {
        sh './gradlew build'
      }
    }
    stage('Deploy') {
      steps {
        sh 'echo "Deploy"'
      }
    }
  }
}