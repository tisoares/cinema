package br.com.as.cinema.internal.configuration;

import br.com.as.cinema.external.domain.SearchCriteria;
import lombok.NonNull;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchCriteriaResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(SearchCriteria.class);
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        List<String> expands = new ArrayList<>();
        List<SearchCriteria.Filter> filters = new ArrayList<>();
        if (webRequest.getParameterMap().containsKey(CinemaConstants.EXPAND_PARAMS)) {
            expands = Arrays.stream(webRequest.getParameterMap().get(CinemaConstants.EXPAND_PARAMS))
                    .flatMap(s -> Arrays.stream(s.split(",")))
                    .toList();
        }
        if (webRequest.getParameterMap().containsKey(CinemaConstants.FILTER_PARAMS)) {
            filters = Arrays.stream(webRequest.getParameterMap().get(CinemaConstants.FILTER_PARAMS))
                    .flatMap(s -> Arrays.stream(s.split(";")))
                    .map(s -> {
                        String[] f = s.split(",");
                        if (f.length == 3) {
                            return SearchCriteria.Filter.builder()
                                    .field(f[0].trim())
                                    .operator(SearchCriteria.Filter.QueryOperator.valueOf(f[1].trim().toUpperCase()))
                                    .value(f[2].trim())
                                    .build();
                        } else {
                            return null;
                        }
                    })
                    .toList();
        }
        return SearchCriteria.builder()
                .expands(expands)
                .filters(filters)
                .build();
    }
}
