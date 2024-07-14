package com.example.market_api.security.config.auditor;


import com.example.market_api.entity.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String auditor = "";
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                if (attributes.getRequest().getHeader("X-EMAIL") != null) {
                    auditor = attributes.getRequest().getHeader("X-EMAIL");
                } else {
                    auditor = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
                }
            }
        } catch (Exception e) {
        }
        return Optional.of(auditor);
    }
}