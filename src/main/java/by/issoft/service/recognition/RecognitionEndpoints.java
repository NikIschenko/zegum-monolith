package by.issoft.service.recognition;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface RecognitionEndpoints {
	@RequestLine("POST /api/recognize/monolith")
	@Headers("Content-Type: multipart/form-data")
	RecognizedResult uploadPhoto(@Param("file") byte[] file);
}
