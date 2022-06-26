import Repository.PeopleRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class _OnlineHandler_ extends Thread{
    @Override
    public void run() {
        while (true)
        {
            for (var item:OnlineHandler.lastOnline.keySet()) {
                if (Duration.between(OnlineHandler.lastOnline.get(item),LocalDateTime.now()).getSeconds()>=15)
                {
                    OnlineHandler.lastOnline.remove(item);
                    PeopleRepository.getInstance().people.get(item).setOnline(false);
                }
            }
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
