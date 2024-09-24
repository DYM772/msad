package msad.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import msad.domain.*;
import msad.infra.AbstractEvent;

//<<< DDD / Domain Event
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
    private Long targetImpressions;
    private String status;
    private String target;

    public Stopped(Delivery aggregate) {
        super(aggregate);
    }

    public Stopped() {
        super();
    }
}
//>>> DDD / Domain Event
