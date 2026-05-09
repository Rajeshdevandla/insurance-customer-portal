package com.rajesh.insurance.service;

import com.rajesh.insurance.dto.PolicyDto;
import com.rajesh.insurance.model.Policy;
import com.rajesh.insurance.repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PolicyService {

    private final PolicyRepository policyRepository;
    private final PolicyMapper policyMapper;

    @Cacheable(value = "policies", key = "#customerId")
    public List<PolicyDto> getPoliciesByCustomer(String customerId) {
        return policyRepository.findByCustomerId(customerId)
                .stream()
                .map(policyMapper::toDto)
                .collect(Collectors.toList());
    }

    public PolicyDto getPolicyById(String policyId) {
        Policy policy = policyRepository.findById(policyId)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found: " + policyId));
        return policyMapper.toDto(policy);
    }

    public PolicyDto renewPolicy(String policyId) {
        Policy policy = policyRepository.findById(policyId)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found: " + policyId));
        policy.setEndDate(policy.getEndDate().plusYears(1));
        policy.setStatus(Policy.PolicyStatus.ACTIVE);
        return policyMapper.toDto(policyRepository.save(policy));
    }
}
