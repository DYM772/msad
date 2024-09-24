package msad.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import msad.AdvertisementApplication;
import msad.domain.Approved;
import msad.domain.Closed;
import msad.domain.Submitted;

@Entity
@Table(name = "Advertisement_table")
@Data
//<<< DDD / Aggregate Root
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long materialId;

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

    @PostPersist
    public void onPostPersist() {
        Submitted submitted = new Submitted(this);
        submitted.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
    }

    public static AdvertisementRepository repository() {
        AdvertisementRepository advertisementRepository = AdvertisementApplication.applicationContext.getBean(
            AdvertisementRepository.class
        );
        return advertisementRepository;
    }

    public void submit() {
        //implement business logic here:

    }

    public void approve() {
        //implement business logic here:

    }

    //<<< Clean Arch / Port Method
    public static void close(Stopped stopped) throws Exception {
        Advertisement advertisement = repository().findById(stopped.getAdId()).orElseThrow(() -> new Exception());
        advertisement.setStatus("CLOSED");
        Closed closed = new Closed(advertisement);
        closed.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
