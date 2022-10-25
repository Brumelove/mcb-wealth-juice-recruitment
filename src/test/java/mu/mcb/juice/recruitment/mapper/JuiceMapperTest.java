package mu.mcb.juice.recruitment.mapper;

import mu.mcb.juice.recruitment.dao.InstructorDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Brume
 **/
class JuiceMapperTest {
    @Autowired
    JuiceMapper mapper;

    @Test
    void mapInstructorDaoToModel() {
        var instructorDao = new InstructorDao();
        instructorDao.setDepartmentName("COMP");

        var instructor = mapper.mapInstructorDtoToMapper(instructorDao);

        assertNotNull(instructor);
        assertEquals(instructorDao.getDepartmentName(), instructor.getDepartmentName());

    }

}