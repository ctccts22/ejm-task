package com.ejm.code.controller;

import com.ejm.code.dto.CodeDTO;
import com.ejm.code.service.CodeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/codes")
@AllArgsConstructor
@Slf4j
public class CodeController {

    private final CodeService codeService;

    @PostMapping
    public ResponseEntity<CodeDTO> createCode(@RequestBody CodeDTO codeDTO) {
        return ResponseEntity.ok(codeService.createCode(codeDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CodeDTO> getCode(@PathVariable Long id) {
        return ResponseEntity.ok(codeService.getCode(id));
    }

    @PutMapping
    public ResponseEntity<CodeDTO> updateCode(@RequestBody CodeDTO codeDTO) {
        return ResponseEntity.ok(codeService.updateCode(codeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCode(@PathVariable Long id) {
        codeService.deleteCode(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<CodeDTO>> getCodesByGroup(@PathVariable Long groupId) {
        return ResponseEntity.ok(codeService.getCodesByGroup(groupId));
    }
}
