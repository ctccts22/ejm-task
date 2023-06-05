package com.ejm.code.entity;

import com.ejm.code.dto.CodeDTO;
import com.ejm.group.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    public Code(CodeDTO codeDTO) {
        this.name=codeDTO.getName();
        this.value=codeDTO.getValue();
    }
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
