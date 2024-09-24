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
    AdvertisementRepository advertisementRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Stoped'"
    )
    public void wheneverStoped_Close(@Payload Stoped stoped) throws Exception {
        Stoped event = stoped;
        System.out.println("\n\n##### listener Close : " + stoped + "\n\n");

        // Sample Logic //
        Advertisement.close(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
