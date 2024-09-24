package msad.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import msad.domain.*;
import msad.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class Approved extends AbstractEvent {

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

    public Approved(Advertisement aggregate) {
        super(aggregate);
    }

    public Approved() {
        super();
    }
}
//>>> DDD / Domain Event
