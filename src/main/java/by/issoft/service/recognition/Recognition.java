package by.issoft.service.recognition;

import feign.Param;

public interface Recognition {
	void uploadPhoto(@Param("file") byte[] file);
	RecognizedResult recognitionResult();
}