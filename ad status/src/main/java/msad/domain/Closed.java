package msad.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;
import msad.infra.AbstractEvent;

@Data
public class Closed extends AbstractEvent {

    private Long id;
    private String title;
    private String content;
    private Long budget;
    private String status;
    private String materialName;
    private String materialUrl;
    private String target;
    private Long targetImpressions;
    private Date startDate;
    private Date endDate;
    private Long materialId;
}
