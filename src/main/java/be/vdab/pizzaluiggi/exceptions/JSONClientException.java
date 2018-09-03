package be.vdab.pizzaluiggi.exceptions;

public class JSONClientException extends RuntimeException{
	
		private static final long serialVersionUID = 1L;
		
		public JSONClientException() {
			super();
		}
		
		public JSONClientException(String message) {
			super(message);
		}
	
}
