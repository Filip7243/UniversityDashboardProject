package com.fxproject.unidashboard.mapper;

import com.fxproject.unidashboard.dto.UniversityAccountDto;
import com.fxproject.unidashboard.model.UniversityAccount;

import java.util.List;

public class UniversityAccountMapper {

    private UniversityAccountMapper() {
    }

    public static UniversityAccountDto mapToUniversityAccountDto(UniversityAccount account) {
        return new UniversityAccountDto(
                account.getMember(),
                account.getUniversityEmail(),
                account.getPassword(),
                account.getRole(),
                account.getCreatedAt(),
                account.getEnabled()
        );
    }


    public static List<UniversityAccountDto> mapToUniversityAccountDtos(List<UniversityAccount> all) {
        return all.stream()
                .map(UniversityAccountMapper::mapToUniversityAccountDto).toList();
    }
}
