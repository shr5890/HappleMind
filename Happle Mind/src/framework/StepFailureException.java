package framework;

public class StepFailureException extends Error {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public StepFailureException () {

	    }

	    public StepFailureException (String message) {
	        super (message);
	    }

	    public StepFailureException (Throwable cause) {
	        super (cause);
	    }

	    public StepFailureException (String message, Throwable cause) {
	        super (message, cause);
	    }
	}