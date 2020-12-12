package hu.szakdolgozat.pm.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DevelopmentLogCreateDTO {

    @NotBlank
    private String description;

    @NotNull
    private Long projectId;

    @NotNull
    private Long taskId;

    @NotNull
    @Min(0)
    @Max(24)
    private Byte developedHours;

    @NotNull
    private Instant day;

}
