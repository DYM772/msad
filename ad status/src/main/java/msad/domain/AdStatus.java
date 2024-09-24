package msad.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "AdStatus_table")
@Data
public class AdStatus {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Long materialId;
    private String materialName;
    private String materialUrl;
    private Long adId;
    private String adStatus;
    private String title;
    private String content;
    private Long budget;
    private String target;
    private Long targetImpressions;
    private String deliveryStatus;
    private Date startDate;
    private Date endDate;
}
