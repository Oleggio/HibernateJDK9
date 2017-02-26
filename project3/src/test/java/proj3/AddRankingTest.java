package proj3;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AddRankingTest {
    RankingService service=new HibernateRankingService();
    @Test
    public void addRanking() {
        service.addRanking("J. C. Smell", "Drew Lombardo", "Mule", 8);
        Assert.assertEquals(service.getRankingFor("J. C. Smell", "Mule"), 8);
    }
}
