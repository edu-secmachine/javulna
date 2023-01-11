import com.code_intelligence.jazzer.api.FuzzedDataProvider;

public class ExampleFuzzer {
    public static void fuzzerTestOneInput(FuzzedDataProvider data) {
        int number = data.consumeInt();
        String string = data.consumeRemainingAsString();
        
        // The function for fuzzing the changePassword function (just a test: should try on more functions and longer code bases...)
        public static void fuzzerTestOneInput(FuzzedDataProvider data) {

            int number = data.consumeInt();
            String string = data.consumeRemainingAsString();

            changePassword(string);
    }
}