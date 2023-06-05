package com.ejm.group.repository;


import com.ejm.group.entity.Group;
import com.ejm.group.entity.QGroup;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class GroupCustomRepositoryImpl extends QuerydslRepositorySupport implements GroupCustomRepository {

    private static final QGroup qGroup = QGroup.group;

    public GroupCustomRepositoryImpl() {
        super(Group.class);
    }

}
