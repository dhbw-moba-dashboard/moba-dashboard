//import default react library
import React, {useContext, useEffect, useState} from "react";

import { Select } from "../../../atoms/input";
//import custom react component
import { ContentContainer } from "../../../container/content_container";
import Image from "../../../atoms/images";
import Text from "../../../atoms/texts";

//import chart component
import {ChartComponent} from "../../../charts/Graph";

//import custom ts functions
import { fetchTrainInformation } from "../../../../logic/backend/fetch_train_data";
//import external ts function
import {format} from 'date-fns';

//import context
import {DataTransferContext} from "../../../../App";
import FlexBox from "../../../container/FlexBox";

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
				const fetchedData = (await fetchTrainInformation((dataTransferContext as any).selectedTrain)).reverse();

				//set data to receuved format
				const formattedData = fetchedData.map((item: any) => ({
					name: format(new Date(item.timeStamp * 1000), 'HH:mm:ss'),
					value: item.data
				}));

				return formattedData;
			} catch (err) {
				console.error("Fehler beim Laden der Zugdaten:", err);
				return [];
			}
		}
		//make inital call
		createDataObject().then((data) => setTrainData(data));

		//set interval of 60 seconds to load data
		setInterval(() => {
			createDataObject().then((data) => setTrainData(data));
		}, 60000);
	}, [selectedAction, dataTransferContext]);

	//return created ui component
	return (
		<>
			<ContentContainer contentContainerHeaderText="Zug Daten" topHeaderSectionChildren={<TrainDataTypeSelect setSelectedAction={setSelectedAction}/>}>
				{
					//check if to set chart component or no data information
					(trainData && trainData.length !== 0) ? (
						<ChartComponent data={trainData} yAxisText={selectedAction}/>
					) : (
						<div style={{height: "300px", display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
							<div style={{display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center'}}>
								<Image style={{height: '80px', width: '80px'}}
									   imageValue="images/color/Icon_Line_Chart_Color.png"/>
								<Text style={{fontSize: '20px', fontWeight: 'bold', marginTop: '.25%', textWrap: 'nowrap'}} textValue="Keine Daten verfügbar!"/>
							</div>
						</div>
					)
				}
			</ContentContainer>
		</>
	);
}

//create train data type select component
function TrainDataTypeSelect({setSelectedAction}: { setSelectedAction: (value: string) => void }) {
	return (
		<Select style={{fontWeight: "bold", fontSize: "18px"}}
				selectAction={(event) => setSelectedAction(event.target.value)}>
			<option value="Geschwindigkeit">Geschwindigkeit</option>
			<optgroup label="Verbrauch">
				<option value="Wasser">Wasser</option>
				<option value="Kohle">Kohle</option>
				<option value="Sand">Sand</option>
			</optgroup>
		</Select>
	);
}
