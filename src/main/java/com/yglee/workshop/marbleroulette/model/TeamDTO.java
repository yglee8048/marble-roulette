package com.yglee.workshop.marbleroulette.model;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {
    private String name;
    private String leaderId;

    public String getName() {
        return StringUtils.isBlank(name) ? null : name;
    }

    public String getLeaderId() {
        return StringUtils.isBlank(leaderId) ? null : leaderId;
    }
}
