package com.rajesh.insurance;

import com.rajesh.insurance.model.Policy;
import com.rajesh.insurance.repository.PolicyRepository;
import com.rajesh.insurance.service.PolicyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PolicyServiceTest {

    @Mock private PolicyRepository policyRepository;
    @InjectMocks private PolicyService policyService;

    @Test
    void shouldReturnPoliciesByCustomer() {
        Policy p = new Policy();
        p.setCustomerId("CUST-001");
        p.setPremium(BigDecimal.valueOf(1200));
        p.setStatus(Policy.PolicyStatus.ACTIVE);
        p.setType(Policy.PolicyType.HEALTH);
        p.setStartDate(LocalDate.now());
        p.setEndDate(LocalDate.now().plusYears(1));

        when(policyRepository.findByCustomerId("CUST-001")).thenReturn(List.of(p));

        var result = policyService.getPoliciesByCustomer("CUST-001");
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getStatus()).isEqualTo("ACTIVE");
    }
}
