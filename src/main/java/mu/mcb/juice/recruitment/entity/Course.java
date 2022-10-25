package mu.mcb.juice.recruitment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@NoArgsConstructor
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private  String name;
    private  String departmentName;
    private  Integer instructorId;
    private  Integer duration;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="course_student",
            joinColumns={@JoinColumn(name="course_id")},
            inverseJoinColumns={@JoinColumn(name="student_id")})
    private Student student;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Course course = (Course) o;
        return id != null && Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
