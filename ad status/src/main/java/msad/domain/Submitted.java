package msad.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;
import msad.infra.AbstractEvent;

@Data
public class Submitted extends AbstractEvent {

    private Long id;
    private String materialName;
    private String materialUrl;
    private String title;
    private String content;
    private Long budget;
    private String target;
    private Long targetImpressions;
    private String status;
    private Date startDate;
    private Date endDate;
    private Long materialId;
}
