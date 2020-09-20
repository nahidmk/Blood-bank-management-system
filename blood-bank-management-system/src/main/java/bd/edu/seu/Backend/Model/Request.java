package bd.edu.seu.Backend.Model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Request  {
    @Id
    private long id;
    @ManyToOne
    private User user;
    private String bloodGroup;
    private LocalDate sendingDate;
    private LocalDate requreDate;
    private int quantity;
    private String transactionID;
    private String status;
}
