package bd.edu.seu.Backend.Service;

import bd.edu.seu.Backend.Model.Request;
import bd.edu.seu.Backend.Repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public void SaveRequest(Request request)
    {
        if(!requestRepository.existsById(request.getId())) {
            requestRepository.save(request);
        }
    }

    public void DeleteRequest(Request request)
    {
        requestRepository.delete(request);
    }

    public List<Request> findAll()
    {
        return requestRepository.findAll();
    }
    public List<Request> FindBySendingDate(LocalDate date,long id)
    {
        return requestRepository.findBySendingDateForSingle(date,id);
    }
    public List<Request> FindByRequredDate(LocalDate date,long id)
    {
        return requestRepository.findByRequreDateForSingle(date,id);
    }

    public long getMaxId(){
        return requestRepository.getMaxId();
    }

    public List<Request> getUserRequest (long id){
        return requestRepository.getUserRequest(id);
    }

    public void update(Request request){
        if(requestRepository.existsById(request.getId())){
            requestRepository.deleteById(request.getId());
            requestRepository.save(request);
        }
    }

    public List<Request> FindBySendingDateForAll(LocalDate date)
    {
        return requestRepository.findBySendingDate(date);
    }
    public List<Request> FindByRequredDateForAll(LocalDate date)
    {
        return requestRepository.findByRequreDate(date);
    }



}
