package com.rajesh.insurance.controller;

import com.rajesh.insurance.dto.PolicyDto;
import com.rajesh.insurance.service.PolicyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
@RequiredArgsConstructor
@Tag(name = "Policy Management")
public class PolicyController {

    private final PolicyService policyService;

    @GetMapping
    @Operation(summary = "Get all policies for authenticated customer")
    public ResponseEntity<List<PolicyDto>> getMyPolicies(@AuthenticationPrincipal String customerId) {
        return ResponseEntity.ok(policyService.getPoliciesByCustomer(customerId));
    }

    @GetMapping("/{policyId}")
    @Operation(summary = "Get policy details")
    public ResponseEntity<PolicyDto> getPolicy(@PathVariable String policyId) {
        return ResponseEntity.ok(policyService.getPolicyById(policyId));
    }

    @PostMapping("/{policyId}/renew")
    @Operation(summary = "Renew a policy")
    public ResponseEntity<PolicyDto> renewPolicy(@PathVariable String policyId) {
        return ResponseEntity.ok(policyService.renewPolicy(policyId));
    }
}
