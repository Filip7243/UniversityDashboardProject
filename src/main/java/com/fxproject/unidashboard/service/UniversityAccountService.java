package com.fxproject.unidashboard.service;

import com.fxproject.unidashboard.dto.UniversityAccountDto;
import com.fxproject.unidashboard.email.EmailAddressGenerator;
import com.fxproject.unidashboard.mapper.UniversityAccountMapper;
import com.fxproject.unidashboard.model.Role;
import com.fxproject.unidashboard.model.UniversityAccount;
import com.fxproject.unidashboard.model.UniversityMember;
import com.fxproject.unidashboard.repository.UniversityAccountRepository;
import com.fxproject.unidashboard.repository.impl.UniversityAccountRepositoryImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.fxproject.unidashboard.mapper.UniversityAccountMapper.mapToUniversityAccountDto;
import static com.fxproject.unidashboard.mapper.UniversityAccountMapper.mapToUniversityAccountDtos;

public class UniversityAccountService {

    private final UniversityAccountRepository accountRepository = new UniversityAccountRepositoryImpl();

    public void removeAccountWithId(Long id) {
        accountRepository.removeWithId(id);
    }

    public UniversityAccountDto findAccountWithId(Long id) {
        UniversityAccount account = accountRepository.findWithId(id).orElseThrow();
        return mapToUniversityAccountDto(account);
    }

    public void addAccount(UniversityMember member) {
        UniversityAccount account = new UniversityAccount();
        account.setMember(member);
        account.setPassword(UUID.randomUUID().toString()); //todo: email sent to change password
        account.setUniversityEmail("test"); //todo: do generator
        account.setCreatedAt(LocalDateTime.now());
        account.setRole(null);
        account.setEnabled(false); //todo :email confirm

        accountRepository.save(account);
    }

    public List<UniversityAccountDto> findAllAccounts() {
        List<UniversityAccount> all = accountRepository.findAll();
        return mapToUniversityAccountDtos(all);
    }

    public UniversityAccountDto findAccountByUniversityEmail(String universityEmail) {
        UniversityAccount account = accountRepository.findAccountByUniversityEmail(universityEmail).orElseThrow();
        return mapToUniversityAccountDto(account);
    }

    public List<UniversityAccountDto> findAllDisabledAccounts() {
        List<UniversityAccount> all = accountRepository.findAllDisabledAccounts();
        return mapToUniversityAccountDtos(all);
    }

    public List<UniversityAccountDto> findAllEnableAccounts() {
        List<UniversityAccount> all = accountRepository.findAllEnableAccounts();
        return mapToUniversityAccountDtos(all);
    }

    public void updateAccount(String universityEmail, UniversityAccountDto accountDto) {
        UniversityAccount account = accountRepository.findAccountByUniversityEmail(universityEmail).orElseThrow();
        account.setUniversityEmail(accountDto.getUniversityEmail());
        account.setPassword(accountDto.getPassword());
        account.setRole(accountDto.getRole());
        account.setEnabled(accountDto.getEnabled());

        accountRepository.save(account);
    }

    public void addRoleToAccount(String universityEmail, Role role) {
        UniversityAccount account = accountRepository.findAccountByUniversityEmail(universityEmail).orElseThrow();
        account.setRole(role);

        accountRepository.save(account);
    }


}
