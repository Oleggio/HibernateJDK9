package proj3;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class ValidateAllRankingsForSubject {

    RankingService service = new HibernateRankingService();

    @Test
    public void findAllRankingsEmptySet() {
        Assert.assertEquals(service.getRankingFor("Nobody", "Java"), 0);
        Assert.assertEquals(service.getRankingFor("Nobody", "Python"), 0);
        Map<String, Integer> rankings = service.findRankingsFor("Nobody");
        // make sure our dataset size is what we expect: empty
        Assert.assertEquals(rankings.size(), 0);
    }

    @Test
    public void findAllRankings() {
        Assert.assertEquals(service.getRankingFor("Somebody", "Java"),0);
        Assert.assertEquals(service.getRankingFor("Somebody", "Python"),0);
        service.addRanking("Somebody", "Nobody", "Java", 9);
        service.addRanking("Somebody", "Nobody", "Java", 7);
        service.addRanking("Somebody", "Nobody", "Python", 7);
        service.addRanking("Somebody", "Nobody", "Python", 5);
        Map<String, Integer> rankings=service.findRankingsFor("Somebody");
        Assert.assertEquals(rankings.size(), 2);
        Assert.assertNotNull(rankings.get("Java"));
        Assert.assertEquals(rankings.get("Java"), Integer.valueOf(8));
        Assert.assertNotNull(rankings.get("Python"));
        Assert.assertEquals(rankings.get("Python"), Integer.valueOf(6));
    }
}
