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
public class Notice extends AbstractEntity implements Cloneable {
    private LocalDate date;
    private String note;
}
