package br.com.as.cinema.internal.configuration;

import br.com.as.cinema.external.domain.SearchCriteria;
import br.com.as.cinema.internal.domain.BaseEntity;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class EntitySpecification<T extends BaseEntity> {

    public Specification<T> specificationBuilder(SearchCriteria searchCriteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(searchCriteria) && CollectionUtils.isNotEmpty(searchCriteria.getExpands())) {
                searchCriteria.getExpands().forEach(root::fetch);
            }


            if (Objects.nonNull(searchCriteria) && CollectionUtils.isNotEmpty(searchCriteria.getFilters())) {
                List<SearchCriteria.Filter> filters = searchCriteria.getFilters();
                predicates = filters.stream()
                        .map(this::createSpecification)
                        .map(specificationBuilder -> specificationBuilder.toPredicate(root, query, criteriaBuilder))
                        .toList();
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<T> createSpecification(SearchCriteria.Filter filter) {
        return (root, query, criteriaBuilder) -> {
            From from = root;
            String field = filter.getField();
            String[] dep = filter.getField().split("\\.");
            if (dep.length > 1) {
                for (int i = 0; i < dep.length - 1; i++) {
                    from = root.join(dep[i].trim());
                }
                field = dep[dep.length - 1];
            }

            return switch (filter.getOperator()) {
                case EQUALS -> criteriaBuilder.equal(from.get(field), filter.getValue());
                case NOT_EQUALS -> criteriaBuilder.notEqual(from.get(field), filter.getValue());
                case GREATER_THAN -> criteriaBuilder.greaterThanOrEqualTo(from.get(field), filter.getValue());
                case LESS_THAN -> criteriaBuilder.lessThanOrEqualTo(from.get(field), filter.getValue());
                case LIKE -> criteriaBuilder.like(from.get(field), "%" + filter.getValue() + "%");
            };
        };
    }

}