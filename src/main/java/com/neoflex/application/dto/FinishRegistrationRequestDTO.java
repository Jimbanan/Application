package com.neoflex.application.dto;

import com.neoflex.application.enums.Genders;
import com.neoflex.application.enums.MaritalStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность итоговых данных пользователя")
public class FinishRegistrationRequestDTO {

    private Genders genders;
    private MaritalStatus maritalStatus;
    private Integer dependentAmount;
    private LocalDate passportIssueDate;
    private String passportIssueBrach;
    private EmploymentDTO employment;
    private String account;

}
