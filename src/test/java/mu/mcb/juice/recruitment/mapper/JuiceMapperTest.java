package mu.mcb.juice.recruitment.mapper;

import mu.mcb.juice.recruitment.ApplicantTestConfig;
import mu.mcb.juice.recruitment.dao.InstructorDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Brume
 **/
@ContextConfiguration(classes = {ApplicantTestConfig.class})
class JuiceMapperTest {
    @Autowired
    JuiceMapper mapper;

    @Test
    void mapInstructorDaoToModel() {
        var instructorDao = new InstructorDao();
        instructorDao.setDepartmentName("COMP");

        var instructor = JuiceMapper.getJuiceMapper().mapInstructorDtoToMapper(instructorDao);

        assertNotNull(instructor);
        assertEquals(instructorDao.getDepartmentName(), instructor.getDepartmentName());

    }

}