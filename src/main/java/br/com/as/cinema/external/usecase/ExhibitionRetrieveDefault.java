package br.com.as.cinema.external.usecase;

import br.com.as.cinema.internal.domain.Exhibition;
import br.com.as.cinema.internal.repository.BaseRepository;
import br.com.as.cinema.internal.usecase.ExhibitionRetrieve;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnSingleCandidate(ExhibitionRetrieve.class)
public class ExhibitionRetrieveDefault extends BaseRetrieveDefault<Exhibition> implements ExhibitionRetrieve {
    public ExhibitionRetrieveDefault(BaseRepository<Exhibition> repository) {
        super(repository);
    }
}
