package calculate;

import java.util.Vector;

import com.github.msteinbeck.sig4j.signal.Signal1;

public class DataChanged {
	public final Signal1<Vector<String>> signal = new Signal1<>();
	public final Signal1<String> signalChanged = new Signal1<>();
	public int ID = -1;
}
