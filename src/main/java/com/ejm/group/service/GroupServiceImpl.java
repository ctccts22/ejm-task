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

/**
 * GroupServiceImpl 클래스는 그룹 데이터를 관리하는 서비스 클래스입니다.
 * 이 클래스는 그룹을 생성, 조회, 업데이트, 삭제하는 기능을 제공합니다.
 */
@Service
@AllArgsConstructor
@Slf4j
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    /**
     * 모델 매퍼, Entity와 DTO 사이의 변환에 사용됩니다.
     */
    private final ModelMapper modelMapper;

    /**
     * 새로운 그룹을 생성하는 메서드입니다.
     *
     * @param groupDTO 그룹 정보를 담은 DTO 객체.
     * @return 생성된 그룹의 정보를 담은 DTO 객체.
     */
    @Override
    @Transactional
    public GroupDTO createGroup(GroupDTO groupDTO) {
        Group group = new Group(groupDTO);
        Group createdGroup = groupRepository.save(group);
        return modelMapper.map(createdGroup, GroupDTO.class);
    }

    /**
     * ID를 통해 그룹을 조회하는 메서드입니다.
     *
     * @param id 조회하려는 그룹의 ID.
     * @return 조회된 그룹의 정보를 담은 DTO 객체.
     */
    @Override
    @Transactional(readOnly = true)
    public GroupDTO getGroup(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Group not found"));
        return modelMapper.map(group, GroupDTO.class);
    }

    /**
     * 그룹 정보를 업데이트하는 메서드입니다.
     *
     * @param groupDTO 업데이트하려는 그룹의 정보를 담은 DTO 객체.
     * @return 업데이트된 그룹의 정보를 담은 DTO 객체.
     */
    @Override
    @Transactional
    public GroupDTO updateGroup(GroupDTO groupDTO) {
        Group existingGroup = groupRepository.findById(groupDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Group not found"));
        existingGroup.setName(groupDTO.getName());
        Group updatedGroup = groupRepository.save(existingGroup);
        return modelMapper.map(updatedGroup, GroupDTO.class);
    }

    /**
     * ID를 통해 그룹을 삭제하는 메서드입니다.
     *
     * @param id 삭제하려는 그룹의 ID.
     */
    @Override
    @Transactional
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
}
