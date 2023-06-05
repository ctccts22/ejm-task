package com.ejm.group.service;

import com.ejm.group.dto.GroupDTO;

public interface GroupService {
    GroupDTO createGroup(GroupDTO groupDTO);
    GroupDTO getGroup(Long id);
    GroupDTO updateGroup(GroupDTO groupDTO);
    void deleteGroup(Long id);
}
