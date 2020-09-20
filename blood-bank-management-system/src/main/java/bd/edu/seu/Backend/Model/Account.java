package bd.edu.seu.Backend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account extends AbstractEntity implements Cloneable {
    private String name;
    private String phone;
    private LocalDate transactionDate;
    private String amount;
}
