package by.issoft.service.recognition;

import feign.Param;

public interface Recognition {
	void uploadPhoto(final @Param("file") byte[] file);
	RecognizedResult recognitionResult();
}