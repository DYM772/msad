package msad.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import msad.domain.*;
import msad.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class Canceled extends AbstractEvent {

    private Long id;
    private Long adId;
    private String target;
    private Long inventory;

    public Canceled(Inventory aggregate) {
        super(aggregate);
    }

    public Canceled() {
        super();
    }
}
//>>> DDD / Domain Event
