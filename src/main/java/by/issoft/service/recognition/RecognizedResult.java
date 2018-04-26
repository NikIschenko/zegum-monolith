package by.issoft.service.recognition;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecognizedResult {
    @JsonProperty("processedImageUrl")
    private String processedImageUrl;
    @JsonProperty("smileCount")
    private int smileCount;

    public String processedImageUrl() {
        return processedImageUrl;
    }
    public int smileCount() {
        return this.smileCount;
    }
}
