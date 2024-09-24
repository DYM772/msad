package msad.domain;

import msad.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "materials", path = "materials")
public interface MaterialRepository
    extends PagingAndSortingRepository<Material, Long> {}
