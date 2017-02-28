package proj3;

import com.olegdev.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.testng.Assert;

import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;

public class HibernateRankingService implements RankingService {

    private Person findPerson(Session session, String name) {
        Query<Person> query = session.createQuery(
                "from Person p " +
                        "where p.name=:name");
        query.setParameter("name", name);
        return query.uniqueResult();
    }

    private Person savePerson (Session session, String name) {
        Person person = findPerson(session, name);
        if (person == null) {
            person = new Person();
            person.setName(name);
            session.persist(person);
        }
        return person;
    }

    private Skill getSkill(Session session, String name) {
        Query<Skill> query = session.createQuery(
                "from Skill s " +
                        "where s.name = :name");
        query.setParameter("name", name);
        return query.uniqueResult();
    }

    private Skill saveSkill(Session session, String name) {
        Skill skill = getSkill(session, name);
        if(skill==null){
            skill = new Skill();
            skill.setName(name);
            session.persist(skill);
        }
        return skill;
    }

    private Ranking findRanking(Session session, String subject,
                                String observer, String skill) {
        Query<Ranking> query = session.createQuery(
                "from Ranking r " +
                        "where r.subject.name = :subject " +
                        "and r.observer.name = :observer " +
                        "and r.skill.name = :skill");
        query.setParameter("subject", subject);
        query.setParameter("observer", observer);
        query.setParameter("skill", skill);
        Ranking ranking = query.uniqueResult();
        return ranking;
    }

    @Override
    public int getRankingFor(String subject, String skill) {
        try(Session session = SessionUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            int average = getRankingFor(session, subject, skill);
            tx.commit();
            return average;
        }
    }

    private int getRankingFor(Session session, String subject, String skill) {
        Query<Ranking> query = session.createQuery(
                "from Ranking r " +
                        "where r.subject.name=:name " +
                          "and r.skill.name=:skill");
        query.setParameter("name", subject);
        query.setParameter("skill", skill);

        IntSummaryStatistics stats = query
                .list()
                .stream()
                .collect(Collectors.summarizingInt(Ranking::getRanking));
        return (int) stats.getAverage();
    }

    @Override
    public void addRanking(String subjectName, String observerName,
                           String skillName, int rank) {
        try (Session session = SessionUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            addRanking(session, subjectName, observerName, skillName, rank);
            tx.commit();
        }
    }

    private void addRanking(Session session,
                            String subjectName, String observerName,
                            String skillName, int rank) {
        Person subject = savePerson(session, subjectName);
        Person observer = savePerson(session, observerName);
        Skill skill = saveSkill(session, skillName);
        Ranking ranking = new Ranking();
        ranking.setObserver(observer);
        ranking.setSubject(subject);
        ranking.setSkill(skill);
        ranking.setRanking(rank);
        session.save(ranking);
    }

    @Override
    public void updateRanking(String subject, String observer, String skill, int rank) {
        try(Session session = SessionUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            updateRanking(session, subject, observer, skill, rank);
            tx.commit();
        }
    }

    private void updateRanking(Session session, String subject, String observer, String skill, int rank) {
        Ranking ranking = findRanking(session, subject, observer, skill);
        if (ranking == null)
            addRanking(session, subject, observer, skill, rank);
        else
            ranking.setRanking(rank);
    }

    @Override
    public void removeRanking(String subject, String observer, String skill) {
        try(Session session = SessionUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            removeRanking(session, subject, observer, skill);
            tx.commit();
        }
    }

    private void removeRanking(Session session, String subject, String observer, String skill) {
        Ranking ranking = findRanking(session, subject, observer, skill);
        Assert.assertNotNull(ranking, "Ranking not found!");
        session.delete(ranking);
    }


}
