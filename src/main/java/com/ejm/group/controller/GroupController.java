package com.ejm.group.controller;

import com.ejm.group.dto.GroupDTO;
import com.ejm.group.service.GroupService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * GroupController는 그룹에 대한 CRUD 연산을 수행하는 REST API를 제공합니다.
 * 이 클래스는 /api/groups 경로에 매핑되어 있습니다.
 */
@RestController
@RequestMapping("/api/groups")
@AllArgsConstructor
@Slf4j
public class GroupController {

    private final GroupService groupService;

    /**
     * 새로운 그룹을 생성합니다.
     * 이 메서드는 POST /api/groups 요청을 처리합니다.
     * @param groupDTO 그룹 DTO. 그룹 이름을 포함해야 합니다.
     * @return 생성된 그룹의 DTO.
     */
    @PostMapping
    public ResponseEntity<GroupDTO> createGroup(@RequestBody GroupDTO groupDTO) {
        log.info("Received request to create group with name: {}", groupDTO.getName());
        GroupDTO createdGroup = groupService.createGroup(groupDTO);
        log.info("Successfully created group with id: {}", createdGroup.getId());
        return ResponseEntity.ok(createdGroup);
    }

    /**
     * 특정 그룹의 세부 정보를 가져옵니다.
     * 이 메서드는 GET /api/groups/{id} 요청을 처리합니다.
     * @param id 가져올 그룹의 ID.
     * @return 요청된 ID의 그룹 DTO.
     */
    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroup(@PathVariable Long id) {
        log.info("Received request to fetch group with id: {}", id);
        GroupDTO fetchedGroup = groupService.getGroup(id);
        log.info("Successfully fetched group with id: {}", fetchedGroup.getId());
        return ResponseEntity.ok(fetchedGroup);
    }

    /**
     * 기존 그룹의 정보를 업데이트합니다.
     * 이 메서드는 PUT /api/groups 요청을 처리합니다.
     * @param groupDTO 그룹 DTO. 그룹 ID와 새로운 그룹 이름을 포함해야 합니다.
     * @return 업데이트된 그룹의 DTO.
     */

    @PutMapping
    public ResponseEntity<GroupDTO> updateGroup(@RequestBody GroupDTO groupDTO) {
        log.info("Received request to update group with id: {}", groupDTO.getId());
        GroupDTO updatedGroup = groupService.updateGroup(groupDTO);
        log.info("Successfully updated group with id: {}", updatedGroup.getId());
        return ResponseEntity.ok(updatedGroup);
    }

    /**
     * 특정 그룹을 삭제합니다.
     * 이 메서드는 DELETE /api/groups/{id} 요청을 처리합니다.
     * @param id 삭제할 그룹의 ID.
     * @return HTTP 200 OK 응답.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        log.info("Received request to delete group with id: {}", id);
        groupService.deleteGroup(id);
        log.info("Successfully deleted group with id: {}", id);
        return ResponseEntity.ok().build();
    }
}
