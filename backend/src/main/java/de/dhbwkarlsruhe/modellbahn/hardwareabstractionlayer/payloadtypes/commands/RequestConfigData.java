package de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes.commands;

import com.google.gson.Gson;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes.Payload;

public class RequestConfigData implements Payload {
    private String fileName;
    @Override
    public byte[] toByteArray() {
        return fileName.getBytes();
    }
    @Override
	public String toString() {
		return new Gson().toJson(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RequestConfigData that = (RequestConfigData) o;
    return that.fileName.equals(fileName);
	}
}
