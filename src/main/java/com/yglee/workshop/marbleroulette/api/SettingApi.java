package com.yglee.workshop.marbleroulette.api;

import com.yglee.workshop.marbleroulette.service.SettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/settings")
@RequiredArgsConstructor
public class SettingApi {

    private final SettingService settingService;

    @DeleteMapping
    public void clearWinners() {
        settingService.deleteAllWinners();
    }
}
