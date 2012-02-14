package tder.main;

enum ServerResultType {
	RESULT_SUCCESS (R.string.appFrmAuthenticationSuccess),
	RESULT_FAIL_TIMEOUT (R.string.appFrmAuthenticationTimeout),
	RESULT_FAIL_BADSERVER (R.string.appFrmAuthenticationBadServer),
	RESULT_FAIL_BADLOGIN (R.string.appFrmAuthenticationBadLogin),
	RESULT_FAIL_BADURI (R.string.appFrmAuthenticationBadURI),
	RESULT_FAIL_GENERAL (R.string.appFrmAuthenticationBadGeneral);
	
	public final int message;
	
	ServerResultType(int message){
		this.message = message;
	}
}