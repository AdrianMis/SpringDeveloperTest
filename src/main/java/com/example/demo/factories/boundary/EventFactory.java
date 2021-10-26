package com.example.demo.factories.boundary;

import com.example.demo.dto.EventDTO;
import com.example.demo.model.Event;

public interface EventFactory {

    Event detectEvent(EventDTO eventDTO);
}
