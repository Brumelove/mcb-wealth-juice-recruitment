package mu.mcb.juice.recruitment.mapper;

import lombok.RequiredArgsConstructor;
import mu.mcb.juice.recruitment.dao.InstructorDao;
import mu.mcb.juice.recruitment.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Brume
 **/

@ActiveProfiles("test")
@RequiredArgsConstructor
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JuiceMapperTest {
    @Autowired
    JuiceMapper mapper;

    @Test
    void mapUserModelToDto() {
        var userModel = new User();
        userModel.setUserName("brume");
        var userDao = JuiceMapper.getJuiceMapper().mapUserModelToDto(userModel);

        assertNotNull(userDao);
        assertEquals(userModel.getUserName(), userDao.getUserName());

    }

    @Test
    void mapInstructorDaoToModel() {
        var instructorDao = new InstructorDao();
        instructorDao.setDepartmentName("COMP");

        var instructor = JuiceMapper.getJuiceMapper().mapInstructorDtoToMapper(instructorDao);

        assertNotNull(instructor);
        assertEquals(instructorDao.getDepartmentName(), instructor.getDepartmentName());

    }

}