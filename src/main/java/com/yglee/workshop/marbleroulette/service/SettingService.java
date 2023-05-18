package com.yglee.workshop.marbleroulette.service;

import com.yglee.workshop.marbleroulette.repository.WinnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SettingService {

    private final WinnerRepository winnerRepository;

    public void deleteAllWinners() {
        winnerRepository.deleteAll();
    }
}
