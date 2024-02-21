package br.com.as.cinema.internal.configuration;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class PageableResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Pageable.class);
    }

    @Override
    public Pageable resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer mavContainer,
                                    NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Map<String, String> requestParameters = webRequest.getParameterMap()
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue()[0]));
        int page = Integer.parseInt(requestParameters.getOrDefault(CinemaConstants.PAGEABLE_PAGE,
                String.valueOf(CinemaConstants.DEFAULT_PAGEABLE_PAGE)));
        int size = Integer.parseInt(requestParameters.getOrDefault(CinemaConstants.PAGEABLE_SIZE,
                String.valueOf(CinemaConstants.DEFAULT_PAGEABLE_SIZE)));
        if (webRequest.getParameterMap().containsKey(CinemaConstants.PAGEABLE_SORT)) {
            return PageRequest.of(page, size, convertSort(webRequest.getParameterMap().get(CinemaConstants.PAGEABLE_SORT)));
        } else {
            return PageRequest.of(page, size);
        }
    }

    private Sort convertSort(String[] values) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String value : values) {
            String[] o = value.split(" ");
            if (o.length > 1) {
                orders.add(new Sort.Order(Sort.Direction.fromString(o[1]), o[0]));
            } else {
                orders.add(new Sort.Order(Sort.Direction.ASC, o[0]));
            }
        }
        return Sort.by(orders);
    }
}
