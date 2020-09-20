package bd.edu.seu.Backend.Repository;

import bd.edu.seu.Backend.Model.BloodGroup;
import bd.edu.seu.Backend.Model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DonorRepository extends JpaRepository<Donor,Long> {

    public List<Donor> findByBloodGroup(String bloodGroup);
}
