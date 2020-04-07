package india.lingayat.we.scheduler;


import india.lingayat.we.repositories.UserRepository;
import india.lingayat.we.services.CacheEvictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class ScheduledEviction {

    @Autowired
    CacheEvictionService cacheEvictionService;

    @Scheduled(cron = "0 0 * * *")
    public void evictCache(){
        cacheEvictionService.evictCache();
    }
}
