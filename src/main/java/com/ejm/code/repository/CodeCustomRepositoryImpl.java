package com.ejm.code.repository;

import com.ejm.code.entity.Code;
import com.ejm.code.entity.QCode;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

/**
 * CodeCustomRepositoryImpl는 QuerydslRepositorySupport를 상속하여 커스텀 리포지토리를 구현합니다.
 * 특정 그룹 ID를 기반으로 코드를 검색하는 기능을 제공합니다.
 */
public class CodeCustomRepositoryImpl extends QuerydslRepositorySupport implements CodeCustomRepository {

    /**
     * QCode 인스턴스를 생성합니다. 이 인스턴스는 Querydsl 쿼리를 작성하는데 사용됩니다.
     */
    private static final QCode qCode = QCode.code;

    /**
     * 생성자에서는 부모 클래스인 QuerydslRepositorySupport의 생성자를 호출하여,
     * 관리하려는 엔티티 클래스를 지정합니다.
     */
    public CodeCustomRepositoryImpl() {
        super(Code.class);
    }

    /**
     * 주어진 그룹 ID에 해당하는 모든 코드를 검색하는 메서드입니다.
     *
     * @param groupId 검색하려는 그룹의 ID.
     * @return 주어진 그룹 ID에 해당하는 모든 코드.
     */
    @Override
    public List<Code> findByGroupId(Long groupId) {
        return from(qCode)
                .select(qCode)
                .where(qCode.group.id.eq(groupId))
                .fetch();
    }
}
