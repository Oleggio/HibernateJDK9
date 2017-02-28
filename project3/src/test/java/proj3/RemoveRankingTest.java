package proj3;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class RemoveRankingTest {

    RankingService service = new HibernateRankingService();

    @Test
    public void removeRanking() {
        service.addRanking("Koko", "Channel", "Parfums", 10);
        assertEquals(10, service.getRankingFor("Koko", "Parfums"));
        service.removeRanking("Koko", "Channel", "Parfums");
        assertEquals(0, service.getRankingFor("Koko", "Parfums"));
    }

//    @Test
//    public void removeNonexistingRanking() {
//        service.removeRanking("U", "La-Lah", "Programming");
//    }
}
