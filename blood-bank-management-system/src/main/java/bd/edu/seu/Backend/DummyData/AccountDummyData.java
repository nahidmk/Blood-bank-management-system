package bd.edu.seu.Backend.DummyData;

import bd.edu.seu.Backend.Model.*;
import bd.edu.seu.Backend.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Service
public class AccountDummyData {

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    @Autowired
    private DonorService donorService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private RequestService requestService;

    User user1 = new User(1,"mk","cp","+987","email.com");
    User user2 = new User(2,"jb","bb","+987","email.com");

    Donor donor1 = new Donor("mk","cp","+09","email.com","AC:099", "A(+ve)");
    Donor donor2 = new Donor("jb","bb","+09","email.com","AC:089", "B(+ve)");

    Notice notice1 = new Notice(LocalDate.now(),"from nahid");
    Notice notice2 = new Notice(LocalDate.now(),"form joniyed");

    Request request1 = new Request(1,user1,"O(+ve)",LocalDate.now(),LocalDate.now(),4,"-","request");
    Request request2 = new Request(2,user1,"AB(+ve)",LocalDate.now(),LocalDate.now(),6,"-","request");

    Account account1 = new Account("nahid","012345", LocalDate.now(),"+345");
    Account account2 = new Account("joniyed","012345", LocalDate.now(),"-345");

//    @PostConstruct
    private void save()
    {
        userService.SaveUser(user1);
        userService.SaveUser(user2);

        donorService.SaveDonor(donor1);
        donorService.SaveDonor(donor2);

        requestService.SaveRequest(request1);
        requestService.SaveRequest(request2);

        noticeService.SaveNotice(notice1);
        noticeService.SaveNotice(notice2);

        accountService.SaveAccount(account1);
        accountService.SaveAccount(account2);
//        noticeService.DeleteNotice(notice1);
//        noticeService.DeleteNotice(notice2);

    }
}
