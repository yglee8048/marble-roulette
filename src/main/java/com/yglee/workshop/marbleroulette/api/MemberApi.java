package com.yglee.workshop.marbleroulette.api;

import com.yglee.workshop.marbleroulette.model.MemberDTO;
import com.yglee.workshop.marbleroulette.model.MemberRanking;
import com.yglee.workshop.marbleroulette.model.OptionDTO;
import com.yglee.workshop.marbleroulette.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberApi {

    private final MemberService memberService;

    @GetMapping
    public List<MemberDTO> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/ranking")
    public List<MemberRanking> getMemberRankings() {
        return memberService.getMemberRankings();
    }

    @GetMapping("/options")
    public List<OptionDTO> getMemberOptions() {
        return memberService.getMemberOptions();
    }

    @PutMapping
    public void updateMember(@RequestBody MemberDTO memberDTO) {
        memberService.updateMember(memberDTO);
    }

    @PostMapping
    public void createMember(@RequestBody MemberDTO memberDTO) {
        memberService.createMember(memberDTO);
    }

    @DeleteMapping("/{memberId}")
    public void deleteMember(@PathVariable("memberId") String memberId) {
        memberService.deleteMember(memberId);
    }
}
