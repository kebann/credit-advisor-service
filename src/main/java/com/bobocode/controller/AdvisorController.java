package com.bobocode.controller;

import com.bobocode.dto.ApplicationDto;
import com.bobocode.service.AdvisorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/advisors")
@RequiredArgsConstructor
public class AdvisorController {

    private final AdvisorService advisorService;

    @PostMapping("/{advisorId}/applications")
    public ResponseEntity<?> assignApplication(@PathVariable Long advisorId) {
        var application = advisorService.assignApplication(advisorId);

        return ResponseEntity.ok(new ApplicationDto(application.getId()));
    }
}
