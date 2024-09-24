package msad.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import msad.InventoryApplication;
import msad.domain.Canceled;

@Entity
@Table(name = "Inventory_table")
@Data
//<<< DDD / Aggregate Root
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String target;

    private Long inventory;

    @PostPersist
    public void onPostPersist() {}

    @PostUpdate
    public void onPostUpdate() {}

    public static InventoryRepository repository() {
        InventoryRepository inventoryRepository = InventoryApplication.applicationContext.getBean(
            InventoryRepository.class
        );
        return inventoryRepository;
    }

    public void register() {
        //implement business logic here:

    }

    //<<< Clean Arch / Port Method
    public static void subtract(Fed fed) {
        Inventory inventory = repository().findByTarget(fed.getTarget());
        Long targetImpressions = fed.getTargetImpressions();
        Long availableInventory = inventory.getInventory();

        if(targetImpressions > availableInventory) {
            Canceled canceled = new Canceled(inventory);
            canceled.setAdId(fed.getAdId());
            canceled.publishAfterCommit();
        } else{
            inventory.setInventory(availableInventory - targetImpressions);
        }
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
