package com.gym.gymmembership.service;

import com.gym.gymmembership.domain.UtilityConfiguration;
import com.gym.gymmembership.repository.UtilityConfigurationRepository;
import com.gym.gymmembership.service.impl.UtilConfigServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UtilConfitServiceTest {
    @Mock
    private UtilityConfigurationRepository utilityConfigurationRepository;

    @InjectMocks
    private UtilConfigServiceImpl utilConfigService;

    @Test
    void testRetrieveConfig_Found() {
        // when
        String configName = "timeout";
        String expectedConfig = "30s";
        UtilityConfiguration mockConfig = new UtilityConfiguration();
        mockConfig.setName(configName);
        mockConfig.setConfig(expectedConfig);

        when(utilityConfigurationRepository.findFirstByName(configName))
                .thenReturn(Optional.of(mockConfig));

        // then
        String result = utilConfigService.retrieveConfig(configName);

        // verify
        assertEquals(expectedConfig, result);
        verify(utilityConfigurationRepository, times(1)).findFirstByName(configName);
    }

    @Test
    void testRetrieveConfig_NotFound() {
        // when
        String configName = "invalidConfig";

        when(utilityConfigurationRepository.findFirstByName(configName))
                .thenReturn(Optional.empty());

        // then
        String result = utilConfigService.retrieveConfig(configName);

        // verify
        assertEquals("", result);
        verify(utilityConfigurationRepository, times(1)).findFirstByName(configName);
    }
}
