package mu.mcb.juice.recruitment.service;

import mu.mcb.juice.recruitment.dto.InstructorDto;

import java.util.List;

/**
 * @author Brume
 **/
public interface InstructorService {
    InstructorDto create(InstructorDto courseDto);
    InstructorDto update(InstructorDto courseDto);
    InstructorDto findById(Integer id);
    List<InstructorDto> findAll();
    void delete(Integer id);
}
