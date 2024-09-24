package msad.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import msad.DeliveryApplication;
import msad.domain.Fed;
import msad.domain.Stoped;

@Entity
@Table(name = "Delivery_table")
@Data
//<<< DDD / Aggregate Root
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long adId;

    private Long materialId;

    private String materialName;

    private String materialUrl;

    private String target;

    private Long targetImpressions;

    private Date startDate;

    private Date endDate;

    private String status;

    @PostPersist
    public void onPostPersist() {
        Fed fed = new Fed(this);
        fed.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        Stoped stoped = new Stoped(this);
        stoped.setStatus("STOP");
        stoped.publishAfterCommit();
    }

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    //<<< Clean Arch / Port Method
    public static void feed(Approved approved) {
        //implement business logic here:

        Delivery delivery = new Delivery();
        delivery.setAdId(approved.getId());
        delivery.setMaterialId(approved.getMaterialId());
        delivery.setMaterialName(approved.getMaterialName());
        delivery.setMaterialUrl(approved.getMaterialUrl());
        delivery.setTarget(approved.getTarget());
        delivery.setTargetImpressions(approved.getTargetImpressions());
        delivery.setStartDate(approved.getStartDate());
        delivery.setEndDate(approved.getEndDate());
        delivery.setStatus("ACTIVE");
        repository().save(delivery);
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void stop(Canceled canceled) {
        Delivery delivery = repository().findByAdId(canceled.getAdId());
        delivery.setStatus("STOP");
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
