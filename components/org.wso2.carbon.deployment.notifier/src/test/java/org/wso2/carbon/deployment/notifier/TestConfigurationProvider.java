package org.wso2.carbon.deployment.notifier;

import org.wso2.carbon.kernel.configprovider.CarbonConfigurationException;
import org.wso2.carbon.kernel.configprovider.ConfigProvider;

import java.util.Map;

public class TestConfigurationProvider implements ConfigProvider {
    @Override
    public <T> T getConfigurationObject(Class<T> configClass) throws CarbonConfigurationException {
        try {
            return configClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new CarbonConfigurationException("error while creating configuration object");
        }
    }

    @Override
    public Map getConfigurationMap(String namespace) throws CarbonConfigurationException {
        return null;
    }
}
