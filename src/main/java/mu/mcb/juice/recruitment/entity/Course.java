package mu.mcb.juice.recruitment.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Student student;

}
