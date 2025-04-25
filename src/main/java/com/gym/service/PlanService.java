package com.gym.service;

import com.gym.domain.Plan;
import com.gym.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    public Plan getPlanPorId(Long id) {
        Optional<Plan> optionalPlan = planRepository.findById(id);
        return optionalPlan.orElse(null);
    }

    public Iterable<Plan> findAll() {
        return planRepository.findAll();
    }
}
