node('linux')
{
  stage ('Poll') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/ZOSOpenTools/frpport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/ZOSOpenTools/frpport.git'), string(name: 'PORT_DESCRIPTION', value: 'A fast reverse proxy to help you expose a local server behind a NAT or firewall to the internet.' ), string(name: 'BUILD_LINE', value: 'STABLE') ]
  }
}
