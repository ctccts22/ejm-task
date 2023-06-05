package com.ejm.group.service;

import com.ejm.group.dto.GroupDTO;
import com.ejm.group.entity.Group;
import com.ejm.group.repository.GroupRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
@Slf4j
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public GroupDTO createGroup(GroupDTO groupDTO) {
        Group group = new Group(groupDTO);
        Group createdGroup = groupRepository.save(group);
        return modelMapper.map(createdGroup, GroupDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public GroupDTO getGroup(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Group not found"));
        return modelMapper.map(group, GroupDTO.class);
    }

    @Override
    @Transactional
    public GroupDTO updateGroup(GroupDTO groupDTO) {
        Group existingGroup = groupRepository.findById(groupDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Group not found"));
        existingGroup.setName(groupDTO.getName());
        Group updatedGroup = groupRepository.save(existingGroup);
        return modelMapper.map(updatedGroup, GroupDTO.class);
    }


    @Override
    @Transactional
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
}

