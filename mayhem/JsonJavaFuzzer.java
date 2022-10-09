import com.code_intelligence.jazzer.api.FuzzedDataProvider;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonJavaFuzzer {

	public static void fuzzerTestOneInput(FuzzedDataProvider data) {
		String input = data.consumeRemainingAsString();
		String jsonStr;
		try {
			JSONObject jsonObject = new JSONObject(input);
			jsonStr = jsonObject.toString();
		} catch (JSONException ignored) {

		}

	}
}
