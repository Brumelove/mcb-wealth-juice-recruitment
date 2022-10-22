package mu.mcb.juice.recruitment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mu.mcb.juice.recruitment.enumeration.UserRole;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Brume
 **/
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String userName;
    private  String email;
    private  String password;
    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.USER;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}