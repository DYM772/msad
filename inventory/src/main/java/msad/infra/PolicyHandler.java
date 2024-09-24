package msad.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import msad.config.kafka.KafkaProcessor;
import msad.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    InventoryRepository inventoryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Fed'"
    )
    public void wheneverFed_Subtract(@Payload Fed fed) {
        Fed event = fed;
        System.out.println("\n\n##### listener Subtract : " + fed + "\n\n");

        // Sample Logic //
        Inventory.subtract(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
