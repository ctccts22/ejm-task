package com.ejm.group.service;

import com.ejm.code.dto.CodeDTO;
import com.ejm.code.entity.Code;
import com.ejm.code.repository.CodeRepository;
import com.ejm.code.service.CodeServiceImpl;
import com.ejm.group.entity.Group;
import com.ejm.group.repository.GroupRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CodeServiceTest {

    @Mock
    private CodeRepository codeRepository;

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CodeServiceImpl codeService;

    @Test
    void createCodeTest() {
        // given
        Group group = new Group();
        group.setName("Group1");

        CodeDTO codeDTO = new CodeDTO();
        codeDTO.setName("Code1");
        codeDTO.setValue("Value1");
        codeDTO.setGroupId(group.getId());

        Code code = new Code();
        code.setName("Code1");
        code.setValue("Value1");
        code.setGroup(group);

        when(groupRepository.findById(codeDTO.getGroupId())).thenReturn(Optional.of(group));
        when(codeRepository.save(any(Code.class))).thenReturn(code);
        when(modelMapper.map(code, CodeDTO.class)).thenReturn(codeDTO);

        // when
        CodeDTO result = codeService.createCode(codeDTO);

        // then
        assertNotNull(result);
        assertEquals(codeDTO.getName(), result.getName());
        assertEquals(codeDTO.getValue(), result.getValue());
        assertEquals(codeDTO.getGroupId(), result.getGroupId());
    }
}
