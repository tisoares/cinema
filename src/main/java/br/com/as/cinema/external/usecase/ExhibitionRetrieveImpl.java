package br.com.as.cinema.external.usecase;

import br.com.as.cinema.internal.configuration.EntitySpecification;
import br.com.as.cinema.internal.domain.Exhibition;
import br.com.as.cinema.internal.repository.BaseRepository;
import br.com.as.cinema.internal.usecase.ExhibitionRetrieve;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnSingleCandidate(ExhibitionRetrieve.class)
public class ExhibitionRetrieveImpl extends BaseRetrieveImpl<Exhibition> implements ExhibitionRetrieve {
    public ExhibitionRetrieveImpl(BaseRepository<Exhibition> repository, EntitySpecification entitySpecification) {
        super(repository, entitySpecification);
    }
}
