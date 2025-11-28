package com.project.taskManager.cache;

import com.project.taskManager.entity.ConfigTasksAppEntity;
import com.project.taskManager.repository.ConfigTasksAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    public enum keys{
        WEATHER_API;
    }

    @Autowired
    private ConfigTasksAppRepository configTasksAppRepository;
    public Map<String, String> appCache;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        List<ConfigTasksAppEntity> all  = configTasksAppRepository.findAll();
        for(ConfigTasksAppEntity configTasksAppEntity : all){
            appCache.put(configTasksAppEntity.getKey(), configTasksAppEntity.getValue());
        }
    }
}
