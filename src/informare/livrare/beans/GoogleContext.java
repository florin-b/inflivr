package informare.livrare.beans;

import java.util.concurrent.TimeUnit;

import com.google.maps.GeoApiContext;

import informare.livrare.utils.Constants;

public class GoogleContext {

	private static GeoApiContext instance0;

	private static GeoApiContext instance1;
	private static GeoApiContext instance2;
	private static GeoApiContext instance3;
	private static GeoApiContext instance4;
	private static GeoApiContext instance5;
	
	private static GeoApiContext instanceKey;

	private GoogleContext() {

	}

	public static GeoApiContext getContext(int contextNumber) {

		

		switch (contextNumber) {
		case 1:
			return getContext1();
		case 2:
			return getContext2();
		case 3:
			return getContext3();
		case 4:
			return getContext4();
		case 5:
			return getContext5();
		default:
			return getContext0();
		}
	}

	public static GeoApiContext getContextKey() {
		if (instanceKey == null) {
			instanceKey = new GeoApiContext().setApiKey(Constants.GOOGLE_PAYED_KEY);
			instanceKey.setQueryRateLimit(2);
			instanceKey.setRetryTimeout(0, TimeUnit.SECONDS);
			instanceKey.setConnectTimeout(1, TimeUnit.SECONDS);

		}

		return instanceKey;
	}
	
	public static GeoApiContext getContext0() {

		if (instance0 == null) {
			instance0 = new GeoApiContext().setApiKey(Constants.GOOGLE_MAPS_API_KEY);
			instance0.setQueryRateLimit(2);
			instance0.setRetryTimeout(0, TimeUnit.SECONDS);
			instance0.setConnectTimeout(1, TimeUnit.SECONDS);

		}

		return instance0;
	}

	public static GeoApiContext getContext1() {

		if (instance1 == null) {
			instance1 = new GeoApiContext().setApiKey(Constants.GOOGLE_MAPS_API_KEY_01);
			instance1.setQueryRateLimit(2);
			instance1.setRetryTimeout(0, TimeUnit.SECONDS);
			instance1.setConnectTimeout(1, TimeUnit.SECONDS);

		}

		return instance1;
	}

	public static GeoApiContext getContext2() {

		if (instance2 == null) {
			instance2 = new GeoApiContext().setApiKey(Constants.GOOGLE_MAPS_API_KEY_02);
			instance2.setQueryRateLimit(2);
			instance2.setRetryTimeout(0, TimeUnit.SECONDS);
			instance2.setConnectTimeout(1, TimeUnit.SECONDS);

		}

		return instance2;
	}

	public static GeoApiContext getContext3() {

		if (instance3 == null) {
			instance3 = new GeoApiContext().setApiKey(Constants.GOOGLE_MAPS_API_KEY_03);
			instance3.setQueryRateLimit(2);
			instance3.setRetryTimeout(0, TimeUnit.SECONDS);
			instance3.setConnectTimeout(1, TimeUnit.SECONDS);

		}

		return instance3;
	}

	public static GeoApiContext getContext4() {

		if (instance4 == null) {
			instance4 = new GeoApiContext().setApiKey(Constants.GOOGLE_MAPS_API_KEY_04);
			instance4.setQueryRateLimit(2);
			instance4.setRetryTimeout(0, TimeUnit.SECONDS);
			instance4.setConnectTimeout(1, TimeUnit.SECONDS);

		}

		return instance4;
	}

	public static GeoApiContext getContext5() {

		if (instance5 == null) {
			instance5 = new GeoApiContext().setApiKey(Constants.GOOGLE_MAPS_API_KEY_05);
			instance5.setQueryRateLimit(2);
			instance5.setRetryTimeout(0, TimeUnit.SECONDS);
			instance5.setConnectTimeout(1, TimeUnit.SECONDS);

		}

		return instance5;
	}

}
