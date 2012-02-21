package tder.main;


/*
	HOME (onHome),
	AWAY (onAway),
	UNKNOWN (onUnknown);

	// Actions to perform when entering/leaving home
	private static TimerTask onHome = new TimerTask() {
		public void run() {
			System.out.println("GPSLOCATIONLISTENER: At home, waking computers.");
		}
	};
	private static TimerTask onAway = new TimerTask() {
		public void run() {
			System.out.println("GPSLOCATIONLISTENER: Leaving home, powering down computers.");
		}
	};
	private static TimerTask onUnknown = new TimerTask() {
		public void run() {
			
		}
	};
	*/

public enum AreaType {
	HOME ("Home"),
	AWAY ("Away"),
	UNKNOWN ("Unknown");
	
	String display;
	
	private AreaType(String display) {
		this.display = display;
	}
}
