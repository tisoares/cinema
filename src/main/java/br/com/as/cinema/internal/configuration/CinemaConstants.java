package br.com.as.cinema.internal.configuration;


public class CinemaConstants {
    public static final String APPLICATION_PROPERTY_PREFIX = "cinema";
    public static final String URL_PREFIX = "/api";
    public static final String V1 = "/v1";
    public static final String URL_PREFIX_V1 = URL_PREFIX + V1 + "/";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_SEQUENCE_NAME = "default_gen";

    public static final String PAGEABLE_PAGE = "$page";
    public static final String PAGEABLE_SIZE = "$size";
    public static final String PAGEABLE_SORT = "$sort";

    public static final String FILTER_PARAMS = "$filter";

    public static final String EXPAND_PARAMS = "$expand";
    public static final int DEFAULT_PAGEABLE_PAGE = 0;
    public static final int DEFAULT_PAGEABLE_SIZE = 50;
}
