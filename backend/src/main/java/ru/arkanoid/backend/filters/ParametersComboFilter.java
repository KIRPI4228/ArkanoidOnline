package ru.arkanoid.backend.filters;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

public abstract class ParametersComboFilter<T> implements ParameterFilter<T> {
    private List<ParameterFilter<T>> filters = new ArrayList<>();

    @Override
    public String doFilter(T parameter) {
        String error;
        for (ParameterFilter filter : filters) {
            error = filter.doFilter(parameter);
            if (error != null) {
                return error;
            }
        }

        return null;
    }

    @PostConstruct
    private void init() {
        applyFilters(filters);
    }

    protected abstract void applyFilters(List<ParameterFilter<T>> filters);
}
