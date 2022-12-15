pipeline {
  agent any

  stages {
    stage('SCM Checkout'){
      steps{
        script{
          sh "rm -rf ${WORKSPACE}/*"
          checkout changelog: false, poll: false, scm: [$class: 'GitSCM', branches: [[name: "*/master"]], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'CleanBeforeCheckout']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: "jenkins-private-key", url: "https://github.com/Suhrid-B/sql-jenkins.git"]]]
          sh script: """#!/bin/bash -x
          pwd
          ls -ltr
          chmod +x sql-query.sh
          ./sql-query.sh ${params.db_user} ${params.db_password}
          """

        }
      }
    }
  }
}

  // environment {
  //   // Creates variables ARTIFACTORY=joe:supersecret, ARTIFACTORY_USR=joe, ARTIFACTORY_PSW=supersecret
  //   ARTIFACTORY = credentials('mysql-cred')
  // }
  // stages {
  //   stage('run pipeline'){
  //     steps{
  //       script{
  //         withCredentials([usernamePassword(credentialsId: 'mysql-cred', usernameVariable: 'ARTIFACTORY_USR', passwordVariable: 'ARTIFACTORY_USR')]) {
  //           sh script: """#!/bin/bash -x
  //           chmod +x sql-query.sh
  //           ./sql-query.sh ${params.ARTIFACTORY_USR} ${params.ARTIFACTORY_USR}
  //           """
  //         }
  //       }
  //     }
  //   }
  // }



// node {
//     withCredentials([usernamePassword(credentialsId: 'artifactory', usernameVariable: 'ARTIFACTORY_USR', passwordVariable: 'ARTIFACTORY_PSW')]) {
//         echo 'Hello world'
//     }
// }