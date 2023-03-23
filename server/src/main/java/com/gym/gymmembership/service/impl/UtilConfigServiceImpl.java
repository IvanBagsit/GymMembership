package com.gym.gymmembership.service.impl;

import com.gym.gymmembership.domain.UtilityConfiguration;
import com.gym.gymmembership.repository.UtilityConfigurationRepository;
import com.gym.gymmembership.service.UtilConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UtilConfigServiceImpl implements UtilConfigService {

    private final UtilityConfigurationRepository utilityConfigurationRepository;

    @Override
    public String retrieveConfig(String name) {
        Optional<UtilityConfiguration> utilConfig = utilityConfigurationRepository.findFirstByName(name);
        if(Optional.ofNullable(utilConfig).isPresent()) {
            log.info("Returning config: {}", utilConfig.get().getConfig());
            return utilConfig.get().getConfig();
        }
        else {
            log.info("Can't find config for : {}", name);
            return "";
        }
    }
}
