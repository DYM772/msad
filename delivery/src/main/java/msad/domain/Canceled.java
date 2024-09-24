package msad.domain;

import java.util.*;
import lombok.*;
import msad.domain.*;
import msad.infra.AbstractEvent;

@Data
@ToString
public class Canceled extends AbstractEvent {

    private Long id;
    private Long adId;
    private String target;
    private Long inventory;
}
