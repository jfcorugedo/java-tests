package es.juan.io;

/**
 * This exception will be thrown whenever a stream tries to read more characters than it's allowed.
 * 
 * This error prevents DoS attacks as well as out of memory errors
 * 
 * @author jfcorugedo
 *
 */
public class SizeLimitExceededException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SizeLimitExceededException() {
		super();
	}

	public SizeLimitExceededException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SizeLimitExceededException(String message, Throwable cause) {
		super(message, cause);
	}

	public SizeLimitExceededException(String message) {
		super(message);
	}

	public SizeLimitExceededException(Throwable cause) {
		super(cause);
	}

}
