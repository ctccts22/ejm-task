package com.ejm.code.service;

import com.ejm.code.dto.CodeDTO;
import com.ejm.code.entity.Code;

import java.util.List;

public interface CodeService {
    CodeDTO createCode(CodeDTO codeDTO);
    CodeDTO getCode(Long id);
    CodeDTO updateCode(CodeDTO codeDTO);
    void deleteCode(Long id);
    List<CodeDTO> getCodesByGroup(Long groupId);
}


