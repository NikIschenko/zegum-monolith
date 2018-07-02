package by.issoft.service;

import by.issoft.service.dto.RecognizedResult;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface RecognitionEndpoints {
	@RequestLine("POST /api/recognize/monolith")
	@Headers("Content-Type: multipart/form-data")
	RecognizedResult uploadPhoto(final @Param("file") byte[] file);
}
