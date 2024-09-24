package msad.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;
import msad.infra.AbstractEvent;

@Data
public class Fed extends AbstractEvent {

    private Long id;
    private Long adId;
    private Long materialId;
    private String materialName;
    private String materialUrl;
    private Date startDate;
    private Date endDate;
    private Long impressions;
    private String status;
    private String target;
}
