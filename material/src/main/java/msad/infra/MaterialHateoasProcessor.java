package msad.infra;

import msad.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class MaterialHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Material>> {

    @Override
    public EntityModel<Material> process(EntityModel<Material> model) {
        return model;
    }
}
