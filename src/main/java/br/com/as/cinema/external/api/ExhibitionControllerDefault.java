package br.com.as.cinema.external.api;

import br.com.as.cinema.internal.api.ExhibitionController;
import br.com.as.cinema.internal.domain.Exhibition;
import br.com.as.cinema.internal.usecase.ExhibitionCreate;
import br.com.as.cinema.internal.usecase.ExhibitionRetrieve;
import br.com.as.cinema.internal.usecase.ExhibitionUpdate;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(ExhibitionController.class)
public class ExhibitionControllerDefault implements ExhibitionController {

    private final ExhibitionRetrieve exhibitionRetrieve;
    private final ExhibitionCreate exhibitionCreate;
    private final ExhibitionUpdate exhibitionUpdate;

    @Override
    public Optional<Exhibition> getByUuid(String uuid) {
        return exhibitionRetrieve.execute(uuid);
    }

    @Override
    public Page<Exhibition> getAll(Pageable pageable) {
        return exhibitionRetrieve.execute(pageable);
    }

    @Override
    public Exhibition create(Exhibition exhibition) {
        return exhibitionCreate.execute(exhibition);
    }

    @Override
    public Exhibition update(String uuid, Exhibition exhibition) {
        return exhibitionUpdate.execute(uuid, exhibition);
    }
}
