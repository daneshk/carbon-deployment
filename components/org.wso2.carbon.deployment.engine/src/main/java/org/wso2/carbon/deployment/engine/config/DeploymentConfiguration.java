/*
 *  Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.wso2.carbon.deployment.engine.config;

import org.wso2.carbon.kernel.annotations.Configuration;
import org.wso2.carbon.kernel.annotations.Element;
import org.wso2.carbon.kernel.utils.Utils;

/**
 * DeploymentConfiguration class holds static configuration parameters specified in the deployment.yml file.
 *
 * @since 5.1.0
 */
@Configuration(namespace = "wso2.deployment", description = "Deployment Engine related configurations")
public class DeploymentConfiguration {

    public DeploymentConfiguration() {
        repositoryLocation = "${carbon.home}/deployment/";
        if (Utils.getSystemVariableValue("carbon.home", null) != null) {
            repositoryLocation = Utils.substituteVariables(repositoryLocation);
        }
    }

    @Element(description = "Currently there can be two modes\n" +
            "1. Scheduled Mode - (enable below property to \"scheduled\") - where the task runs periodically " +
            "and trigger deployment\n" +
            "2. Triggered Mode - (enable below property to \"triggered\") - the deployment has to be triggered " +
            "externally,\n" +
            "eg : in a worker node we don't need the task to run, but rather when we receive a\n" +
            "cluster msg,  the deployment has to be triggered manually\n")
    private DeploymentModeEnum mode = DeploymentModeEnum.scheduled;

    @Element(description = "Location of the artifact repository\n")
    private String repositoryLocation;

    @Element(description = "Deployment update interval in seconds. This is the interval between repository listener\n" +
            "executions.\n")
    private int updateInterval = 15;

    @Element(description = "Notify deployment status via JMS to a topic.\n" +
            "In a cluster, all nodes should publish the status to the same topic which may be\n" +
            "listened on to take deployment decisions such as reverting artifacts.\n" +
            "This is disabled by default.\n" +
            "Specify any static message content to be published as key-value pairs\n" +
            "under staticMessageContent.\n")
    private DeploymentNotifierConfig deploymentNotifier = new DeploymentNotifierConfig();

    public DeploymentModeEnum getMode() {
        return mode;
    }

    public String getRepositoryLocation() {
        return repositoryLocation;
    }

    public int getUpdateInterval() {
        return updateInterval;
    }

    public DeploymentNotifierConfig getDeploymentNotifier() {
        return deploymentNotifier;
    }

}
