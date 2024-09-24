package msad.domain;

import msad.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "advertisements",
    path = "advertisements"
)
public interface AdvertisementRepository
    extends PagingAndSortingRepository<Advertisement, Long> {}
