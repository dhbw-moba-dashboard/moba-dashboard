//import default react library
import React, {useContext, useEffect, useState} from "react";

import { Select } from "../../../atoms/input";
//import custom react component
import { ContentContainer } from "../../../container/content_container";

//import chart component
import {ChartComponent} from "../../../charts/Graph";

//import context
import {DataTransferContext} from "../../../../App";
import { fetchTrainInformation } from "../../../../logic/backend/fetch_train_data";
import {formatTimestampToTime} from "../../../../logic/other/time_transfer";

//create and export default train data container
export default function TrainDataContainer() {
	//get data transfer context
	const dataTransferContext = useContext(DataTransferContext);

	//define state hook for selected train data type option
	const [selectedAction, setSelectedAction] = useState<string>('Geschwindigkeit');
	//define state hook for diagram data
	const [trainData, setTrainData] = useState<any[]>([]);

	//get data to show in diagram
	useEffect(() => {
		async function createDataObject(): Promise<any[]> {
			try {
				const fetchedData = await fetchTrainInformation((dataTransferContext as any).selectedTrain);

				//set data to receuved format
				const formattedData = fetchedData.map((item: any) => ({
					name: formatTimestampToTime(item.timeStamp),
					value: item.data
				}));

				return formattedData;
			} catch (err) {
				console.error("Fehler beim Laden der Zugdaten:", err);
				return [];
			}
		}

		createDataObject().then((data) => setTrainData(data));

		//get new values every 30 seconds
		//const interval = setInterval(createDataObject, 30000);

		//return () => clearInterval(interval);
	}, [selectedAction, dataTransferContext]);

	//return created ui component
	return (
		<>
			<ContentContainer contentContainerHeaderText="Zug Daten" topHeaderSectionChildren={<TrainDataTypeSelect setSelectedAction={setSelectedAction}/>}>
				<ChartComponent data={trainData} yAxisText={selectedAction}/>
			</ContentContainer>
		</>
	);
}

//create train data type select component
function TrainDataTypeSelect({ setSelectedAction }: { setSelectedAction: (value: string) => void }) {
	return (
		<Select style={{ fontWeight: "bold", fontSize: "18px" }} selectAction={(event) => setSelectedAction(event.target.value)}>
			<option value="Geschwindigkeit">Geschwindigkeit</option>
			<optgroup label="Verbrauch">
				<option value="Wasser">Wasser</option>
				<option value="Kohle">Kohle</option>
				<option value="Sand">Sand</option>
			</optgroup>
		</Select>
	);
}
