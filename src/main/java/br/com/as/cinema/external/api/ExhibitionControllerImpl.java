package br.com.as.cinema.external.api;

import br.com.as.cinema.external.domain.ExhibitionRequest;
import br.com.as.cinema.external.domain.SearchCriteria;
import br.com.as.cinema.internal.api.ExhibitionController;
import br.com.as.cinema.internal.domain.Exhibition;
import br.com.as.cinema.internal.usecase.ExhibitionCreate;
import br.com.as.cinema.internal.usecase.ExhibitionRetrieve;
import br.com.as.cinema.internal.usecase.ExhibitionUpdate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(ExhibitionController.class)
public class ExhibitionControllerImpl implements ExhibitionController {

    private final ExhibitionRetrieve exhibitionRetrieve;
    private final ExhibitionCreate exhibitionCreate;
    private final ExhibitionUpdate exhibitionUpdate;

    @Override
    public Optional<Exhibition> getByUuid(String uuid) {
        return exhibitionRetrieve.execute(uuid);
    }

    @Override
    public Page<Exhibition> getAll(Pageable pageable, SearchCriteria searchCriteria) {
        return exhibitionRetrieve.execute(pageable, searchCriteria);
    }

    @Override
    public Exhibition create(ExhibitionRequest exhibitionRequest) {
        return exhibitionCreate.execute(exhibitionRequest);
    }

    @Override
    public Exhibition update(String uuid, Exhibition exhibition) {
        return exhibitionUpdate.execute(uuid, exhibition);
    }
}
