package com.ejm.group.entity;

import com.ejm.code.entity.Code;
import com.ejm.group.dto.GroupDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Group 클래스는 'code_groups' 테이블의 데이터를 표현하는 엔티티 클래스입니다.
 * 이 클래스는 그룹의 ID, 이름, 그리고 관련된 코드 목록을 속성으로 갖습니다.
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "code_groups")
public class Group {

    /**
     * 그룹의 ID. 데이터베이스에서 자동으로 생성됩니다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 그룹의 이름. 이 속성은 null이 될 수 없습니다.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 이 그룹에 연결된 코드들의 목록입니다.
     * OneToMany 연관관계로 지정되었으며, 'group' 필드를 참조합니다.
     */
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Code> codes;

    /**
     * 코드 목록을 설정하는 Setter 메서드입니다.
     * @param codes 이 그룹과 연결할 코드 목록.
     */
    public void setCodes(List<Code> codes) {
        this.codes = codes;
    }

    /**
     * GroupDTO를 받아서 새로운 그룹 인스턴스를 생성하는 생성자입니다.
     * @param groupDTO 그룹 정보를 담은 DTO 객체.
     */
    public Group(GroupDTO groupDTO) {
        this.name = groupDTO.getName();
    }

    /**
     * 그룹 이름을 업데이트하는 Setter 메서드입니다.
     * @param name 새로운 그룹 이름.
     */
    public void setName(String name) {
        this.name = name;
    }
}
