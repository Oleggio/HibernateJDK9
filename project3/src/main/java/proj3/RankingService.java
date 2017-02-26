package proj3;

public interface RankingService {
    int getRankingFor(String subject, String skill);

    void addRanking(String subject, String observer, String skill, int ranking);
}
