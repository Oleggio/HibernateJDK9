package proj3;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class UpdateRankingTest {

    RankingService rankingService = new HibernateRankingService();

    @Test
    public void updateExistingRanking() {
        rankingService.addRanking("Santa", "Lucia", "SQL", 8);
        assertEquals(rankingService.getRankingFor("Santa", "SQL"), 8);
        rankingService.updateRanking("Santa", "Lucia", "SQL", 6);
        assertEquals(rankingService.getRankingFor("Santa", "SQL"), 6);
    }

    @Test
    public void updateNonexistingRanking() {
        assertEquals(rankingService.getRankingFor("Fakundo", "C#"),0);
        rankingService.addRanking("Fakundo", "Ferreira", "C#", 5);
        assertEquals(rankingService.getRankingFor("Fakundo","C#"), 5);
    }
}
