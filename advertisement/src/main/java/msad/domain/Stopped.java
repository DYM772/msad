package msad.domain;

import java.util.*;
import lombok.*;
import msad.domain.*;
import msad.infra.AbstractEvent;

@Data
@ToString
public class Stopped extends AbstractEvent {

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
