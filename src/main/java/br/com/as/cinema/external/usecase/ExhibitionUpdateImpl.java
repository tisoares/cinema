package br.com.as.cinema.external.usecase;

import br.com.as.cinema.internal.domain.Exhibition;
import br.com.as.cinema.internal.repository.BaseRepository;
import br.com.as.cinema.internal.usecase.ExhibitionUpdate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnSingleCandidate(ExhibitionUpdate.class)
public class ExhibitionUpdateImpl extends BaseUpdateImpl<Exhibition> implements ExhibitionUpdate {
    public ExhibitionUpdateImpl(BaseRepository<Exhibition> repository) {
        super(repository);
    }

    @Override
    protected Exhibition loadDependencies(Exhibition request, Exhibition saved) {
        return request;
    }
}
