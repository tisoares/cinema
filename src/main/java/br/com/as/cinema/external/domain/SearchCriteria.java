package br.com.as.cinema.external.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(toBuilder = true)
public class SearchCriteria {

    private List<Filter> filters;
    private List<String> expands;

    @Getter
    @Builder(toBuilder = true)
    public static class Filter {
        // Name of the operation we like to perform
        public enum QueryOperator {
            EQUALS, NOT_EQUALS, LIKE, LESS_THAN, GREATER_THAN
        }

        private String field; // Name of the filed from entity like firstName
        private QueryOperator operator; // Operator we like to apply
        private String value; // value we would like to match
    }

}