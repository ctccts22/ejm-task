package com.ejm.code.repository;

import com.ejm.code.entity.Code;
import com.ejm.code.entity.QCode;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CodeCustomRepositoryImpl extends QuerydslRepositorySupport implements CodeCustomRepository {

    private static final QCode qCode = QCode.code;

    public CodeCustomRepositoryImpl() {
        super(Code.class);
    }

    @Override
    public List<Code> findByGroupId(Long groupId) {
        return from(qCode)
                .select(qCode)
                .where(qCode.group.id.eq(groupId))
                .fetch();
    }
}
