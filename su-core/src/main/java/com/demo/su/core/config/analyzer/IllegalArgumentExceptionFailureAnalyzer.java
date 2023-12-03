package com.demo.su.core.config.analyzer;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IllegalArgumentExceptionFailureAnalyzer extends AbstractFailureAnalyzer<IllegalArgumentException> {

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, IllegalArgumentException cause) {
        return new FailureAnalysis(cause.getMessage(), "请检查输入参数！", cause);
    }
}
