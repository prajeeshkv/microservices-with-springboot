package com.rakbank.fees.mapper;

 
import com.rakbank.fees.model.Fees;
import com.rakbank.fees.model.FeesDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface FeesMapper {
 

    Fees toEntity(FeesDto feesDto);

    @Mapping( target = "createdOn", ignore = true)
    @Mapping( target = "updatedOn", ignore = true)
    FeesDto toDTO(Fees fees);
}
