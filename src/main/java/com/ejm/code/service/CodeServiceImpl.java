package com.ejm.code.service;

import com.ejm.code.dto.CodeDTO;
import com.ejm.code.entity.Code;
import com.ejm.code.repository.CodeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CodeServiceImpl implements CodeService{

    private final CodeRepository codeRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public CodeDTO createCode(CodeDTO codeDTO) {
        Code code = modelMapper.map(codeDTO, Code.class);
        Code savedCode = codeRepository.save(code);
        return modelMapper.map(savedCode, CodeDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public CodeDTO getCode(Long id) {
        Code code = codeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Code not found"));
        return modelMapper.map(code, CodeDTO.class);
    }

    @Override
    @Transactional
    public CodeDTO updateCode(CodeDTO codeDTO) {
        Code existingCode = codeRepository.findById(codeDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Code not found"));
        modelMapper.map(codeDTO, existingCode);
        Code updatedCode = codeRepository.save(existingCode);
        return modelMapper.map(updatedCode, CodeDTO.class);
    }

    @Override
    @Transactional
    public void deleteCode(Long id) {
        codeRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CodeDTO> getCodesByGroup(Long groupId) {
        return codeRepository.findByGroupId(groupId).stream()
                .map(code -> modelMapper.map(code, CodeDTO.class))
                .collect(Collectors.toList());
    }
}
