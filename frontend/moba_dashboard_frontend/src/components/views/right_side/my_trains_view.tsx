//import react library
import React, { useContext, useEffect, useState } from "react";

import Text, { ImageText } from "../../atoms/texts";
import GridBox from "../../container/GridBox";
//import custom react components
import { ContentContainer } from "../../container/content_container";
import { TrainOptionElement } from "../../molecules/train_option_element";

//import custom ts functions
import { setConsoleMessage } from "../../../logic/tools/messages";
import FlexBox from "../../container/FlexBox";

//import context
import { DataTransferContext } from "../../../App";
import { Select } from "../../atoms/input";
import { ImageButton } from "../../atoms/buttons";

//create and export default my trains container
export default function MyTrainsContainer() {
	//define state hook to load train options
	const [trainOptions, setTrainOptions] = useState<any[]>([]);
	//get data transfer context values
	const transferedData = useContext(DataTransferContext);

	//use effect to load train options from .json file
	useEffect(() => {
		//fetch data from .json file
		fetch("../data/trains.json")
			.then((jsonResponse) => jsonResponse.json())
			.then((jsonData) => setTrainOptions(jsonData))
			.catch((readJsonFileError) => setConsoleMessage(readJsonFileError, true));
	}, []);

	//function to set current selected train
	function currentSelectedTrainAction(currentTrainId: string): void {
		if (currentTrainId !== transferedData.selectedTrain) {
			transferedData.setSelectedTrain(currentTrainId);
		}
	}

	//define map for different train information options
	const trainInformationOptions = new Map<string, [string, string]>([
		[
			"currentSpeed",
			[`Aktuelle Geschwindigkeit: km/h`, "Icon_Speedometer_IOS_White"],
		],
		["coalValue", [`Aktueller Kohle stand:  kg`, "Icon_Coal_IOS_White"]],
		["waterValue", [`Aktueller Wasser Stand: l`, "Icon_Water_IOS_White"]],
		["sandValue", [`Aktueller Sand stand: kg`, "Icon_Sand_IOS_White"]],
	]);

	//return created ui component
	return (
		<>
			<ContentContainer contentContainerHeaderText="Meine Züge">
				<GridBox>
					{
						//check if to set train options
						trainOptions ? (
							trainOptions.map((currentTrain: any) => (
								<TrainOptionElement
									key={currentTrain.trainID}
									trainOptionElementText={currentTrain.trainName}
									trainOptionElementImage={currentTrain.trainImage}
									style={{
										border:
											transferedData.selectedTrain === currentTrain.trainID
												? "1px solid white"
												: "none",
									}}
									action={() =>
										currentSelectedTrainAction(currentTrain.trainID)
									}
								/>
							))
						) : (
							<ImageText textValue="No trains found!" />
						)
					}
				</GridBox>
				<hr />
				<div>
					{
						//load train information data
						Array.from(trainInformationOptions).map(
							([key, currentTrainInformation]) => (
								<ImageText
									key={key}
									textStyle={{ fontSize: "18px" }}
									textValue={currentTrainInformation[0]}
									imageStyle={{ height: "auto", width: "24px" }}
									textImage={`images/general/${currentTrainInformation[1]}.png`}
								/>
							),
						)
					}
				</div>
				<hr />
				<div style={{marginTop: '2%'}}>
					<Text
						style={{ fontWeight: "bold", fontSize: "20px" }}
						textValue="trAIn - Das Modellbahn AI System"
					/>
					<FlexBox style={{marginTop: '2%', alignItems: 'center'}}>
						<Select style={{width: '90%', fontWeight: 'bold', fontSize: "18px"}}>
							{
								//load all train select options
								trainOptions.map((currentTrainAiOption) => (
									<option value={currentTrainAiOption.trainID}>{currentTrainAiOption.trainName}</option>
								))
							}
						</Select>
						<ImageButton style={{marginLeft: '6%', backgroundColor: '#3fbbd7', padding: '4%', borderRadius: '10px'}}
									 imageStyle={{height: '32px', width: 'auto'}} buttonImage="images/general/Icon_Speak_IOS_White.png"/>
					</FlexBox>
				</div>
				<FlexBox />
			</ContentContainer>
		</>
	);
}
