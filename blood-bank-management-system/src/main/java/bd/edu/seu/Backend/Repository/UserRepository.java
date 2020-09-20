package bd.edu.seu.Backend.Repository;

import bd.edu.seu.Backend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public List<User> findByName(String name);
    @Query(value = "SELECT MAX(id) FROM user", nativeQuery = true)
    public long getMaxId();

    @Query(value = "SELECT * from user WHERE phone=?",nativeQuery = true)
    User getUser(String phone);

}
