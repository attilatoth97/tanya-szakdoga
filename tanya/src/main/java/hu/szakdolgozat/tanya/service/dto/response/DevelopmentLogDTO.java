package hu.szakdolgozat.tanya.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DevelopmentLogDTO {

    private Long id;

    private Long userId;
    private String userName;

    private Long projectId;
    private String projectName;

    private Long taskId;
    private String taskName;

    private String description;

    private Byte developedHours;

    private Instant day;
}
