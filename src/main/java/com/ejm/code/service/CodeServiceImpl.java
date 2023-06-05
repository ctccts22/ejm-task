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

/**
 * CodeServiceImpl 클래스는 CodeService 인터페이스를 구현하며, 코드 관련 비즈니스 로직을 처리합니다.
 */
@Service
@AllArgsConstructor
@Slf4j
public class CodeServiceImpl implements CodeService{

    private final CodeRepository codeRepository;
    private final GroupRepository groupRepository;
    private final ModelMapper modelMapper;

    /**
     * 새로운 코드를 생성하는 메서드입니다.
     *
     * @param codeDTO 생성하려는 코드 정보.
     * @return 생성된 코드.
     */
    @Override
    @Transactional
    public CodeDTO createCode(CodeDTO codeDTO) {
        log.info("Attempting to create code with name: {}", codeDTO.getName());
        Code code = new Code(codeDTO);

        Group group = groupRepository.findById(codeDTO.getGroupId())
                .orElseThrow(() -> {
                    log.error("Group not found with id: {}", codeDTO.getGroupId());
                    return new EntityNotFoundException("Group not found");
                });

        code.setGroup(group);

        Code createdCode = codeRepository.save(code);
        log.info("Successfully created code with id: {}", createdCode.getId());

        return modelMapper.map(createdCode, CodeDTO.class);
    }

    /**
     * 주어진 ID에 해당하는 코드를 반환하는 메서드입니다.
     *
     * @param id 검색하려는 코드의 ID.
     * @return 검색된 코드.
     */
    @Override
    @Transactional(readOnly = true)
    public CodeDTO getCode(Long id) {
        log.info("Attempting to fetch code with id: {}", id);
        Code code = codeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Code not found with id: {}", id);
                    return new EntityNotFoundException("Code not found");
                });

        CodeDTO codeDTO = modelMapper.map(code, CodeDTO.class);
        codeDTO.setGroupId(code.getGroup().getId());

        log.info("Successfully fetched code with id: {}", id);
        return codeDTO;
    }

    /**
     * 주어진 코드 정보로 기존 코드를 업데이트하는 메서드입니다.
     *
     * @param codeDTO 업데이트하려는 코드 정보.
     * @return 업데이트된 코드.
     */
    @Override
    @Transactional
    public CodeDTO updateCode(CodeDTO codeDTO) {
        log.info("Attempting to update code with id: {}", codeDTO.getId());
        Code existingCode = codeRepository.findById(codeDTO.getId())
                .orElseThrow(() -> {
                    log.error("Code not found with id: {}", codeDTO.getId());
                    return new EntityNotFoundException("Code not found");
                });

        Group group = groupRepository.findById(codeDTO.getGroupId())
                .orElseThrow(() -> {
                    log.error("Group not found with id: {}", codeDTO.getGroupId());
                    return new EntityNotFoundException("Group not found");
                });

        existingCode.setName(codeDTO.getName());
        existingCode.setValue(codeDTO.getValue());
        existingCode.setGroup(group);

        Code updatedCode = codeRepository.save(existingCode);
        CodeDTO updatedCodeDTO = modelMapper.map(updatedCode, CodeDTO.class);
        updatedCodeDTO.setGroupId(updatedCode.getGroup().getId());

        log.info("Successfully updated code with id: {}", updatedCode.getId());
        return updatedCodeDTO;
    }

    /**
     * 주어진 ID에 해당하는 코드를 삭제하는 메서드입니다.
     *
     * @param id 삭제하려는 코드의 ID.
     */
    @Override
    @Transactional
    public void deleteCode(Long id) {
        log.info("Attempting to delete code with id: {}", id);
        codeRepository.deleteById(id);
        log.info("Successfully deleted code with id: {}", id);
    }

    /**
     * 주어진 그룹 ID에 해당하는 모든 코드를 검색하는 메서드입니다.
     *
     * @param groupId 검색하려는 그룹의 ID.
     * @return 검색된 코드 리스트.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CodeDTO> getCodesByGroup(Long groupId) {
        log.info("Attempting to fetch codes for group id: {}", groupId);
        List<Code> codes = codeRepository.findByGroupId(groupId);
        log.info("Found {} codes for group id: {}", codes.size(), groupId);

        return codes.stream()
                .map(code -> modelMapper.map(code, CodeDTO.class))
                .collect(Collectors.toList());
    }
}
