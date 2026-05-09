package com.rajesh.insurance.controller;

import com.rajesh.insurance.dto.ClaimDto;
import com.rajesh.insurance.dto.ClaimRequestDto;
import com.rajesh.insurance.service.ClaimsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
@RequiredArgsConstructor
public class ClaimsController {

    private final ClaimsService claimsService;

    @GetMapping
    public ResponseEntity<List<ClaimDto>> getMyClaims(@AuthenticationPrincipal String customerId) {
        return ResponseEntity.ok(claimsService.getClaimsByCustomer(customerId));
    }

    @PostMapping
    public ResponseEntity<ClaimDto> submitClaim(@RequestBody ClaimRequestDto request,
                                                 @AuthenticationPrincipal String customerId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(claimsService.submitClaim(customerId, request));
    }

    @GetMapping("/{claimId}/status")
    public ResponseEntity<String> getClaimStatus(@PathVariable String claimId) {
        return ResponseEntity.ok(claimsService.getClaimStatus(claimId));
    }
}
