package bd.edu.seu.Backend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Donor extends AbstractEntity implements Cloneable {
    private String name;
    private String address;
    private String phone;
    private String email;
    private String account;
    private String bloodGroup;

}
