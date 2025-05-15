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
import TextButton from "../../../atoms/buttons";
import {setConsoleMessage} from "../../../../logic/tools/messages";

//create and export default train data container
export default function TrainDataContainer() {
	//get data transfer context
	const dataTransferContext = useContext(DataTransferContext);

	//define state hook for selected train data type option
	const [selectedAction, setSelectedAction] = useState<string>('Geschwindigkeit');
	//define state hook for diagram data
	const [trainData, setTrainData] = useState<any[]>([]);

    //define state for amount of data values
    const [dataAmountOptions, setDataAmountOptions] = useState<any[]>([]);
    const [dataValuesAmount, setDataValuesAmount] = useState<number>(10);

    //use effect to load all data amount value options
    useEffect(() => {
        //fetch data from .json file
        fetch("../data/data_amount_options.json")
                .then((jsonResponse) => jsonResponse.json())
                .then((jsonData) => setDataAmountOptions(jsonData))
                .catch((readJsonFileError) => setConsoleMessage(readJsonFileError, true));
    }, []);

	//get data to show in diagram
	useEffect(() => {
		async function createDataObject(): Promise<any[]> {
			try {
				const fetchedData = (await fetchTrainInformation((dataTransferContext as any).selectedTrain, dataValuesAmount)).reverse();

				//set data to receuved format
				const formattedData = fetchedData.map((item: any) => ({
					name: format(new Date(item.timeStamp * 1000), 'HH:mm:ss'),
					value: item.data / 10
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
	}, [selectedAction, dataTransferContext, dataValuesAmount]);

	//return created ui component
	return (
		<>
			<ContentContainer contentContainerHeaderText="Zug Daten" topHeaderSectionChildren={<TrainDataTypeSelect setSelectedAction={setSelectedAction}/>}>
				{
					//check if to set chart component or no data information
					(trainData && trainData.length !== 0) ? (
                            <div>
                                <ChartComponent data={trainData} yAxisText={selectedAction}/>
								<hr style={{border: '1px solid white'}}/>
                                <FlexBox style={{justifyContent: 'space-between', alignItems: 'center', marginTop: '2%', marginBottom: '2%'}}>
                                    {
                                        //check if data loaded and add to ui
                                        dataAmountOptions.map((currentTrain: any, index: number) => (
                                                <TextButton key={index} style={{padding: '1%', fontSize: '18px', border: '.5px solid white'}}
                                                            buttonText={currentTrain.buttonText} buttonAction={() => setDataValuesAmount(currentTrain.buttonValue)}/>

                                        ))
                                    }
                                </FlexBox>
                            </div>
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
