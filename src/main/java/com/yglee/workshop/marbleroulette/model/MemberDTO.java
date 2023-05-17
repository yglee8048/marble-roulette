package com.yglee.workshop.marbleroulette.model;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private String id;
    private String name;
    private String teamName;

    public String getId() {
        return StringUtils.isBlank(id) ? null : id;
    }

    public String getName() {
        return StringUtils.isBlank(name) ? null : name;
    }

    public String getTeamName() {
        return StringUtils.isBlank(teamName) ? null : teamName;
    }
}
