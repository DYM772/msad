package msad.infra;

import java.util.List;
import msad.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "adStatuses",
    path = "adStatuses"
)
public interface AdStatusRepository
    extends PagingAndSortingRepository<AdStatus, Long> {
    List<AdStatus> findByAdId(Long adId);
}
