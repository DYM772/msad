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
        Approved approved = new Approved(this);
        approved.publishAfterCommit();

        Closed closed = new Closed(this);
        closed.publishAfterCommit();
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
    public static void close(Stoped stoped) {
        //implement business logic here:

        /** Example 1:  new item 
        Advertisement advertisement = new Advertisement();
        repository().save(advertisement);

        */

        /** Example 2:  finding and process
        
        repository().findById(stoped.get???()).ifPresent(advertisement->{
            
            advertisement // do something
            repository().save(advertisement);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
