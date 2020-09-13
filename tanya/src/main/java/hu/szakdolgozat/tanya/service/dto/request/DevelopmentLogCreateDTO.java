package hu.szakdolgozat.tanya.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Size(min = 0 , max = 24)
    private Byte developedHours;

    @NotNull
    private Instant day;

}
