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
    DeliveryRepository deliveryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Approved'"
    )
    public void wheneverApproved_Feed(@Payload Approved approved) {
        Approved event = approved;
        System.out.println("\n\n##### listener Feed : " + approved + "\n\n");

        // Sample Logic //
        Delivery.feed(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Canceled'"
    )
    public void wheneverCanceled_Stop(@Payload Canceled canceled) {
        Canceled event = canceled;
        System.out.println("\n\n##### listener Stop : " + canceled + "\n\n");

        // Sample Logic //
        Delivery.stop(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
