package attendance_percentages;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AttendancePercentageDAO {

    private final SessionFactory sessionFactory;

    public AttendancePercentageDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveAttendancePercentage(AttendancePercentage attendancePercentage) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.saveOrUpdate(attendancePercentage);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    public AttendancePercentage findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(AttendancePercentage.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<AttendancePercentage> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM AttendancePercentage", AttendancePercentage.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateAttendancePercentage(AttendancePercentage attendancePercentage) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.update(attendancePercentage);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    public void deleteAttendancePercentage(AttendancePercentage attendancePercentage) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.delete(attendancePercentage);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            }
        }
    }
}
