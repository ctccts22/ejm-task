package com.ejm.group.entity;

import com.ejm.code.entity.Code;
import com.ejm.group.dto.GroupDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "code_groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Code> codes;

    public void setCodes(List<Code> codes) {
        this.codes = codes;
    }

    public Group(GroupDTO groupDTO) {
        this.name = groupDTO.getName();
    }

    // Update을 위한 Setter 생성
    public void setName(String name) {
        this.name = name;
    }
}