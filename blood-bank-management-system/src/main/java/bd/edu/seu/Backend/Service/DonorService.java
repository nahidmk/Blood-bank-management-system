package bd.edu.seu.Backend.Service;

import bd.edu.seu.Backend.Model.BloodGroup;
import bd.edu.seu.Backend.Model.Donor;
import bd.edu.seu.Backend.Repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonorService {
    @Autowired
    private DonorRepository donorRepository;

    public void SaveDonor(Donor donor)
    {
        donorRepository.save(donor);
    }
    public void DeleteDonor(Donor donor)
    {
        donorRepository.delete(donor);
    }
    public List<Donor> FindAll()
    {
        return donorRepository.findAll();
    }
    public List<Donor> findByBloodGroup(String bloodGroup)
    {
        return donorRepository.findByBloodGroup(bloodGroup);
    }
}
