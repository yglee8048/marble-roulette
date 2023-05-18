package com.yglee.workshop.marbleroulette.api;

import com.yglee.workshop.marbleroulette.model.MemberDTO;
import com.yglee.workshop.marbleroulette.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/marbles")
@RequiredArgsConstructor
public class MarbleApi {

    private final MemberService memberService;

    @GetMapping
    public List<String> getScoredList(@RequestParam(value = "excludes", required = false) List<String> excludes) {
        return memberService.getMemberRankings()
                .stream()
                .filter(memberRanking -> CollectionUtils.isEmpty(excludes) || !excludes.contains(memberRanking.getId()))
                .filter(memberRanking -> memberRanking.getTotalScore() > 0)
                .map(memberRanking ->
                        String.format("%s * %d", memberRanking.getName(), memberRanking.getTotalScore()))
                .collect(Collectors.toList());
    }

    @GetMapping("/no-score")
    public List<String> getNonScoredList() {
        return memberService.getAllMembers()
                .stream()
                .map(MemberDTO::getName)
                .collect(Collectors.toList());
    }
}
