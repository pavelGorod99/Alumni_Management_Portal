package com.example.Alumni_Management_Portal.services;

import com.example.Alumni_Management_Portal.dto.InsightDto;

import java.util.List;

public interface InsightService {

    public void create(InsightDto insightDto);

    public InsightDto getById (Integer insightId);

    public List<InsightDto> getAll();
}

