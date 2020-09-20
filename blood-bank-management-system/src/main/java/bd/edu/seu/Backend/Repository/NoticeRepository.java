package bd.edu.seu.Backend.Repository;

import bd.edu.seu.Backend.Model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface NoticeRepository extends JpaRepository<Notice,Long> {
    public List<Notice> findByDate(LocalDate date);
}
