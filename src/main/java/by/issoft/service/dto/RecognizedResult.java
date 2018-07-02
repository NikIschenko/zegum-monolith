package by.issoft.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecognizedResult {
    @JsonProperty("processedImageUrl")
    private String processedImageUrl;
    @JsonProperty("smileCount")
    private int smileCount;

    public String getProcessedImageUrl() {
        return processedImageUrl;
    }

    public void setProcessedImageUrl(String processedImageUrl) {
        this.processedImageUrl = processedImageUrl;
    }

    public int getSmileCount() {
        return smileCount;
    }

    public void setSmileCount(int smileCount) {
        this.smileCount = smileCount;
    }
}
