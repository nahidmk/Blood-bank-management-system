package bd.edu.seu.Backend.Repository;

import bd.edu.seu.Backend.Model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface RequestRepository extends JpaRepository<Request,Long> {

    @Query(value = "SELECT * from request WHERE sending_date=? AND user_id=?",nativeQuery = true)
    public List<Request> findBySendingDateForSingle(LocalDate date,long id);

    @Query(value = "SELECT * from request WHERE requre_date=? AND user_id=?",nativeQuery = true)
    public List<Request> findByRequreDateForSingle(LocalDate date,long id);


    @Query(value = "SELECT MAX(id) FROM request", nativeQuery = true)
    public long getMaxId();

    @Query(value = "SELECT * FROM request WHERE user_id=?",nativeQuery = true)
    List<Request> getUserRequest(long user_id);


    public List<Request> findBySendingDate(LocalDate date);
    public List<Request> findByRequreDate(LocalDate date);



}
