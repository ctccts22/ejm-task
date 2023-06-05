package com.ejm.code.repository;

import com.ejm.code.entity.Code;

import java.util.List;

public interface CodeCustomRepository {
    List<Code> findByGroupId(Long groupId);

}
