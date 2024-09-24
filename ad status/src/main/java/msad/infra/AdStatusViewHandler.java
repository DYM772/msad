package msad.infra;

import msad.domain.*;
import msad.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AdStatusViewHandler {

//<<< DDD / CQRS
    @Autowired
    private AdStatusRepository adStatusRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenSubmitted_then_CREATE_1 (@Payload Submitted submitted) {
        try {

            if (!submitted.validate()) return;

            // view 객체 생성
            AdStatus adStatus = new AdStatus();
            // view 객체에 이벤트의 Value 를 set 함
            adStatus.setMaterialId(submitted.getMaterialId());
            adStatus.setMaterialName(submitted.getMaterialName());
            adStatus.setMaterialUrl(submitted.getMaterialUrl());
            adStatus.setAdId(submitted.getId());
            adStatus.setAdStatus(submitted.getStatus());
            adStatus.setTitle(submitted.getTitle());
            adStatus.setContent(submitted.getContent());
            adStatus.setBudget(submitted.getBudget());
            adStatus.setTarget(submitted.getTarget());
            adStatus.setTargetImpressions(submitted.getTargetImpressions());
            adStatus.setStartDate(submitted.getStartDate());
            adStatus.setEndDate(submitted.getEndDate());
            // view 레파지 토리에 save
            adStatusRepository.save(adStatus);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenApproved_then_UPDATE_1(@Payload Approved approved) {
        try {
            if (!approved.validate()) return;
                // view 객체 조회

                List<AdStatus> adStatusList = adStatusRepository.findByAdId(approved.getId());
                for(AdStatus adStatus : adStatusList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    adStatus.setDeliveryStatus(approved.getStatus());
                // view 레파지 토리에 save
                adStatusRepository.save(adStatus);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenFed_then_UPDATE_2(@Payload Fed fed) {
        try {
            if (!fed.validate()) return;
                // view 객체 조회

                List<AdStatus> adStatusList = adStatusRepository.findByAdId(fed.getAdId());
                for(AdStatus adStatus : adStatusList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    adStatus.setDeliveryStatus(fed.getStatus());
                // view 레파지 토리에 save
                adStatusRepository.save(adStatus);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenStoped_then_UPDATE_3(@Payload Stoped stoped) {
        try {
            if (!stoped.validate()) return;
                // view 객체 조회

                List<AdStatus> adStatusList = adStatusRepository.findByAdId(stoped.getAdId());
                for(AdStatus adStatus : adStatusList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    adStatus.setDeliveryStatus(stoped.getStatus());
                // view 레파지 토리에 save
                adStatusRepository.save(adStatus);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenClosed_then_UPDATE_4(@Payload Closed closed) {
        try {
            if (!closed.validate()) return;
                // view 객체 조회

                List<AdStatus> adStatusList = adStatusRepository.findByAdId(closed.getId());
                for(AdStatus adStatus : adStatusList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    adStatus.setAdStatus(closed.getStatus());
                // view 레파지 토리에 save
                adStatusRepository.save(adStatus);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


//>>> DDD / CQRS
}

