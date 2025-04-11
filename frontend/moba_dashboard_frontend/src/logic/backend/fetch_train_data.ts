//import custom ts function
import {setConsoleMessage} from "../tools/messages";

//define and export function to fetch train information
export async function fetchTrainInformation(trainId: string | number): Promise<any[]> {
	//try catch for error handling
	try {
		//define backend url
		const TRAIN_INFORMATION_URL: string = `http://localhost:8080/numberrequest/speed/${trainId}?entries=5`;

		//make backend call to load data
		const backendResponse: Response = await fetch(TRAIN_INFORMATION_URL, {
			method: "GET",
			headers: {
				"Content-Type": "application/json",
			},
		});
		//check if response is not ok to set error message
		if (!backendResponse.ok) {
			//set error message
			throw new Error("error fetching train information");
		}
		//return received information data
		return backendResponse.json();
	} catch (getTrainInformationError: any) {
		setConsoleMessage(getTrainInformationError.message, true);
		return [];
	}
}

//define and export async funtion to get the data value with the latest timestamp
export async function getCurrentSpeed(trainId: string | number): Promise<any[]> {
	//try catch for error handling
	try {
		//load total train data
		const allTrainData: any[] = await fetchTrainInformation(trainId);

		//check if no train data
		if (!allTrainData || allTrainData.length === 0) return [];

		//sort and get latest entry
		const latestEntry = allTrainData.sort((a, b) =>
			new Date(b.timestamp).getTime() - new Date(a.timestamp).getTime()
		)[0];

		return latestEntry;
	} catch (getTrainSpeedError: any) {
		setConsoleMessage(getTrainSpeedError.message, true);
		return [];
	}
}