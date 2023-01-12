package com.kalavit.javulna.services;

import com.code_intelligence.jazzer.api.FuzzedDataProvider;

public class ExampleFuzzer {
    public static void fuzzerTestOneInput(FuzzedDataProvider data) {
        int number = data.consumeInt();
        String string = data.consumeRemainingAsString();
        
        changePassword(string);
}