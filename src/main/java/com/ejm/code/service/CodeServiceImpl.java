package com.ejm.code.service;

import com.ejm.code.dto.CodeDTO;
import com.ejm.code.entity.Code;
import com.ejm.code.repository.CodeRepository;
import com.ejm.group.entity.Group;
import com.ejm.group.repository.GroupRepository;
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
    private final GroupRepository groupRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public CodeDTO createCode(CodeDTO codeDTO) {
        Code code = new Code(codeDTO);

        Group group = groupRepository.findById(codeDTO.getGroupId())
                .orElseThrow(() -> new EntityNotFoundException("Group not found"));

        code.setGroup(group);

        Code createdCode = codeRepository.save(code);

        return modelMapper.map(createdCode, CodeDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public CodeDTO getCode(Long id) {
        Code code = codeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Code not found"));

        CodeDTO codeDTO = modelMapper.map(code, CodeDTO.class);
        codeDTO.setGroupId(code.getGroup().getId()); // Add this line

        return codeDTO;
    }


    @Override
    @Transactional
    public CodeDTO updateCode(CodeDTO codeDTO) {
        Code existingCode = codeRepository.findById(codeDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Code not found"));

        // Fetch Group based on groupId from codeDTO
        Group group = groupRepository.findById(codeDTO.getGroupId())
                .orElseThrow(() -> new EntityNotFoundException("Group not found"));

        // Set new values from codeDTO
        existingCode.setName(codeDTO.getName());
        existingCode.setValue(codeDTO.getValue());
        existingCode.setGroup(group);

        Code updatedCode = codeRepository.save(existingCode);
        CodeDTO updatedCodeDTO = modelMapper.map(updatedCode, CodeDTO.class);
        updatedCodeDTO.setGroupId(updatedCode.getGroup().getId()); // Add this line

        return updatedCodeDTO;
    }


    @Override
    @Transactional
    public void deleteCode(Long id) {
        codeRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CodeDTO> getCodesByGroup(Long groupId) {
        log.info("Fetching codes for group id: {}", groupId);
        List<Code> codes = codeRepository.findByGroupId(groupId);
        if (codes.isEmpty()) {
            log.warn("No codes found for group id: {}", groupId);
        } else {
            log.info("Found {} codes for group id: {}", codes.size(), groupId);
        }
        return codes.stream()
                .map(code -> modelMapper.map(code, CodeDTO.class))
                .collect(Collectors.toList());
    }
}
