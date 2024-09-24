package msad.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import msad.MaterialApplication;

@Entity
@Table(name = "Material_table")
@Data
//<<< DDD / Aggregate Root
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String type;

    private Long size;

    private String url;

    @PostPersist
    public void onPostPersist() {}

    public static MaterialRepository repository() {
        MaterialRepository materialRepository = MaterialApplication.applicationContext.getBean(
            MaterialRepository.class
        );
        return materialRepository;
    }

    public void upload() {
        //implement business logic here:

    }
}
//>>> DDD / Aggregate Root
