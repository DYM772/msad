package msad.infra;

import msad.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class AdvertisementHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Advertisement>> {

    @Override
    public EntityModel<Advertisement> process(
        EntityModel<Advertisement> model
    ) {
        return model;
    }
}
