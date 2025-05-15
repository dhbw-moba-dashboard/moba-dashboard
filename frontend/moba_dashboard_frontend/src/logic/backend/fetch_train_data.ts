//import custom ts function
import {setConsoleMessage} from "../tools/messages";

//define and export function to fetch train information
export async function fetchTrainInformation(trainId: number | string, dataValuesAmount: number | string = 10): Promise<any[]> {
	//try catch for error handling
	try {
		//define backend url
		const TRAIN_INFORMATION_URL: string = `${process.env.REACT_APP_BACKEND_URL}/request/speed/${trainId}?entries=${dataValuesAmount.toString()}`;

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
export async function getCurrentSpeed(trainId: string | number): Promise<any | null> {
	//try catch for error handling
	try {
		//load total train data
		const allTrainData: any[] = await fetchTrainInformation(trainId, 1);

		//check if no train data
		if (!allTrainData || allTrainData.length === 0) return null;

		//return current speed
		return allTrainData[0];
	} catch (getTrainSpeedError: any) {
		setConsoleMessage(getTrainSpeedError.message, true);
		return null;
	}
}