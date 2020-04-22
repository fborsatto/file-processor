package com.southsystem.fileprocessor.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface BrokerInput {

    String FILE_PROCESSOR = "fileProcessor";

    @Input(BrokerInput.FILE_PROCESSOR)
    SubscribableChannel fileProcessorQueue();
}
