package proj3;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidateAverageRankingTest {

    @Test
    public void validateRankingAverage() {
        RankingService service = new HibernateRankingService();
        service.addRanking("Alloha", "Commacho", "Skill", 4);
        service.addRanking("Alloha", "Commacho", "Skill", 3);
        service.addRanking("Alloha", "Commacho", "Skill", 5);
        Assert.assertEquals(4, service.getRankingFor("Alloha", "Skill"));
        service.addRanking("Alloha", "Commacho", "Skill", 6);
        service.addRanking("Alloha", "Commacho", "Skill", 8);
        service.addRanking("Alloha", "Commacho", "Skill", 4);
        service.addRanking("Alloha", "Commacho", "Skill", 6);
        Assert.assertEquals(5, service.getRankingFor("Alloha", "Skill"));


    }
}
