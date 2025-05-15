//import react library
import React, { useContext, useEffect, useState } from "react";

//import skeleton properties
import Skeleton from "react-loading-skeleton";
import "react-loading-skeleton/dist/skeleton.css";

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
import {playSound} from "../../../logic/other/sounds";
import {getCurrentSpeed} from "../../../logic/backend/fetch_train_data";

//create and export default my trains container
export default function MyTrainsContainer() {
	//define state hook to load train options
	const [trainOptions, setTrainOptions] = useState<any[]>([]);
	//get data transfer context values
	const transferedData = useContext(DataTransferContext);

	//define state hook to fetch train data
	const [fetchedTrainData, setFetchedTrainData] = useState<any | null>(null);
	//define state for selected train name
	const [selectedAiTrainName, setSelectedAiTrainName] = useState<string>('Crossrail');

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

	//use effect to fetch train speed
	useEffect(() => {
		async function fetchSpeed(): Promise<void> {
			try {
				const latestData = await getCurrentSpeed(transferedData.selectedTrain);

				//add latest data
				setFetchedTrainData(latestData)
			} catch (err) {
				setConsoleMessage((err as any).message, true);
			}
		}
		//initial fetch
		fetchSpeed();

		//set interval of 30 seconds to load data
		const interval = setInterval(fetchSpeed, 30000);
		return () => clearInterval(interval);
	}, [transferedData.selectedTrain]);

	//define async function to play AI sound for selecte train
	async function playAiSound(): Promise<void> {
		//call function to play sound
		playSound(selectedAiTrainName);
	}

	//define map for different train information options
	const trainInformationOptions = new Map<string, [string, string]>([
		[
			"currentSpeed",
			[`Aktuelle Geschwindigkeit: ${(fetchedTrainData?.data / 10) ?? "--"} km/h`, "Icon_Speedometer_IOS_White"],
		],
		["coalValue", [`Aktueller Kohle stand: -- kg`, "Icon_Coal_IOS_White"]],
		["waterValue", [`Aktueller Wasser Stand: -- l`, "Icon_Water_IOS_White"]],
		["sandValue", [`Aktueller Sand stand: -- kg`, "Icon_Sand_IOS_White"]],
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
											transferedData.selectedTrain == currentTrain.trainID
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
								fetchedTrainData ? (
									<ImageText
										key={key}
										textStyle={{ fontSize: "18px" }}
										textValue={currentTrainInformation[0]}
										imageStyle={{ height: "auto", width: "24px" }}
										textImage={`images/general/${currentTrainInformation[1]}.png`}
									/>
								) : (
									<Skeleton key={key} style={{width: '90%', marginTop: '1.5%', marginBottom: '1.5%'}} baseColor="rgba(255, 255, 255, 0.1)"/>
								)
							)
						)
					}
				</div>
				<hr />
				<div style={{marginTop: '2%'}}>
					<Text
						style={{ fontWeight: "bold", fontSize: "20px" }}
						textValue="trAIn - Das Modellbahn AI System"
					/>
					<FlexBox style={{marginTop: '2%', justifyContent: 'space-between', alignItems: 'center'}}>
						<Select style={{width: '92%', fontWeight: 'bold', fontSize: "18px"}} selectAction={(event) => setSelectedAiTrainName(event.target.value)}>
							{
								//load all train select options
								trainOptions.map((currentTrainAiOption) => (
									<option value={currentTrainAiOption.trainName}>{`${currentTrainAiOption.trainName} - AI Ansage`}</option>
								))
							}
						</Select>
						<ImageButton style={{padding: '4%', borderRadius: '10px'}}
									 imageStyle={{height: '30px', width: 'auto'}} buttonImage="images/general/Icon_Speak_IOS_White.png"
									 buttonAction={playAiSound}/>
					</FlexBox>
				</div>
				<FlexBox />
			</ContentContainer>
		</>
	);
}
