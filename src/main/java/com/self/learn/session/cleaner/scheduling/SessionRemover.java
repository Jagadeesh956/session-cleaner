package com.self.learn.session.cleaner.scheduling;

import com.spring.learn.sessiondao.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SessionRemover {

    SessionRepository sessionRepository;
    @Autowired
    public SessionRemover(SessionRepository sessionRepository){
        this.sessionRepository = sessionRepository;
    }
    @Scheduled(cron = "0 */1 * * * ?")
    public int remove(){
        long oneHourAgoInMillis = System.currentTimeMillis() ; // 60L ensures long calculation
         int removedCount = sessionRepository.removeSessions(oneHourAgoInMillis);
         System.out.println("Removed "+ removedCount +" expired sessions");
         return removedCount;
    }

}
