package br.com.as.cinema.external.usecase;

import br.com.as.cinema.internal.domain.Exhibition;
import br.com.as.cinema.internal.usecase.ExhibitionCreate;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@ConditionalOnSingleCandidate(ExhibitionCreate.class)
public class ExhibitionCreateDefault implements ExhibitionCreate {

    private final ExhibitionCreate exhibitionCreate;

    @Override
    public Exhibition execute(Exhibition exhibition) {
        return exhibitionCreate.execute(exhibition);
    }
}
