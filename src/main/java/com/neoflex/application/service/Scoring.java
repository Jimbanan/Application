package com.neoflex.application.service;

import com.neoflex.application.dto.LoanApplicationRequestDTO;
import com.neoflex.application.exceptions.ScoringException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class Scoring {

    public void checkScoringData(LoanApplicationRequestDTO loanApplicationRequestDTO) {

        List<String> errors = new ArrayList<>();

        Pattern email = Pattern.compile("^[\\w-\\.]{2,50}@([\\w-]+\\.)+[\\w-]{2,20}");
        Matcher matcher;

        if (loanApplicationRequestDTO.getAmount() == null) {
            log.info("Не указана сумма кредита");
            errors.add("Не указана сумма кредита");
        } else if (loanApplicationRequestDTO.getAmount().compareTo(BigDecimal.valueOf(10000)) <= 0) {
            log.info("checkScoringData(): void Минимальная сумма кредита: 10000");
            errors.add("Минимальная сумма кредита: 10000");
        }

        if (loanApplicationRequestDTO.getTerm() == null) {
            log.info("Не указан срок кредита");
            errors.add("Не указан срок кредита");
        } else if (loanApplicationRequestDTO.getTerm() < 6) {
            log.info("checkScoringData(): void Минимальный срок кредита: 6 месяцев");
            errors.add("Минимальный срок кредита: 6 месяцев");
        }

        if (loanApplicationRequestDTO.getFirstName() == null) {
            log.info("Не указано имя");
            errors.add("Не указано имя");
        } else if (loanApplicationRequestDTO.getFirstName().length() < 2 && loanApplicationRequestDTO.getFirstName().length() > 30) {
            log.info("checkScoringData(): void Длина имени: 2-30 символов");
            errors.add("Длина имени: 2-30 символов");
        }

        if (loanApplicationRequestDTO.getLastName() == null) {
            log.info("Не указана фамилия");
            errors.add("Не указана фамилия");
        } else if (loanApplicationRequestDTO.getLastName().length() < 2 && loanApplicationRequestDTO.getLastName().length() > 30) {
            log.info("checkScoringData(): void Длина фамилии: 2-30 символов");
            errors.add("Длина фамилии: 2-30 символов");
        }


        if (loanApplicationRequestDTO.getMiddleName() == null) {
            log.info("Не указано отчество");
            errors.add("Не указано отчество");
        } else if (loanApplicationRequestDTO.getMiddleName().length() > 0) {
            if (loanApplicationRequestDTO.getMiddleName().length() < 2 && loanApplicationRequestDTO.getMiddleName().length() > 30) {
                log.info("checkScoringData(): void Длина отчества: 2-30 символов");
                errors.add("Длина отчества: 2-30 символов");
            }
        }

        if (loanApplicationRequestDTO.getEmail() == null) {
            log.info("Не указана электронная почта");
            errors.add("Не указана электронная почта");
        } else {
            matcher = email.matcher(loanApplicationRequestDTO.getEmail());
            if (!matcher.find()) {
                log.info("checkScoringData(): void Почта имеет некорректный вид");
                errors.add("Почта имеет некорректный вид");
            }
        }

        if (loanApplicationRequestDTO.getBirthdate() == null) {
            log.info("Не указана дата рождения");
            errors.add("Не указана дата рождения");
        } else if (0 <= loanApplicationRequestDTO.getBirthdate().plus(18, ChronoUnit.YEARS).compareTo(LocalDate.now())) {
            log.info("checkScoringData(): void Возраст меньше 18 лет");
            errors.add("Возраст меньше 18 лет");
        }

        if (loanApplicationRequestDTO.getPassportSeries() == null) {
            log.info("Не указана серия паспорта");
            errors.add("Не указана серия паспорта");
        } else if (loanApplicationRequestDTO.getPassportSeries().length() != 4) {
            log.info("checkScoringData(): void Длина серии паспорта: 4 символа");
            errors.add("Длина серии паспорта: 4 символа");
        }

        if (loanApplicationRequestDTO.getPassportNumber() == null) {
            log.info("Не указан номер паспорта");
            errors.add("Не указан номер паспорта");
        } else if (loanApplicationRequestDTO.getPassportNumber().length() != 6) {
            log.info("checkScoringData(): void Длина номера паспорта: 6 символа");
            errors.add("Длина номера паспорта: 6 символа");
        }

        if (errors.size() > 0) {
            log.info("checkScoringData(): void Передача ошибок");
            throw new ScoringException(errors);
        }

    }

}
