package bd.edu.seu.Backend.Service;

import bd.edu.seu.Backend.Model.Notice;
import bd.edu.seu.Backend.Repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;


    public void SaveNotice(Notice notice)
    {
        noticeRepository.save(notice);
    }

    public void DeleteNotice(Notice notice)
    {
        noticeRepository.delete(notice);
    }

    public List<Notice> findAll()
    {
        return noticeRepository.findAll();
    }

    public List<Notice> findByDate(LocalDate date)
    {
        return noticeRepository.findByDate(date);
    }
}
