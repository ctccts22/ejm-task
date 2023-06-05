package com.ejm.code.service;

import com.ejm.group.dto.GroupDTO;
import com.ejm.group.entity.Group;
import com.ejm.group.repository.GroupRepository;
import com.ejm.group.service.GroupServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private GroupServiceImpl groupService;

    @Test
    void createGroupTest() {
        // Given
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setName("Group1");

        Group group = new Group();
        group.setName("Group1");

        when(modelMapper.map(any(Group.class), eq(GroupDTO.class))).thenReturn(groupDTO);
        when(groupRepository.save(any(Group.class))).thenReturn(group);


        // When
        GroupDTO result = groupService.createGroup(groupDTO);

        // Then
        assertNotNull(result);
        assertEquals(groupDTO.getName(), result.getName());
    }
}
