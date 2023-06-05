package com.ejm.config;

import com.ejm.code.dto.CodeDTO;
import com.ejm.code.entity.Code;
import com.ejm.group.entity.Group;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * STRICT는 소스 객체와 대상 객체의 속성 이름이 정확히 일치하는 경우에만 ModelMapper에 매핑하도록 지시합니다. 잘못된 매핑을 방지하는 것이 좋습니다.
 * setSkipNullEnabled(true)는 ModelMapper에 null 값을 건너뛰도록 지시합니다. null인 소스 속성은 매핑되지 않습니다.
 */
@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        // define converter
        Converter<Group, Long> groupToGroupId = context -> context.getSource() == null ? null : context.getSource().getId();

        // add property mapping
        TypeMap<Code, CodeDTO> typeMap = modelMapper.createTypeMap(Code.class, CodeDTO.class);
        typeMap.addMappings(mapper -> mapper.using(groupToGroupId).map(Code::getGroup, CodeDTO::setGroupId));

        return modelMapper;
    }
}
