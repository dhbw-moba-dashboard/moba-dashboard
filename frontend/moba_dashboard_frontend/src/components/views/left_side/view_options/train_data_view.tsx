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

//testing
const data = [
	{ name: "15 min", value: 30 },
	{ name: "30 min", value: 45 },
	{ name: "45 min", value: 15 },
	{ name: "60 min", value: 10 },
	{ name: "90 min", value: 10 },
	{name: "120 min", value: 100}
];

//create and export default train data container
export default function TrainDataContainer() {
	//get data transfer context
	const dataTransferContext = useContext(DataTransferContext);

	//define state hook for selected train data type option
	const [selectedAction, setSelectedAction] = useState<string>('Geschwindigkeit');
	//define state hook for diagram data
	const [trainData, setTrainData] = useState<any[]>(data);

	//get data to show in diagram
	useEffect(() => {
		//function to create data object
		async function createDataObject(): Promise<any | []> {
			const fetchedDataObject = await fetchTrainInformation((dataTransferContext as any).selectedTrain);

			//create structure for data
			
		}
		setTrainData(await createDataObject());
	}, [selectedAction]);

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
