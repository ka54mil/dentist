package com.example.dentist.services;

import com.example.dentist.models.ScheduledTreatment;

public interface ScheduledTreatmentService {

    ScheduledTreatment getById(Long id);

    ScheduledTreatment save(ScheduledTreatment scheduledTreatment);
}
