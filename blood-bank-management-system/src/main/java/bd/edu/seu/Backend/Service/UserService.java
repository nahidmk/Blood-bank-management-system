package bd.edu.seu.Backend.Service;

import bd.edu.seu.Backend.Model.User;
import bd.edu.seu.Backend.Repository.UserRepository;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void SaveUser(User user)
    {
        if(!userRepository.findById(user.getId()).isPresent()) {
            userRepository.save(user);
        }
    }

    public List<User> FindAll()
    {
        return userRepository.findAll();
    }

    public void DeleteUser(User user)
    {
        userRepository.delete(user);
    }

    public List<User> FindByName(String name)
    {
        return userRepository.findByName(name);
    }

    public long getMaxId(){
        return userRepository.getMaxId()+1;
    }

    public User getUser(String phone){
        User user = userRepository.getUser(phone);
        if(user==null) user=new User();

        return user;
    }
}
