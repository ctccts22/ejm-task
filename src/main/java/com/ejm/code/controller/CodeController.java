package com.ejm.code.controller;

import com.ejm.code.dto.CodeDTO;
import com.ejm.code.service.CodeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CodeController는 코드와 관련된 HTTP 요청을 처리하는 컨트롤러입니다.
 * 이 클래스는 '/api/codes' 엔드포인트에 대한 처리를 담당하며, 각 HTTP 메소드에 대한 처리를 정의합니다.
 */
@RestController
@RequestMapping("/api/codes")
@AllArgsConstructor
@Slf4j
public class CodeController {

    private final CodeService codeService;

    /**
     * 새로운 코드를 생성하는 HTTP POST 요청을 처리합니다.
     *
     * @param codeDTO 생성할 코드 정보를 담은 DTO.
     * @return 생성된 코드 정보를 담은 응답 엔티티.
     */
    @PostMapping
    public ResponseEntity<CodeDTO> createCode(@RequestBody CodeDTO codeDTO) {
        log.info("Received request to create code with name: {}", codeDTO.getName());
        CodeDTO createdCode = codeService.createCode(codeDTO);
        log.info("Successfully created code with id: {}", createdCode.getId());
        return ResponseEntity.ok(createdCode);
    }
    /**
     * 주어진 ID에 해당하는 코드 정보를 조회하는 HTTP GET 요청을 처리합니다.
     *
     * @param id 조회할 코드의 ID.
     * @return 조회된 코드 정보를 담은 응답 엔티티.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CodeDTO> getCode(@PathVariable Long id) {
        log.info("Received request to fetch code with id: {}", id);
        CodeDTO fetchedCode = codeService.getCode(id);
        log.info("Successfully fetched code with id: {}", fetchedCode.getId());
        return ResponseEntity.ok(fetchedCode);
    }

    /**
     * 코드 정보를 업데이트하는 HTTP PUT 요청을 처리합니다.
     *
     * @param codeDTO 업데이트할 코드 정보를 담은 DTO.
     * @return 업데이트된 코드 정보를 담은 응답 엔티티.
     */
    @PutMapping
    public ResponseEntity<CodeDTO> updateCode(@RequestBody CodeDTO codeDTO) {
        log.info("Received request to update code with id: {}", codeDTO.getId());
        CodeDTO updatedCode = codeService.updateCode(codeDTO);
        log.info("Successfully updated code with id: {}", updatedCode.getId());
        return ResponseEntity.ok(updatedCode);
    }

    /**
     * 주어진 ID에 해당하는 코드를 삭제하는 HTTP DELETE 요청을 처리합니다.
     *
     * @param id 삭제할 코드의 ID.
     * @return 상태 코드를 포함한 응답 엔티티.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCode(@PathVariable Long id) {
        log.info("Received request to delete code with id: {}", id);
        codeService.deleteCode(id);
        log.info("Successfully deleted code with id: {}", id);
        return ResponseEntity.ok().build();
    }

    /**
     * 주어진 그룹 ID에 해당하는 모든 코드를 조회하는 HTTP GET 요청을 처리합니다.
     *
     * @param groupId 조회할 코드의 그룹 ID.
     * @return 조회된 코드 목록을 담은 응답 엔티티.
     */
    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<CodeDTO>> getCodesByGroup(@PathVariable Long groupId) {
        log.info("Received request to fetch codes by group id: {}", groupId);
        List<CodeDTO> codes = codeService.getCodesByGroup(groupId);
        log.info("Successfully fetched {} codes for group id: {}", codes.size(), groupId);
        return ResponseEntity.ok(codes);
    }
}
