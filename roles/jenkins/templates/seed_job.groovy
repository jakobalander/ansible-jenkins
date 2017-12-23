import jenkins.model.*

def jobName = "{{ jenkins_seed_job.name }}"

def configXml = """\
  <?xml version='1.0' encoding='UTF-8'?>
  <project>
    <actions/>
    <description></description>
    <keepDependencies>false</keepDependencies>
    <properties>
    </properties>
    <scm class="hudson.plugins.git.GitSCM" plugin="git@{{ plugin_git_version }}">
        <configVersion>2</configVersion>
        <userRemoteConfigs>
            <hudson.plugins.git.UserRemoteConfig>
                <url>{{ this_repository }}</url>
            </hudson.plugins.git.UserRemoteConfig>
        </userRemoteConfigs>
        <branches>
            <hudson.plugins.git.BranchSpec>
                <name>*/master</name>
            </hudson.plugins.git.BranchSpec>
        </branches>
        <doGenerateSubmoduleConfigurations>false</doGenerateSubmoduleConfigurations>
        <submoduleCfg class="list"/>
        <extensions/>
    </scm>
    <canRoam>true</canRoam>
    <disabled>false</disabled>
    <blockBuildWhenDownstreamBuilding>false</blockBuildWhenDownstreamBuilding>
    <blockBuildWhenUpstreamBuilding>false</blockBuildWhenUpstreamBuilding>
    <triggers/>
    <concurrentBuild>false</concurrentBuild>
    <builders>
      <javaposse.jobdsl.plugin.ExecuteDslScripts plugin="job-dsl@{{ plugin_job_dsl_version }}">
        <targets>{{ jenkins_seed_job.dsl_script_path }}</targets>
        <usingScriptText>false</usingScriptText>
        <ignoreExisting>false</ignoreExisting>
        <removedJobAction>IGNORE</removedJobAction>
        <removedViewAction>IGNORE</removedViewAction>
        <lookupStrategy>JENKINS_ROOT</lookupStrategy>
        <additionalClasspath></additionalClasspath>
      </javaposse.jobdsl.plugin.ExecuteDslScripts>
    </builders>
    <publishers/>
    <buildWrappers/>
  </project>
""".stripIndent()


if (!Jenkins.instance.getItem(jobName)) {
    def xmlStream = new ByteArrayInputStream( configXml.getBytes() )
    try {
        def seedJob = Jenkins.instance.createProjectFromXML(jobName, xmlStream)
        seedJob.scheduleBuild(0, null)
    } catch (ex) {
        println "ERROR: ${ex}"
        println configXml.stripIndent()
    }
}
