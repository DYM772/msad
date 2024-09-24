package msad.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import msad.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/advertisements")
@Transactional
public class AdvertisementController {

    @Autowired
    AdvertisementRepository advertisementRepository;

    @PatchMapping("/advertisements/approval/{adId}")
    public void approve(@PathVariable Long adId) throws Exception {
        Advertisement advertisement = advertisementRepository.findById(adId).orElseThrow(() -> new Exception());
        advertisement.setStatus("RUNNING");
        Approved approved = new Approved(advertisement);
        approved.publishAfterCommit();
    }
}
//>>> Clean Arch / Inbound Adaptor
