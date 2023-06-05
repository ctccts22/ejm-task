package com.ejm.code.entity;

import com.ejm.code.dto.CodeDTO;
import com.ejm.group.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Code 엔티티는 데이터베이스의 'codes' 테이블과 매핑됩니다.
 * 각 Code 객체는 공통 코드의 한 항목을 나타내며, 이름과 값, 그리고 해당 코드가 속한 그룹 정보를 포함합니다.
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "codes")
public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "value", nullable = false)
    private String value;

    /**
     * ManyToOne 관계로 설정된 Group 필드는 이 코드가 속한 그룹을 나타냅니다.
     * 그룹 ID는 'group_id' 컬럼에 저장됩니다.
     */
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    /**
     * CodeDTO로부터 Code 객체를 생성하는 생성자입니다.
     *
     * @param codeDTO 생성에 사용될 CodeDTO 객체.
     */
    public Code(CodeDTO codeDTO) {
        this.name=codeDTO.getName();
        this.value=codeDTO.getValue();
    }

    /**
     * @setter
     */
    public void setGroup(Group group) {
        this.group = group;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setValue(String value) {
        this.value = value;
    }

}
